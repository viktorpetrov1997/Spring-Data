package bg.softuni.orm;

import bg.softuni.orm.annotations.Column;
import bg.softuni.orm.annotations.Entity;
import bg.softuni.orm.annotations.Id;
import bg.softuni.orm.exceptions.ORMException;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;

public class EntityManager<E> implements DbContext<E>
{
    private static final String INSERT_QUERY = "INSERT INTO %s (%s) VALUES (%s)";
    private static final String SELECT_QUERY_WITH_WHERE_LIMIT = "SELECT * FROM %s %s %s";
    private static final String UPDATE_QUERY = "UPDATE %s SET %s WHERE %s = %s";

    private final Connection connection;

    public EntityManager(Connection connection)
    {
        this.connection = connection;
    }

    @Override
    public boolean persist(E entity) throws IllegalAccessException, SQLException
    {
        // TODO: Check if this is an @Entity class
        Field idField = getIdField(entity);
        int id = getIdFieldValue(entity, idField);

        if(id == 0)
        {
            return doInsert(entity);
        }

        return doUpdate(entity);
    }

    private boolean doInsert(E entity) throws SQLException
    {
        String tableName = getTableName(entity.getClass());
        List<String> columnNames = getColumnNamesWithoutId(entity);
        List<String> values = getValuesWithoutId(entity);

        String sqlValues = String.join(",", values);
        String sqlColumns = String.join(",", columnNames);

        String sql = String.format(INSERT_QUERY, tableName, sqlColumns, sqlValues);

        return this.connection.prepareStatement(sql).executeUpdate() > 0;
    }

    private String getTableName(Class<?> clazz)
    {
        Entity annotation = clazz.getAnnotation(Entity.class);

        if(annotation == null)
        {
            throw new ORMException("Entities must have @Entity annotation");
        }

        return annotation.name();
    }

    // TODO: Add default if name in @Column is missing
    private List<String> getColumnNamesWithoutId(E entity)
    {
        return Arrays.stream(entity
                        .getClass()
                        .getDeclaredFields())
                .filter(field -> field.isAnnotationPresent(Column.class))
                .filter(field -> !field.isAnnotationPresent(Id.class))
                .map(field -> field.getAnnotation(Column.class).name())
                .toList();
    }

    private List<String> getValuesWithoutId(E entity)
    {
        return Arrays.stream(entity
                        .getClass()
                        .getDeclaredFields())
                .filter(field -> field.isAnnotationPresent(Column.class))
                .filter(field -> !field.isAnnotationPresent(Id.class))
                .map(field ->
                {
                    try
                    {
                        field.setAccessible(true);
                        return field.get(entity);
                    }
                    catch(IllegalAccessException e)
                    {
                        throw new RuntimeException(e);
                    }
                })
                .map(Object::toString)
                .map(s -> "'" + s + "'")
                .toList();
    }

    private boolean doUpdate(E entity) throws IllegalAccessException, SQLException
    {
        String tableName = getTableName(entity.getClass());
        Field idColumn = getIdField(entity);
        int idValue = getIdFieldValue(entity, idColumn);
        String fieldListWithValues = getFieldsWithValues(entity);

        String sql = String.format(UPDATE_QUERY, tableName, fieldListWithValues, idColumn.getName(), idValue);

        return this.connection.prepareStatement(sql).executeUpdate() > 0;
    }

    private Field getIdField(E entity)
    {
        Optional<Field> idField = Arrays.stream(entity.getClass().getDeclaredFields())
                .filter(field -> field.isAnnotationPresent(Id.class))
                .findFirst();

        if(idField.isEmpty())
        {
            throw new ORMException("Entity without @Id field");
        }

        return idField.get();
    }

    private int getIdFieldValue(E entity, Field idField) throws IllegalAccessException
    {
        idField.setAccessible(true);
        Object value = idField.get(entity);

        return Integer.parseInt(value.toString());
    }

    private String getFieldsWithValues(E entity)
    {
        List<String> columnNames = getColumnNamesWithoutId(entity);
        List<String> values = getValuesWithoutId(entity);

        List<String> result = new ArrayList<>();
        for(int i = 0; i < columnNames.size(); i++)
        {
            result.add(columnNames.get(i) + "=" + values.get(i));
        }

        return String.join(",", result);
    }

    @Override
    public Iterable<E> find(Class<E> clazz) throws SQLException, InvocationTargetException, NoSuchMethodException, InstantiationException, IllegalAccessException
    {
        return find(clazz, null);
    }

    @Override
    public Iterable<E> find(Class<E> clazz, String where) throws SQLException, InvocationTargetException, NoSuchMethodException, InstantiationException, IllegalAccessException
    {
        return baseFind(clazz, where, null);
    }

    @Override
    public E findFirst(Class<E> clazz) throws SQLException, InvocationTargetException, NoSuchMethodException, InstantiationException, IllegalAccessException
    {
        return findFirst(clazz, null);
    }

    @Override
    public E findFirst(Class<E> clazz, String where) throws SQLException, InvocationTargetException, NoSuchMethodException, InstantiationException, IllegalAccessException
    {
        Iterable<E> items = baseFind(clazz, where, 1);

        Iterator<E> iterator = items.iterator();
        if(!iterator.hasNext())
        {
            return null;
        }

        return iterator.next();
    }

    private Iterable<E> baseFind(Class<E> clazz, String where, Integer limit) throws SQLException, InvocationTargetException, NoSuchMethodException, InstantiationException, IllegalAccessException
    {
        String tableName = getTableName(clazz);

        String computedWhere = where == null ? "" : "WHERE " + where;
        String computedLimit = limit == null ? "" : "LIMIT " + limit;

        String sql = String.format(SELECT_QUERY_WITH_WHERE_LIMIT, tableName, computedWhere, computedLimit);

        ResultSet result = connection.prepareStatement(sql).executeQuery();

        List<E> iterable = new ArrayList<>();

        while(result.next())
        {
            E nextItem = fillEntity(result, clazz);

            iterable.add(nextItem);
        }

        return iterable;
    }

    private E fillEntity(ResultSet result, Class<E> clazz) throws NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException
    {
        E resultEntity = clazz.getConstructor().newInstance();

        Arrays.stream(clazz.getDeclaredFields())
                .filter(f -> f.isAnnotationPresent(Column.class))
                .forEach(f ->
                {
                    try
                    {
                        fillField(resultEntity, result, f);
                    }
                    catch (SQLException | IllegalAccessException e)
                    {
                        throw new RuntimeException(e);
                    }
                });

        return resultEntity;
    }

    private void fillField(E entity, ResultSet result, Field field) throws SQLException, IllegalAccessException
    {
        String columnName = field.getAnnotation(Column.class).name();

        String dbValue = result.getString(columnName);
        Object value = getValueWithCorrectType(field.getType(), dbValue);

        field.setAccessible(true);
        field.set(entity, value);
    }

    private Object getValueWithCorrectType(Class<?> type, String dbValue)
    {
        if(type == int.class || type == Integer.class)
        {
            return Integer.parseInt(dbValue);
        }
        else if(type == double.class || type == Double.class)
        {
            return Double.parseDouble(dbValue);
        }
        else if(type == String.class)
        {
            return dbValue;
        }
        else if(type == LocalDate.class)
        {
            return LocalDate.parse(dbValue);
        }

        throw new ORMException("Unsupported data type " + type);
    }
}
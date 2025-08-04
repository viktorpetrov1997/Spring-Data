package sofuni.exam.database;
//TestDbPrimaryKeys
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DataSourceUtils;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@DataJpaTest
public class TestDbPrimaryKeys {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public JdbcTemplate getJdbcTemplate() {
        return jdbcTemplate;
    }


    @Test
    void testPrimaryKeys() throws SQLException {
        DatabaseMetaData metaData = getDatabaseMetaData();

        ResultSet primaryKeyMoons = metaData.getPrimaryKeys(null, null, "MOONS");
        ResultSet primaryKeyPlanets = metaData.getPrimaryKeys(null, null, "PLANETS");
        ResultSet primaryKeyDiscoverers = metaData.getPrimaryKeys(null, null, "DISCOVERERS");

        List<String> actualResult = new ArrayList<>();

        primaryKeyMoons.next();
        actualResult.add(primaryKeyMoons.getString("TABLE_NAME"));
        actualResult.add(primaryKeyMoons.getString("COLUMN_NAME"));

        primaryKeyPlanets.next();
        actualResult.add(primaryKeyPlanets.getString("TABLE_NAME"));
        actualResult.add(primaryKeyPlanets.getString("COLUMN_NAME"));

        primaryKeyDiscoverers.next();
        actualResult.add(primaryKeyDiscoverers.getString("TABLE_NAME"));
        actualResult.add(primaryKeyDiscoverers.getString("COLUMN_NAME"));


        List<String> expectedResult = new ArrayList<>();

        expectedResult.add("MOONS");
        expectedResult.add("ID");
        expectedResult.add("PLANETS");
        expectedResult.add("ID");
        expectedResult.add("DISCOVERERS");
        expectedResult.add("ID");


        Assertions.assertArrayEquals(expectedResult.stream().sorted().toArray(), actualResult.stream().sorted().toArray());
    }

    private DatabaseMetaData getDatabaseMetaData() throws SQLException {
        DataSource dataSource = getJdbcTemplate().getDataSource();
        Connection connection = DataSourceUtils.getConnection(dataSource);
        return connection.getMetaData();
    }
}
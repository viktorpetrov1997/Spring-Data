package DBAppsIntroduction.Exercises;

import java.sql.*;
import java.util.Scanner;

public class AddMinion
{
    public static int ensureTown(Connection connection, String townName) throws SQLException
    {
        PreparedStatement selectStatement = connection.prepareStatement("SELECT id FROM towns where name = ?;");

        selectStatement.setString(1, townName);

        ResultSet resultSet = selectStatement.executeQuery();

        if(resultSet.next())
        {
            return resultSet.getInt(1);
        }

        PreparedStatement insertStatement = connection.prepareStatement("INSERT INTO towns(name) VALUES(?);", Statement.RETURN_GENERATED_KEYS);
        insertStatement.setString(1, townName);

        insertStatement.executeUpdate();

        ResultSet generatedKeys = insertStatement.getGeneratedKeys();

        if(!generatedKeys.next())
        {
            throw new IllegalStateException("Could not access generated key for town.");
        }

        System.out.printf("Town %s was added to the database.%n", townName);
        return generatedKeys.getInt(1);
    }

    public static int ensureVillain(Connection connection, String villainName) throws SQLException
    {
        PreparedStatement selectStatement = connection.prepareStatement("SELECT id FROM villains where name = ?;");

        selectStatement.setString(1, villainName);

        ResultSet resultSet = selectStatement.executeQuery();

        if(resultSet.next())
        {
            return resultSet.getInt(1);
        }

        PreparedStatement insertStatement = connection.prepareStatement(
                "INSERT INTO villains(name, evilness_factor) VALUES(?, 'evil');", Statement.RETURN_GENERATED_KEYS);

        insertStatement.setString(1, villainName);

        insertStatement.executeUpdate();

        ResultSet generatedKeys = insertStatement.getGeneratedKeys();

        if(!generatedKeys.next())
        {
            throw new IllegalStateException("Could not access generated key for villain.");
        }

        System.out.printf("Villain %s was added to the database.%n", villainName);
        return generatedKeys.getInt(1);
    }

    private static int createMinion(Connection connection, String name, int age, int townId) throws SQLException
    {
        PreparedStatement insertStatement = connection.prepareStatement(
                "INSERT INTO minions(name, age, town_id) VALUES(?, ?, ?);", Statement.RETURN_GENERATED_KEYS);

        insertStatement.setString(1, name);
        insertStatement.setInt(2, age);
        insertStatement.setInt(3, townId);

        insertStatement.executeUpdate();

        ResultSet generatedKeys = insertStatement.getGeneratedKeys();

        if(!generatedKeys.next())
        {
            throw new IllegalStateException("Could not access generated key for minion.");
        }

        return generatedKeys.getInt(1);
    }

    private static void connectMinionAndVillain(Connection connection, int minionId, int villainId) throws SQLException
    {
        PreparedStatement insertStatement = connection.prepareStatement(
                "INSERT INTO minions_villains(minion_id, villain_id) VALUES(?, ?);");

        insertStatement.setInt(1, minionId);
        insertStatement.setInt(2, villainId);

        insertStatement.executeUpdate();
    }

    public static void main(String[] args) throws SQLException
    {
        Scanner scanner = new Scanner(System.in);

        Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/minions_db", "root", "12345");

        String[] minionData = scanner.nextLine().split("\\s+");
        String minionName = minionData[1];
        int age = Integer.parseInt(minionData[2]);
        String townName = minionData[3];

        String[] villainData = scanner.nextLine().split("\\s+");
        String villainName = villainData[1];

        int townId = ensureTown(connection, townName);
        int villainId = ensureVillain(connection, villainName);
        int minionId = createMinion(connection, minionName, age, townId);
        connectMinionAndVillain(connection, minionId, villainId);

        System.out.printf("Successfully added %s to be minion of %s.%n", minionName, villainName);
    }
}

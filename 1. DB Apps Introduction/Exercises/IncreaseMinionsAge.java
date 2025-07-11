package DBAppsIntroduction.Exercises;

import java.sql.*;
import java.util.Arrays;
import java.util.Collections;
import java.util.Scanner;

public class IncreaseMinionsAge
{
    private static void updateMinions(Connection connection, int[] minionIds) throws SQLException
    {
        String idParameters = String.join(",", Collections.nCopies(minionIds.length, "?"));
        PreparedStatement updateStatement = connection.prepareStatement(String.format("UPDATE minions SET age = age + 1, name = lower(name) WHERE id IN (%s);", idParameters));

        for(int i = 0; i < minionIds.length; i++)
        {
            updateStatement.setInt(i + 1, minionIds[i]);
        }

        updateStatement.executeUpdate();
    }

    private static void printMinions(Connection connection) throws SQLException
    {
        PreparedStatement selectStatement = connection.prepareStatement("SELECT name, age FROM minions");

        ResultSet resultSet = selectStatement.executeQuery();

        while(resultSet.next())
        {
            String name = resultSet.getString(1);
            int age = resultSet.getInt(2);

            System.out.printf("%s %s%n", name, age);
        }
    }

    public static void main(String[] args) throws SQLException
    {
        Scanner scanner = new Scanner(System.in);

        Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/minions_db", "root", "12345");

        int[] minionIds = Arrays.stream(scanner.nextLine().split("\\s+")).mapToInt(Integer::parseInt).toArray();

        updateMinions(connection, minionIds);
        printMinions(connection);
    }
}

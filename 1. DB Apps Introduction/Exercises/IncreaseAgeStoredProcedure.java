package DBAppsIntroduction.Exercises;

import java.sql.*;
import java.util.Scanner;

public class IncreaseAgeStoredProcedure
{
    private static void increaseMinionAge(Connection connection, int minionId) throws SQLException
    {
        PreparedStatement callStatement = connection.prepareCall("CALL usp_get_older(?);");

        callStatement.setInt(1, minionId);

        callStatement.executeUpdate();
    }

    private static void printMinion(Connection connection, int minionId) throws SQLException
    {
        PreparedStatement selectStatement = connection.prepareStatement("SELECT name, age FROM minions WHERE id = ?;");

        selectStatement.setInt(1, minionId);

        ResultSet resultSet = selectStatement.executeQuery();

        if(resultSet.next())
        {
            String name = resultSet.getString(1);
            int age = resultSet.getInt(2);

            System.out.printf("%s %d", name, age);
        }
    }

    public static void main(String[] args) throws SQLException
    {
        Scanner scanner = new Scanner(System.in);

        Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/minions_db", "root", "12345");

        int minionId = Integer.parseInt(scanner.nextLine());

        increaseMinionAge(connection, minionId);
        printMinion(connection, minionId);
    }
}

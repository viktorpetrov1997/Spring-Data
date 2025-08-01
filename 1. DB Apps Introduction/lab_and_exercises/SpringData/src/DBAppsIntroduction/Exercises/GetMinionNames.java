package DBAppsIntroduction.Exercises;

import java.sql.*;
import java.util.Scanner;

public class GetMinionNames
{
    public static boolean printVillain(Connection connection, int villainId) throws SQLException
    {
        PreparedStatement query = connection.prepareStatement("SELECT name FROM villains where id = ?;");

        query.setInt(1, villainId);

        ResultSet result = query.executeQuery();

        if(result.next())
        {
            String name = result.getString("name");
            System.out.printf("Villain: %s%n", name);

            return true;
        }

        System.out.printf("No villain with ID %d exists in the database.", villainId);
        return false;
    }

    public static void printMinions(Connection connection, int villainId) throws SQLException
    {
        PreparedStatement query = connection.prepareStatement("SELECT name, age FROM minions m " +
                "JOIN minions_villains mv ON m.id = mv.minion_id\n" +
                "WHERE mv.villain_id = ?;");

        query.setInt(1, villainId);

        int order = 0;

        ResultSet result = query.executeQuery();

        while(result.next())
        {
            String name = result.getString(1);
            int age = result.getInt(2);

            System.out.printf("%d. %s %d%n", ++order, name, age);
        }
    }

    public static void main(String[] args) throws SQLException
    {
        Scanner scanner = new Scanner(System.in);

        int villainId = Integer.parseInt(scanner.nextLine());

        Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/minions_db", "root", "12345");

        boolean villainExists = printVillain(connection, villainId);

        if(villainExists)
        {
            printMinions(connection, villainId);
        }

        connection.close();
    }
}

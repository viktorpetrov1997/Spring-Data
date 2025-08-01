package DBAppsIntroduction.Exercises;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class ChangeTownNameCasing
{
    public static int uppercaseTownNames(Connection connection, String country) throws SQLException
    {
        PreparedStatement updateStatement = connection.prepareStatement("UPDATE towns SET name = upper(name) WHERE country = ?;");

        updateStatement.setString(1, country);

        return updateStatement.executeUpdate();
    }

    public static void printTownNames(Connection connection, String country) throws SQLException
    {
        PreparedStatement selectStatement = connection.prepareStatement("SELECT name FROM towns WHERE country = ?;");

        selectStatement.setString(1, country);

        ResultSet resultSet = selectStatement.executeQuery();

        List<String> updatedTowns = new ArrayList<>();

        while(resultSet.next())
        {
            String townName = resultSet.getString(1);
            updatedTowns.add(townName);
        }

        System.out.println(updatedTowns);
    }

    public static void main(String[] args) throws SQLException
    {
        Scanner scanner = new Scanner(System.in);

        Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/minions_db", "root", "12345");

        String country = scanner.nextLine();

        int updatedRowsCount = uppercaseTownNames(connection, country);

        if(updatedRowsCount == 0)
        {
            System.out.println("No town names were affected.");
        }
        else
        {
            System.out.printf("%d town names were affected.%n", updatedRowsCount);
            printTownNames(connection, country);
        }
    }
}

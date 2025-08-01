package DBAppsIntroduction.Lab;

import java.sql.*;
import java.util.Properties;
import java.util.Scanner;

public class SoftUniDatabaseDataRetrieval
{
    public static void main(String[] args) throws SQLException
    {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter username (Default: root): ");
        String user = scanner.nextLine();
        user = user.equals("") ? "root" : user;
        System.out.println();

        System.out.print("Enter password: ");
        String password = scanner.nextLine().trim();
        System.out.println();

        Properties props = new Properties();
        props.setProperty("user", user);
        props.setProperty("password", password);

        Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/soft_uni", props);

        PreparedStatement query = connection.prepareStatement("SELECT * FROM employees WHERE salary > ?");

        System.out.print("Salary: ");
        String salary = scanner.nextLine();
        System.out.println();

        query.setDouble(1, Double.parseDouble(salary));
        ResultSet result = query.executeQuery();

        while(result.next())
        {
            System.out.println(result.getString("first_name") + " " + result.getString("last_name"));
        }

        connection.close();
    }
}
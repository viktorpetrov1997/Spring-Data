package DBAppsIntroduction.Lab;

import java.sql.*;
import java.util.Properties;
import java.util.Scanner;

public class DiabloDatabaseDataRetrieval
{
    public static void main(String[] args) throws SQLException
    {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter database username (Default: root): ");
        String user = scanner.nextLine();
        user = user.equals("") ? "root" : user;
        System.out.println();

        System.out.print("Enter password: ");
        String password = scanner.nextLine().trim();
        System.out.println();

        Properties props = new Properties();
        props.setProperty("user", user);
        props.setProperty("password", password);

        Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/diablo", props);

        System.out.print("Enter username (Example: nakov): ");
        String username = scanner.nextLine().trim();
        System.out.println();

        PreparedStatement query = connection.prepareStatement("SELECT user_name, first_name, last_name, COUNT(ug.id) as games_count FROM users u " +
                "JOIN users_games AS ug ON u.id = ug.user_id " +
                "WHERE u.user_name = ? " +
                "group by user_name, first_name, last_name;");

        query.setString(1, username);
        ResultSet result = query.executeQuery();

        if(result.next())
        {
            String user_name = result.getString("user_name");
            String first_name = result.getString("first_name");
            String last_name = result.getString("last_name");
            int gamesCount = result.getInt("games_count");

            System.out.println("User: " + user_name);
            System.out.printf("%s %s has played %d games", first_name, last_name, gamesCount);
        }
        else
        {
            System.out.println("No such user exists");
        }
    }
}

package DBAppsIntroduction.Exercises;

import java.sql.*;

public class GetVillainsNames
{
    public static void main(String[] args) throws SQLException
    {
        Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/minions_db", "root", "12345");

        PreparedStatement query = connection.prepareStatement("SELECT v.name, count(*) from villains v " +
                "JOIN minions_villains mv " +
                "ON v.id = mv.villain_id " +
                "GROUP BY v.id " +
                "HAVING count(*) > 15 " +
                "ORDER BY count(*) DESC;");

        ResultSet result = query.executeQuery();

        while(result.next())
        {
            System.out.println(result.getString(1) + " " + result.getInt(2));

            // Second way of printing the data by using the exact names of the columns in the query.
            // We can also use an alias for the name of column count(*).
            // System.out.println(result.getString("name") + " " + result.getInt("count(*)"));
        }

        connection.close();
    }
}

package DBAppsIntroduction.Exercises;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PrintAllMinionNamesSecondWay
{
    public static void main(String[] args) throws SQLException
    {
        Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/minions_db", "root", "12345");

        PreparedStatement query = connection.prepareStatement("SELECT name from minions");

        ResultSet resultSet = query.executeQuery();

        List<String> minionNames = new ArrayList<>();

        while(resultSet.next())
        {
            minionNames.add(resultSet.getString(1));
        }

        int start = 0;
        int end = minionNames.size() - 1;

        while(start <= end)
        {
            System.out.println(minionNames.get(start++));
            if(start <= end)
            {
                System.out.println(minionNames.get(end--));
            }
        }
    }
}

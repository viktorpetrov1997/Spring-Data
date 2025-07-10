package DBAppsIntroduction.Exercises;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PrintAllMinionNames
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

        for(int i = 0; i < minionNames.size(); i++)
        {
            String next;
            if(i % 2 == 0)
            {
                next = minionNames.get(i / 2);
            }
            else
            {
                next = minionNames.get(minionNames.size() - (i + 1) / 2);
            }
            System.out.println(next);
        }
    }
}

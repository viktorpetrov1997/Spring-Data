package DBAppsIntroduction.Exercises;

import java.sql.*;
import java.util.Scanner;

public class RemoveVillain
{
    private static String findVillain(Connection connection, int villainId) throws SQLException
    {
        PreparedStatement selectStatement = connection.prepareStatement("SELECT name from villains where id = ?");

        selectStatement.setInt(1, villainId);

        ResultSet resultSet = selectStatement.executeQuery();

        if(!resultSet.next())
        {
            return null;
        }

        return resultSet.getString(1);
    }

    private static int deleteConnectedEntities(Connection connection, int villainId) throws SQLException
    {
        PreparedStatement deleteStatement = connection.prepareStatement("DELETE FROM minions_villains where villain_id = ?");

        deleteStatement.setInt(1, villainId);

        return deleteStatement.executeUpdate();
    }

    private static void deleteVillain(Connection connection, int villainId) throws SQLException
    {
        PreparedStatement deleteStatement = connection.prepareStatement("DELETE FROM villains where id = ?");

        deleteStatement.setInt(1, villainId);

        deleteStatement.executeUpdate();
    }

    public static void main(String[] args) throws SQLException
    {
        Scanner scanner = new Scanner(System.in);

        int villainId = Integer.parseInt(scanner.nextLine());

        Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/minions_db", "root", "12345");

        String villainName = findVillain(connection, villainId);

        if(villainName == null)
        {
            System.out.println("No such villain was found");
        }
        else
        {
            try
            {
                int releasedMinionsCount = deleteConnectedEntities(connection, villainId);
                deleteVillain(connection, villainId);

                System.out.printf("%s was deleted%n", villainName);
                System.out.printf("%d minions released%n", releasedMinionsCount);
            }
            catch(Exception e)
            {
                connection.rollback();
                throw e;
            }
        }
    }
}

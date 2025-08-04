package sofuni.exam.database;
//TestDbForeignKeysDevices
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DataSourceUtils;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@DataJpaTest
public class TestDbForeignKeysMoons {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public JdbcTemplate getJdbcTemplate() {
        return jdbcTemplate;
    }

    @Test
    void testForeignKeys() throws SQLException {
        DatabaseMetaData metaData = getDatabaseMetaData();

        ResultSet moonsKeys = metaData.getImportedKeys(null, null, "MOONS");

        List<String> actualResult = new ArrayList<>();
        moonsKeys.next();
        actualResult.add(moonsKeys.getString("PKTABLE_NAME"));
        actualResult.add(moonsKeys.getString("PKCOLUMN_NAME"));
        actualResult.add(moonsKeys.getString("PKCOLUMN_NAME"));
        actualResult.add(moonsKeys.getString("FKCOLUMN_NAME"));


        List<String> expectedResult = new ArrayList<>();
        expectedResult.add("DISCOVERERS");
        expectedResult.add("DISCOVERER_ID");
        expectedResult.add("ID");
        expectedResult.add("ID");

        Assertions.assertArrayEquals(expectedResult.stream().sorted().toArray(), actualResult.stream().sorted().toArray());
    }

    private DatabaseMetaData getDatabaseMetaData() throws SQLException {
        DataSource dataSource = getJdbcTemplate().getDataSource();
        Connection connection = DataSourceUtils.getConnection(dataSource);
        return connection.getMetaData();
    }
}
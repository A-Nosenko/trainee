package app.database.connection;

import app.exception.AppException;
import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.SQLException;
import org.junit.AfterClass;
import org.junit.Test;

public class ConnectionFactoryTest {
    private static Props props = new Props("jdbc:mysql://localhost:3306?useUnicode=true" +
        "&useJDBCCompliantTimezoneShift=true" +
        "&useLegacyDatetimeCode=false" +
        "&serverTimezone=UTC",
        "root",
        "root",
        "com.mysql.cj.jdbc.Driver");
    private static Connection connection = ConnectionFactory.getConnection(props);

    @Test
    public void nullPropsTest() {
        assert (ConnectionFactory.getConnection(null) == null);
    }

    @Test()
    public void getConnection() {
        assert (connection != null);
        try {
            DatabaseMetaData metaData = connection.getMetaData();
            System.out.println(metaData.getDriverVersion());
            assert (metaData.getDriverVersion().startsWith("mysql-connector-java-"));
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Test(expected = AppException.class)
    public void exceptionTest() {
        ConnectionFactory.getConnection(
            new Props(null, null, null, null));
    }

    @AfterClass
    public static void methodAfterClass() {
        if (connection != null) {
            try {
                connection.close();
                connection = null;
                props = null;
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}

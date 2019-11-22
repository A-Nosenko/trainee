package app.database.query;

import app.database.connection.ConnectionFactory;
import app.database.connection.Props;
import app.exception.AppException;
import java.sql.Connection;
import java.sql.SQLException;
import org.junit.AfterClass;
import static org.junit.Assert.*;
import org.junit.Test;

public class QueryManagerTest {

    private static Props props = new Props("jdbc:mysql://localhost:3306?useUnicode=true" +
        "&useJDBCCompliantTimezoneShift=true" +
        "&useLegacyDatetimeCode=false" +
        "&serverTimezone=UTC",
        "root",
        "root",
        "com.mysql.cj.jdbc.Driver");
    private static Connection connection = ConnectionFactory.getConnection(props);
    private static QueryManager queryManager = QueryManager.getInstance();

    @Test
    public void getInstance() {
        assertNotNull(queryManager);
    }

    @Test
    public void getDatabasesNames() {
        assertTrue(queryManager.getDatabasesNames(connection).size() > 0);
    }

    @Test(expected = AppException.class)
    public void getDatabasesNamesException() {
        queryManager.getDatabasesNames(null);
    }

    @Test
    public void getTablesNames() {
        assertTrue(queryManager.getTablesNames("sys", connection).size() > 0);
    }

    @Test(expected = AppException.class)
    public void getTablesNamesException() {
        queryManager.getTablesNames("sys", null);
    }

    @Test
    public void getDDL() {
        assertTrue(queryManager.getDDL("sys", DDL.TABLE, "sys_config", connection).contains("CREATE TABLE"));
    }

    @Test(expected = AppException.class)
    public void getTableDDLException() {
        queryManager.getDDL("sys", DDL.TABLE, null, connection);
    }

    @Test
    public void getColumnAttributes() {
        assertTrue(queryManager.getColumnAttributes("sys", "sys_config", connection).size() > 0);
    }

    @Test(expected = AppException.class)
    public void getColumnAttributesException() {
        queryManager.getColumnAttributes(null, "sys_config", connection);
    }

    @Test
    public void getTableViews() {
        assertTrue(queryManager.getTableViews("host_summary", connection).size() > 0);
    }

    @Test(expected = AppException.class)
    public void getTableViewsException() {
        queryManager.getTableViews(null, null);
    }

    @Test
    public void getDatabaseStoredProcedures() {
        assertTrue(queryManager.getDatabaseStoredProcedures("sys", connection).size() > 0);
    }

    @Test(expected = AppException.class)
    public void getDatabaseStoredProceduresException() {
        queryManager.getDatabaseStoredProcedures(null, null);
    }

    @Test
    public void getDatabaseFunctions() {
        assertTrue(queryManager.getDatabaseFunctions("sys", connection).size() > 0);
    }

    @Test(expected = AppException.class)
    public void getDatabaseFunctionsException() {
        queryManager.getDatabaseFunctions(null, null);
    }

    @Test
    public void getForeignKeyAttributes() {
        assertTrue(queryManager
            .getColumnKeyAttributes("sys", "sys_config", "variable", connection).size() > 0);
    }

    @Test(expected = AppException.class)
    public void getForeignKeyAttributesException() {
        queryManager.getColumnKeyAttributes("sys", "sys_config", null, null);
    }

    @Test
    public void getTriggersAttributes() {
        assertTrue(queryManager
            .getTriggersAttributes("sys", "sys_config", connection).size() == 2);
    }

    @Test
    public void getLastInsertId() {
        assertTrue(queryManager
            .getLastInsertId("sys", "sys_config", connection) > 0);
    }

    @Test(expected = AppException.class)
    public void getLastInsertIdException() {
        queryManager.getLastInsertId("sys", null, connection);
    }

    @AfterClass
    public static void methodAfterClass() {
        if (connection != null) {
            try {
                connection.close();
                props = null;
                connection = null;
                queryManager = null;
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}
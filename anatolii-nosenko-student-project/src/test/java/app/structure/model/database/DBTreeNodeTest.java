package app.structure.model.database;

import app.database.connection.ConnectionFactory;
import app.database.connection.Props;
import app.exception.AppException;
import app.literals.Constants;
import app.structure.model.Item;
import java.sql.Connection;
import java.sql.SQLException;
import org.junit.AfterClass;
import static org.junit.Assert.*;
import org.junit.Test;

public class DBTreeNodeTest {

    private static Props props = new Props("jdbc:mysql://localhost:3306?useUnicode=true" +
        "&useJDBCCompliantTimezoneShift=true" +
        "&useLegacyDatetimeCode=false" +
        "&serverTimezone=UTC",
        "root",
        "root",
        "com.mysql.cj.jdbc.Driver");
    private static Connection connection = ConnectionFactory.getConnection(props);

    @Test
    public void initChildNodes() {
        Item item = new Item();
        item.setAttribute(Constants.DATABASE_NAME, "sys");
        item.setAttribute(Constants.TABLE_NAME, "user_summary");

        DBTreeNode dbTreeNode = new TableDatabaseTreeNode(item);
        assertNotNull(dbTreeNode.initChildNodes(connection, false));

    }

    @Test(expected = AppException.class)
    public void initChildNodesException() {
        Item item = new Item();
        item.setAttribute(Constants.DATABASE_NAME, "sys");
        item.setAttribute(Constants.TABLE_NAME, "user_summary");

        DBTreeNode dbTreeNode = new TableDatabaseTreeNode(item);
        dbTreeNode.initChildNodes(new Object(), true);

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

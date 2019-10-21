package app.structure.model.database;

import app.database.connection.ConnectionFactory;
import app.database.connection.Props;
import app.structure.model.Item;
import java.sql.Connection;
import java.sql.SQLException;
import org.junit.AfterClass;
import static org.junit.Assert.*;
import org.junit.Test;

public class FunctionDatabaseTreeNodeTest {

    private static Props props = new Props("jdbc:mysql://localhost:3306?useUnicode=true" +
        "&useJDBCCompliantTimezoneShift=true" +
        "&useLegacyDatetimeCode=false" +
        "&serverTimezone=UTC",
        "root",
        "root",
        "com.mysql.cj.jdbc.Driver");
    private static Connection connection = ConnectionFactory.getConnection(props);

    @Test
    public void fetchChildNodes() {
        DBTreeNode dbTreeNode = new FunctionDatabaseTreeNode(new Item());
        System.out.println(dbTreeNode.toJSON());
        assertNull(
            dbTreeNode.fetchChildNodes(null));
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
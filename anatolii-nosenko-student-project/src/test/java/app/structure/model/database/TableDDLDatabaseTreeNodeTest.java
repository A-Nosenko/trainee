package app.structure.model.database;

import app.database.connection.ConnectionFactory;
import app.database.connection.Props;
import app.literals.Constants;
import app.structure.model.Item;
import app.structure.model.TreeNode;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import org.junit.AfterClass;
import org.junit.Test;

public class TableDDLDatabaseTreeNodeTest {

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

        DBTreeNode dbTreeNode = new TableDDLDatabaseTreeNode(item);
        List<TreeNode> nodes = dbTreeNode.initChildNodes(connection, true);
        assert (nodes.size() > 0);
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
package app.structure.model.database;

import app.database.connection.ConnectionFactory;
import app.database.connection.Props;
import app.literals.Constants;
import app.structure.model.Item;
import app.structure.model.TreeNode;
import java.sql.Connection;
import java.sql.SQLException;
import org.junit.AfterClass;
import org.junit.Test;

public class ViewsDatabaseTreeNodeTest {

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
        item.setAttribute(Constants.TABLE_NAME, "schema_auto_increment_columns");

        DBTreeNode dbTreeNode = new ViewsDatabaseTreeNode(item);
        System.out.println(dbTreeNode.toJSON());
        assert (dbTreeNode.initChildNodes(connection, true).size() > 0);

        for (TreeNode node : dbTreeNode.getChildTreeNodes()) {
            System.out.println(node.toJSON());
        }
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
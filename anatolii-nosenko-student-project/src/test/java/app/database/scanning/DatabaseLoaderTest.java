package app.database.scanning;

import app.database.connection.ConnectionFactory;
import app.database.connection.Props;
import app.parsing.xml.TreeSaverToXML;
import app.structure.model.Item;
import app.structure.model.TreeModel;
import app.structure.model.TreeNode;
import app.structure.model.database.RootDatabasesTreeNode;
import app.structure.search.BreadthFirstSearcher;
import java.sql.Connection;
import java.sql.SQLException;
import org.junit.AfterClass;
import static org.junit.Assert.*;
import org.junit.Test;

public class DatabaseLoaderTest {
    private static Props props = new Props("jdbc:mysql://localhost:3306?useUnicode=true" +
        "&useJDBCCompliantTimezoneShift=true" +
        "&useLegacyDatetimeCode=false" +
        "&serverTimezone=UTC",
        "root",
        "root",
        "com.mysql.cj.jdbc.Driver");
    private static Connection connection = ConnectionFactory.getConnection(props);
    private static DatabaseLoader loader = new DatabaseLoader(connection);
    private static TreeSaverToXML treeSaverToXML = new TreeSaverToXML();

    @Test
    public void nodesLazyLoading() {
        TreeModel treeModel = new TreeModel(new BreadthFirstSearcher());
        treeModel.add(new RootDatabasesTreeNode(new Item()));
        TreeNode rootNode = treeModel.getRoot();
        assert (rootNode.getChildTreeNodes().size() == 0);
        loader.nodesLazyLoading(treeModel, rootNode.getItem().getUniqueId());
        assert (rootNode.getChildTreeNodes().size() > 0);
        for (TreeNode lazyLoadedNode : rootNode.getChildTreeNodes()) {
            assert (lazyLoadedNode.getChildTreeNodes().size() == 0);
            loader.nodesLazyLoading(treeModel, lazyLoadedNode.getItem().getUniqueId());
            assert (lazyLoadedNode.getChildTreeNodes().size() > 0);
        }
        assertNull(loader.nodesLazyLoading(treeModel, 0));
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
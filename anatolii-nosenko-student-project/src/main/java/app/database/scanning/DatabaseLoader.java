package app.database.scanning;

import app.structure.model.TreeModel;
import app.structure.model.TreeNode;
import java.sql.Connection;
import java.util.List;
import org.apache.log4j.Logger;

/**
 * Class to load database structure to TreeModel object.
 * <p>
 * To load full tree without lazy loading you should create tree with RootDatabasesTreeNode
 * as root node and run initialization child nodes with false lazy initialization flag:
 * <p>
 * TreeModel tree = new TreeModel(searcher);
 * tree.add(new RootDatabasesTreeNode(new Item()));
 * tree.getRoot().initChildNodes(connection, false);
 */
public class DatabaseLoader {
    private static final Logger LOGGER = Logger.getLogger(DatabaseLoader.class.getName());

    private final Connection connection;

    public DatabaseLoader(Connection connection) {
        this.connection = connection;
    }

    /**
     * Method to lazy load nodes from database.
     *
     * @param treeModel Current tree.
     * @param itemId    Item id from node to open.
     * @return List with loaded nodes.
     */
    public List<TreeNode> nodesLazyLoading(TreeModel treeModel, long itemId) {
        LOGGER.info("Lazy loading the node with item id = ".concat(String.valueOf(itemId)));

        TreeNode node = treeModel.getSearcher().find(treeModel.getRoot(), itemId);

        if (node != null) {
            node.initChildNodes(connection, true);
            return node.getChildTreeNodes();
        }

        return null;
    }
}

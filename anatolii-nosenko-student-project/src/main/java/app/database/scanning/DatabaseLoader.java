package app.database.scanning;

import app.structure.model.Item;
import app.structure.model.TreeModel;
import app.structure.model.TreeNode;
import app.structure.model.database.RootDatabasesTreeNode;
import app.structure.search.Searcher;
import java.sql.Connection;

/**
 * Class to load database structure to TreeModel object.
 */
public class DatabaseLoader {

    private final Connection connection;

    public DatabaseLoader(Connection connection) {
        this.connection = connection;
    }

    /**
     * Method creates tree and loads database structure into it.
     *
     * @param searcher Searcher implementation to create tree.
     * @return TreeModel with the database structure.
     */
    public TreeModel loadNodesInfoFromDB(Searcher searcher) {

        TreeModel tree = new TreeModel(searcher);
        tree.add(new RootDatabasesTreeNode(new Item()));
        tree.getRoot().initChildNodes(connection, false);

        return tree;
    }

    /**
     * Method to lazy load nodes from database.
     *
     * @param treeModel Current tree.
     * @param itemId    Item id from node to open.
     * @return Tree with opened specific node.
     */
    public TreeModel nodesLazyLoading(TreeModel treeModel, long itemId) {
        TreeNode node = treeModel.getSearcher().find(treeModel.getRoot(), itemId);
        node.initChildNodes(connection, true);
        return treeModel;
    }
}

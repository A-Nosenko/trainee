package app.database.scanning;

import app.structure.model.Item;
import app.structure.model.TreeModel;
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
}

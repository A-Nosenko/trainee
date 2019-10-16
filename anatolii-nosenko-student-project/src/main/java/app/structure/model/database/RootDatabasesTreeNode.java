package app.structure.model.database;

import static app.literals.Constants.DATABASES;
import static app.literals.Constants.DATABASE_NAME;
import app.database.query.QueryManager;
import app.structure.model.Item;
import app.structure.model.TreeNode;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;

/**
 * Root tree node for databases representation.
 */
public class RootDatabasesTreeNode extends DBTreeNode {

    public RootDatabasesTreeNode(Item item) {
        super(item);
        item.setTagName(DATABASES);
    }

    @Override
    List<TreeNode> fetchChildNodes(Connection connection) {
        List<TreeNode> treeNodes = new ArrayList<>();

        List<String> databasesList = QueryManager.getInstance().getDatabasesNames(connection);
        for (String databaseName : databasesList) {
            Item database = new Item();
            treeNodes.add(new DatabaseTreeNode(database));
            database.setAttribute(DATABASE_NAME, databaseName);
        }

        return treeNodes;
    }
}

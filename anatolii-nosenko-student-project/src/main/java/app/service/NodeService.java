package app.service;

import app.database.connection.ConnectionFactory;
import app.database.connection.Props;
import app.literals.Constants;
import app.structure.model.Item;
import app.structure.model.TreeModel;
import app.structure.model.TreeNode;
import app.structure.model.database.RootDatabasesTreeNode;
import app.structure.search.BreadthFirstSearcher;
import java.sql.Connection;
import java.util.List;
import org.springframework.stereotype.Service;

/**
 * Service to provide loaded nodes.
 */
@Service
public class NodeService {

    ///////////////////////////////////////////////////////////////////////////////////
    // Test configuration, till connection form not completed use default connection.
    ///////////////////////////////////////////////////////////////////////////////////
    private static Props props = new Props("jdbc:mysql://localhost:3306?useUnicode=true"
        + "&useJDBCCompliantTimezoneShift=true"
        + "&useLegacyDatetimeCode=false"
        + "&serverTimezone=UTC",
        "root",
        "root",
        "com.mysql.cj.jdbc.Driver");
    private static Connection connection = ConnectionFactory.getConnection(props);
    private static TreeModel treeModel = new TreeModel(new BreadthFirstSearcher());

    static {
        treeModel.add(new RootDatabasesTreeNode(new Item()));
    }
    ///////////////////////////////////////////////////////////////////////////////////

    /**
     * Method to load nodes.
     *
     * @param itemId Item id of node to load child nodes.
     * @return JSON array of child nodes.
     */
    public String loadChildNodes(long itemId) {
        StringBuilder builder = new StringBuilder();
        builder.append(Constants.START_JSON_ARRAY);
        TreeNode node = treeModel.getSearcher().find(treeModel.getRoot(), itemId);

        if (node != null) {
            List<TreeNode> treeNodes = node.getChildTreeNodes();
            if (treeNodes == null || treeNodes.isEmpty()) {
                treeNodes = node.initChildNodes(connection, true);
            }
            if (treeNodes != null && !treeNodes.isEmpty()) {
                for (TreeNode child : treeNodes) {
                    builder.append(child.toJSON());
                    builder.append(Constants.COMMA);
                }
                builder.replace(builder.length() - 2, builder.length(), "");
            }
        }

        builder.append(Constants.FINISH_JSON_ARRAY);
        return builder.toString();
    }
}

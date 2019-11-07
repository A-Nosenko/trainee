package app.service;

import app.literals.Constants;
import app.model.ConnectionHolder;
import app.model.TreeHolder;
import app.structure.model.TreeModel;
import app.structure.model.TreeNode;
import com.google.gson.Gson;
import java.sql.Connection;
import java.util.List;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Service to provide loaded nodes.
 */
@Service
public class NodeService {
    private static final Logger LOGGER = Logger.getLogger(NodeService.class.getName());

    @Autowired
    private TreeHolder treeHolder;

    @Autowired
    private ConnectionHolder connectionHolder;

    /**
     * Method to load nodes.
     *
     * @param itemId Item id of node to load child nodes.
     * @return JSON array of child nodes.
     */
    public String loadChildNodes(long itemId) {
        TreeModel treeModel = treeHolder.getTreeModel();
        Connection connection = connectionHolder.getConnection();
        String emptyResult = Constants.START_JSON_ARRAY.concat(Constants.FINISH_JSON_ARRAY);

        if (treeModel == null || connection == null) {
            LOGGER.error("Can't load child nodes! First initialise Connection and TreeModel via ConnectionController.");
            return emptyResult;
        }

        TreeNode node = treeModel.getSearcher().find(treeModel.getRoot(), itemId);

        if (node != null) {
            List<TreeNode> treeNodes = node.getChildTreeNodes();
            if (treeNodes == null || treeNodes.isEmpty()) {
                treeNodes = node.initChildNodes(connection, true);
            }

            Gson gson = new Gson();
            return gson.toJson(treeNodes);
        }

        return emptyResult;
    }
}

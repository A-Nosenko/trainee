package app.service;

import app.literals.Constants;
import app.model.ConnectionHolder;
import app.model.NodePostDtoResponse;
import app.model.TreeHolder;
import app.structure.model.TreeModel;
import app.structure.model.TreeNode;
import app.structure.model.base.node.BaseTreeNode;
import com.google.gson.Gson;
import java.sql.Connection;
import java.util.ArrayList;
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
        String emptyResult = Constants.START_JSON_ARRAY.concat(Constants.FINISH_JSON_ARRAY);

        if (treeModel == null) {
            LOGGER.error("Can't load child nodes! First initialise Connection and TreeModel via ConnectionController.");
            return emptyResult;
        }

        TreeNode node = treeModel.getSearcher().find(treeModel.getRoot(), itemId);

        if (node != null) {
            List<TreeNode> treeNodes = node.getChildTreeNodes();
            if (treeNodes.isEmpty()) {
                Connection connection = connectionHolder.getConnection();
                if (connection != null) {
                    treeNodes = node.initChildNodes(connection, true);
                }
            }

            List<NodePostDtoResponse> nodePostDtoResponses = new ArrayList<>();
            for (TreeNode treeNode : treeNodes) {
                nodePostDtoResponses.add(new NodePostDtoResponse(treeNode));
            }

            Gson gson = new Gson();
            return gson.toJson(nodePostDtoResponses);
        }

        return emptyResult;
    }
}

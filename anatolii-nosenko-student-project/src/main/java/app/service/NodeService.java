package app.service;

import app.literals.Constants;
import app.model.ConnectionHolder;
import app.model.NodePostDtoResponse;
import app.model.TreeHolder;
import app.structure.model.TreeModel;
import app.structure.model.TreeNode;
import com.google.gson.Gson;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
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

    public String loadAll() {
        treeHolder.getTreeModel().getRoot().initChildNodes(connectionHolder.getConnection(), false);

        LOGGER.info("== ALL NODES COLLECTED ==");

        return "== ALL NODES COLLECTED ==";
    }

    public String readAll() {
        Gson gson = new Gson();
        ArrayList<String> responses = new ArrayList<>();
        Queue<TreeNode> nodes = new LinkedList();
        nodes.offer(treeHolder.getTreeModel().getRoot());

        while (!nodes.isEmpty()) {
            TreeNode node = nodes.poll();
            if (node != null) {
                responses.add(gson.toJson(new NodePostDtoResponse(node)));
                node.getChildTreeNodes().forEach(nodes::offer);
            }
        }

        LOGGER.info("== SENDING ALL NODES ==");

        return gson.toJson(responses);
    }

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

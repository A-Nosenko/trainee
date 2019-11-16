package app.service;

import app.exception.AppException;
import app.file.work.TextWriter;
import app.literals.Constants;
import app.model.ConnectionPostDtoResponse;
import app.model.TreeHolder;
import app.parsing.xml.TreeLoaderFromXML;
import app.parsing.xml.TreeSaverToXML;
import app.structure.model.TreeModel;
import app.structure.model.TreeNode;
import app.structure.model.base.node.BaseTreeNode;
import app.structure.search.DepthFirstSearcher;
import com.google.gson.Gson;
import java.io.File;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Service to save tree in XML and vice versa.
 */
@Service
public class TreeSavingService {

    @Autowired
    private TreeHolder treeHolder;

    /**
     * Method to save tree in XML file.
     *
     * @return True, if saving completed without exceptions.
     */
    public boolean saveTree() {
        TreeModel treeModel = treeHolder.getTreeModel();
        if (treeModel == null) {
            return false;
        }
        TreeSaverToXML saverToXML = new TreeSaverToXML();
        String content = saverToXML.save(treeModel);
        try {
            new TextWriter().write(content, new File(Constants.TEMP_XML));
        } catch (AppException e) {
            return false;
        }

        return true;
    }

    /**
     * Method to load tree from XML file.
     *
     * @return Object with root tree node and message about tree source.
     */
    public ConnectionPostDtoResponse loadTree() {
        TreeLoaderFromXML loaderFromXML = new TreeLoaderFromXML();
        TreeModel treeModel = new TreeModel(new DepthFirstSearcher());

        try {
            loaderFromXML.load(new File(Constants.TEMP_XML), treeModel);
        } catch (AppException e) {
            return null;
        }

        treeHolder.setTreeModel(treeModel);

        // Transferring to frontend the root node with closed child nodes to reduce load of response.
        TreeNode temp = new BaseTreeNode(treeHolder.getTreeModel().getRoot().getItem());
        temp.setFinalNode(treeHolder.getTreeModel().getRoot().isFinalNode());
        temp.setReceivedFromXML(treeHolder.getTreeModel().getRoot().isReceivedFromXML());
        temp.setReceivedFromDatabase(treeHolder.getTreeModel().getRoot().isReceivedFromDatabase());

        Gson gson = new Gson();
        ConnectionPostDtoResponse response = new ConnectionPostDtoResponse();
        response.setRoot(gson.toJson(temp));
        response.setConnector(Constants.LOADED_FROM_XML_STATUS);
        return response;
    }
}

package app.structure.search;

import app.exception.AppException;
import app.structure.model.TreeNode;

/**
 * Searcher implementation with Depth first search algorithm.
 */
public class DepthFirstSearcher implements Searcher {
    private TreeNode result;

    @Override
    public TreeNode find(TreeNode treeNode, long itemId) {
        if (treeNode == null) {
            throw new AppException("Null treeNode!");
        }

        result = null;

        if (treeNode.getItem().getUniqueId() == itemId) {
            result = treeNode;
            return result;
        }

        for (TreeNode child : treeNode.getChildTreeNodes()) {
            if (result == null) {
                find(child, itemId);
            }
        }

        return result;
    }
}

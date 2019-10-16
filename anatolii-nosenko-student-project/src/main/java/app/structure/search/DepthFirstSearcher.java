package app.structure.search;

import app.structure.exception.AppException;
import app.structure.model.Item;
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
        System.out.print(treeNode.getItem().getUniqueId() + " => ");

        if (treeNode.getItem().getUniqueId() == itemId) {
            System.out.println();
            result = treeNode;
            return result;
        }

        for (TreeNode child : treeNode.getChildTreeNodes()) {
            find(child, itemId);
        }

        return result;
    }
}

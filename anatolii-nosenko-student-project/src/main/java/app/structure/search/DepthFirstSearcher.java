package app.structure.search;

import app.structure.model.Item;
import app.structure.model.TreeNode;

public class DepthFirstSearcher implements Searcher {
    private TreeNode result;

    @Override
    public TreeNode find(TreeNode treeNode, Item item) {
        if (treeNode == null) {
            return null;
        }
        result = null;
        System.out.print(treeNode.getItem().getUniqueId() + " => ");

        if (treeNode.getItem().equals(item)) {
            System.out.println();
            result = treeNode;
        }
        if (result != null) {
            return result;
        }
        for (TreeNode child : treeNode.getChildTreeNodes()) {
            if (result != null) {
                return result;
            }
            find(child, item);
        }
        return result;
    }
}

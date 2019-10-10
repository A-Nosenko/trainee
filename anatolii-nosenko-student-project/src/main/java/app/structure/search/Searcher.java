package app.structure.search;

import app.structure.model.Item;
import app.structure.model.TreeNode;

/**
 * Interface with method to move through tree.
 */
public interface Searcher {

    /**
     * Method to move through tree and search some item.
     * @param treeNode Node to start searching.
     * @param item Item to find.
     * @return Node with goal item.
     */
    TreeNode find(TreeNode treeNode, Item item);
}

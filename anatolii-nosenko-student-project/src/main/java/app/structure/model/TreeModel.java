package app.structure.model;

import app.literals.Constants;
import app.structure.search.Searcher;
import app.exception.AppException;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * Custom Tree structure.
 */
public class TreeModel {
    private final Map<String, String> declarationMap = new LinkedHashMap<>();
    private TreeNode root;
    private Searcher searcher;

    /**
     * Constructor applies realisation of Searcher to find nodes in the Tree.
     *
     * @param searcher Searcher implementation.
     */
    public TreeModel(Searcher searcher) {
        this.searcher = searcher;
        declarationMap.put(Constants.VERSION, Constants.DEFAULT_VERSION);
        declarationMap.put(Constants.ENCODING, Constants.DEFAULT_ENCODING);
    }

    /**
     * Recursive method to get tree root.
     *
     * @return Root node.
     */
    public TreeNode getRoot() {
        if (root != null) {
            return root.getRoot();
        }
        return null;
    }

    public Map<String, String> getDeclarationMap() {
        return declarationMap;
    }

    public void setSearcher(Searcher searcher) {
        this.searcher = searcher;
    }

    public Searcher getSearcher() {
        return searcher;
    }

    /**
     * Base node item establishment.
     *
     * @param treeNode Root tree node.
     */
    public void add(TreeNode treeNode) {
        root = treeNode;
    }

    /**
     * Method to add element as child tree node.
     *
     * @param parentId Parent node item id.
     * @param treeNode Node to add in tree.
     * @return True if tree contains node with parent item and addition was done. False if method can't find parent node.
     */
    public boolean add(long parentId, TreeNode treeNode) {
        if (treeNode == null) {
            return false;
        }

        TreeNode parent = searcher.find(root, parentId);

        if (parent == null || parent.getItem() == null) {
            return false;
        }

        return parent.add(treeNode);
    }

    /**
     * Method to remove node with specific item from tree.
     *
     * @param itemId Item id of node to remove.
     * @return true, if deletion completed, or false otherwise.
     */
    public boolean removeNode(long itemId) {
        if (root == null) {
            return false;
        } else if (root.getItem().getUniqueId() == itemId) {
            root = null;
            return true;
        }
        TreeNode node = searcher.find(root, itemId);
        if (node == null) {
            return false;
        }
        TreeNode parent = node.getParentTreeNode();
        return parent.remove(node);
    }

    @Override
    public String toString() {
        return root.toString();
    }
}

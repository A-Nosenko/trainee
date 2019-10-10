package app.structure.model;

import app.structure.search.Searcher;
import app.structure.exception.AppException;
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
     * @param searcher Searcher implementation.
     */
    public TreeModel(Searcher searcher) {
        this.searcher = searcher;
    }

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

    /**
     * Base node item establishment.
     * @param item Root node content.
     */
    public void add(Item item) {
        root = new TreeNode(item);
    }

    /**
     * Method to add element as child tree node.
     * @param parent  Parent node item.
     * @param current Item to add in tree.
     * @return True if tree contains node with parent item
     *         and addition was done. False if method can't find parent node.
     */
    public boolean add(Item parent, Item current) throws AppException {
        if (parent == null || current == null) {
            return false;
        }
        if (parent.getContent() != null) {
            throw new AppException("Content already assigned, can't add child element!");
        }
        TreeNode targetTreeNode = searcher.find(root, parent);
        if (targetTreeNode == null) {
            throw new AppException("Can't add child node, target is null!");
        }
        if (!targetTreeNode.add(new TreeNode(current))) {
            throw new AppException("Can't add child in target node with element "
                .concat(targetTreeNode.getItem().toString()));
        }
        return true;
    }

    public boolean remove(Item item) {
        if (root == null) {
            return false;
        } else if (root.getItem().equals(item)) {
            root = null;
            return true;
        }
        TreeNode node = searcher.find(root, item);
        TreeNode parent = node.getParentTreeNode();
        return parent.remove(node);
    }

    @Override
    public String toString() {
        return root.toString();
    }
}

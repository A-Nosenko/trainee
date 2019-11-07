package app.structure.model;

import app.literals.Constants;
import app.exception.AppException;
import java.util.ArrayList;
import java.util.List;

/**
 * Abstract node of the TreeModel.
 */
public abstract class TreeNode {
    private Item item;
    private TreeNode parentTreeNode;
    private final List<TreeNode> childTreeNodes;
    private boolean isFinalNode;

    protected TreeNode(Item item) {
        this.item = item;
        this.childTreeNodes = new ArrayList<>();
    }

    /**
     * Method to initialize child nodes.
     *
     * @param datasource         Source to fetch data, it may be Connection to database
     *                           or other datasource to fetch child nodes items.
     * @param lazyInitialisation Boolean flag to start initialize next child nodes.
     *                           if lazyInitialisation is true, next child nodes will not
     *                           be initialized.
     * @return List of child tree nodes.
     */
    public abstract List<TreeNode> initChildNodes(Object datasource, boolean lazyInitialisation);

    /**
     * Method to get tree root from any tree node.
     *
     * @return Tree root.
     */
    public TreeNode getRoot() {
        if (parentTreeNode == null) {
            return this;
        } else {
            return parentTreeNode.getRoot();
        }
    }

    boolean add(TreeNode treeNode) {
        if (item.getTagName() == null) {
            throw new AppException("Tag name didn't assigned. Can't add child node here, teg name must be assigned.");
        }

        treeNode.parentTreeNode = this;
        return childTreeNodes.add(treeNode);
    }

    boolean remove(TreeNode treeNode) {
        return childTreeNodes.remove(treeNode);
    }

    TreeNode getParentTreeNode() {
        return parentTreeNode;
    }

    public Item getItem() {
        return item;
    }

    public boolean isFinalNode() {
        return isFinalNode;
    }

    public void setFinalNode(boolean finalNode) {
        isFinalNode = finalNode;
    }

    public List<TreeNode> getChildTreeNodes() {
        return childTreeNodes;
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        if (item != null) {
            builder.append(item);
            builder.append(Constants.NEW_LINE);
        } else {
            builder.append(Constants.NULL_ELEMENT);
        }
        for (TreeNode next : childTreeNodes) {
            builder.append(next.toString());
        }
        return builder.toString();
    }
}

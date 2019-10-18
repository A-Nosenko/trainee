package app.structure.model;

import static app.literals.Constants.NEW_LINE;
import static app.literals.Constants.NULL_ELEMENT;
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

    public TreeNode(Item item) {
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
     */
    public abstract void initChildNodes(Object datasource, boolean lazyInitialisation);

    TreeNode getRoot() {
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
        if (item.getContent() != null) {
            throw new AppException("Item content already assigned. Can't add child node here, item must be without text content.");
        }
        treeNode.parentTreeNode = this;
        return childTreeNodes.add(treeNode);
    }

    boolean remove(TreeNode treeNode) {
        return childTreeNodes.remove(treeNode);
    }

    public TreeNode getParentTreeNode() {
        return parentTreeNode;
    }

    public Item getItem() {
        return item;
    }

    public void setItem(Item item) {
        this.item = item;
    }

    public List<TreeNode> getChildTreeNodes() {
        return childTreeNodes;
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        if (item != null) {
            builder.append(item);
            builder.append(NEW_LINE);
        } else {
            builder.append(NULL_ELEMENT);
        }
        for (TreeNode next : childTreeNodes) {
            if (next != null) {
                builder.append(next.toString());
            } else {
                builder.append(NULL_ELEMENT);
            }
        }
        return builder.toString();
    }
}

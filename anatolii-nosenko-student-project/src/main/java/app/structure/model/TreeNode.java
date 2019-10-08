package app.structure.model;

import static app.literals.Constants.NEW_LINE;
import static app.literals.Constants.NULL_ELEMENT;
import app.structure.exception.AppException;
import java.util.ArrayList;
import java.util.List;

public class TreeNode {
    private Item item;
    private TreeNode parentTreeNode;
    private final List<TreeNode> childTreeNodes;

    TreeNode(Item item) {
        this.item = item;
        this.childTreeNodes = new ArrayList<>();
    }

    TreeNode getRoot() {
        if (parentTreeNode == null) {
            return this;
        } else {
            return parentTreeNode.getRoot();
        }
    }

    boolean add(TreeNode treeNode) {
        if (item.getContent() != null) {
            throw new AppException("Item content already assigned. "
                + "Can't add child node here, item must be without text content.");
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

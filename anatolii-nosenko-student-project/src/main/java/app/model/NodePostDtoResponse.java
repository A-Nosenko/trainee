package app.model;

import app.literals.Constants;
import app.structure.model.Item;
import app.structure.model.TreeNode;
import app.structure.model.database.ColumnDatabaseTreeNode;
import app.structure.model.database.DatabaseTreeNode;
import app.structure.model.database.FunctionDatabaseTreeNode;
import app.structure.model.database.TableDatabaseTreeNode;
import app.structure.model.database.TriggerDatabaseTreeNode;

/**
 * Class to return on frontend the information about tree node.
 */
public class NodePostDtoResponse {
    private final Item item;
    private final TreeNode[] childTreeNodes = new TreeNode[0];
    private final boolean isFinalNode;
    private final boolean receivedFromDatabase;
    private final boolean receivedFromXML;

    /**
     * Constructor to wrap TreeNode class in NodePostDtoResponse.
     * Some addition parameters retrieves from item attributes and writes in
     * item tag name during wrapping.
     *
     * @param treeNode Node to return on frontend.
     */
    public NodePostDtoResponse(TreeNode treeNode) {
        this.item = treeNode.getItem();
        this.isFinalNode = treeNode.isFinalNode();
        this.receivedFromDatabase = treeNode.isReceivedFromDatabase();
        this.receivedFromXML = treeNode.isReceivedFromXML();

        if (treeNode instanceof ColumnDatabaseTreeNode) {
            item.setTagName(item.getTagName().concat(Constants.SPACE).concat(item.getAttribute(Constants.COLUMN_ATTRIBUTES[0])));
        } else if (treeNode instanceof DatabaseTreeNode) {
            item.setTagName(item.getTagName().concat(Constants.SPACE).concat(item.getAttribute(Constants.DATABASE_NAME)));
        } else if (treeNode instanceof FunctionDatabaseTreeNode) {
            item.setTagName(item.getTagName().concat(Constants.SPACE).concat(item.getAttribute(Constants.FUNCTION_ATTRIBUTES[0])));
        } else if (treeNode instanceof TableDatabaseTreeNode) {
            item.setTagName(item.getTagName().concat(Constants.SPACE).concat(item.getAttribute(Constants.TABLE_NAME)));
        } else if (treeNode instanceof TriggerDatabaseTreeNode) {
            item.setTagName(item.getTagName().concat(Constants.SPACE).concat(item.getAttribute(Constants.TRIGGER_ATTRIBUTES[2])));
        }
    }

    public Item getItem() {
        return item;
    }

    public TreeNode[] getChildTreeNodes() {
        return childTreeNodes;
    }

    public boolean isFinalNode() {
        return isFinalNode;
    }

    public boolean isReceivedFromDatabase() {
        return receivedFromDatabase;
    }

    public boolean isReceivedFromXML() {
        return receivedFromXML;
    }
}

package app.structure.model.database;

import app.literals.Constants;
import app.structure.model.Item;
import app.structure.model.TreeNode;
import java.sql.Connection;
import java.util.List;

public class ViewDatabaseTreeNode extends DBTreeNode {

    public ViewDatabaseTreeNode(Item item) {
        super(item);
        item.setTagName(Constants.VIEW);
        setFinalNode(true);
    }

    @Override
    List<TreeNode> fetchChildNodes(Connection connection) {
        return null;
    }
}

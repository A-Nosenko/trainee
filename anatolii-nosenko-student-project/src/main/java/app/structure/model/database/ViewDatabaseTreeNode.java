package app.structure.model.database;

import app.literals.Constants;
import app.structure.model.Item;
import app.structure.model.TreeNode;
import java.sql.Connection;
import java.util.List;

class ViewDatabaseTreeNode extends DBTreeNode {

    ViewDatabaseTreeNode(Item item) {
        super(item);
        item.setTagName(Constants.VIEW);
    }

    @Override
    List<TreeNode> fetchChildNodes(Connection connection) {
        return null;
    }
}

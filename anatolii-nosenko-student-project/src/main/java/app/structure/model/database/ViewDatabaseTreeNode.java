package app.structure.model.database;

import static app.literals.Constants.VIEW;
import app.structure.model.Item;
import app.structure.model.TreeNode;
import java.sql.Connection;
import java.util.List;

class ViewDatabaseTreeNode extends DBTreeNode {

    ViewDatabaseTreeNode(Item item) {
        super(item);
        item.setTagName(VIEW);
    }

    @Override
    List<TreeNode> fetchChildNodes(Connection connection) {
        return null;
    }
}

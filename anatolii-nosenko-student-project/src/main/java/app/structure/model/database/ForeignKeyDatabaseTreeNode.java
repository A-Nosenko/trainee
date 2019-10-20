package app.structure.model.database;

import app.literals.Constants;
import app.structure.model.Item;
import app.structure.model.TreeNode;
import java.sql.Connection;
import java.util.List;

class ForeignKeyDatabaseTreeNode extends DBTreeNode {

    ForeignKeyDatabaseTreeNode(Item item) {
        super(item);
        item.setTagName(Constants.FOREIGN_KEY);
    }

    @Override
    List<TreeNode> fetchChildNodes(Connection connection) {
        return null;
    }
}

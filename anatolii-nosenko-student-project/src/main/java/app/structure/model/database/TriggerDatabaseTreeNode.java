package app.structure.model.database;

import app.literals.Constants;
import app.structure.model.Item;
import app.structure.model.TreeNode;
import java.sql.Connection;
import java.util.List;

class TriggerDatabaseTreeNode extends DBTreeNode {

    TriggerDatabaseTreeNode(Item item) {
        super(item);
        item.setTagName(Constants.TRIGGER);
    }

    @Override
    List<TreeNode> fetchChildNodes(Connection connection) {
        return null;
    }
}

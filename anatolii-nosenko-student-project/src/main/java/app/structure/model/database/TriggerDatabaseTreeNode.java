package app.structure.model.database;

import static app.literals.Constants.TRIGGER;
import app.structure.model.Item;
import app.structure.model.TreeNode;
import java.sql.Connection;
import java.util.List;

class TriggerDatabaseTreeNode extends DBTreeNode {

    public TriggerDatabaseTreeNode(Item item) {
        super(item);
        item.setTagName(TRIGGER);
    }

    @Override
    List<TreeNode> fetchChildNodes(Connection connection) {
        return null;
    }
}

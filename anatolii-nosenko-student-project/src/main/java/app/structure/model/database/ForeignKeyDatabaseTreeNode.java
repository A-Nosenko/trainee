package app.structure.model.database;

import static app.literals.Constants.FOREIGN_KEY;
import app.structure.model.Item;
import app.structure.model.TreeNode;
import java.sql.Connection;
import java.util.List;

class ForeignKeyDatabaseTreeNode extends DBTreeNode {

    ForeignKeyDatabaseTreeNode(Item item) {
        super(item);
        item.setTagName(FOREIGN_KEY);
    }

    @Override
    List<TreeNode> fetchChildNodes(Connection connection) {
        return null;
    }
}

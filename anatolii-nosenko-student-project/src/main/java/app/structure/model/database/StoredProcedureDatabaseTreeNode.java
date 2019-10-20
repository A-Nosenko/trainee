package app.structure.model.database;

import app.literals.Constants;
import app.structure.model.Item;
import app.structure.model.TreeNode;
import java.sql.Connection;
import java.util.List;

class StoredProcedureDatabaseTreeNode extends DBTreeNode {

    StoredProcedureDatabaseTreeNode(Item item) {
        super(item);
        item.setTagName(Constants.STORED_PROCEDURE);
    }

    @Override
    List<TreeNode> fetchChildNodes(Connection connection) {
        return null;
    }
}

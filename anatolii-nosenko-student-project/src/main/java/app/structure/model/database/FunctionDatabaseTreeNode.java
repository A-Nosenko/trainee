package app.structure.model.database;

import static app.literals.Constants.FUNCTION;
import app.structure.model.Item;
import app.structure.model.TreeNode;
import java.sql.Connection;
import java.util.List;

class FunctionDatabaseTreeNode extends DBTreeNode {

    FunctionDatabaseTreeNode(Item item) {
        super(item);
        item.setTagName(FUNCTION);
    }

    @Override
    List<TreeNode> fetchChildNodes(Connection connection) {
        return null;
    }
}

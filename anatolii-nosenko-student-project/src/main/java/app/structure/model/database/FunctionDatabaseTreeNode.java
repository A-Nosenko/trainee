package app.structure.model.database;

import app.literals.Constants;
import app.structure.model.Item;
import app.structure.model.TreeNode;
import java.sql.Connection;
import java.util.List;

public class FunctionDatabaseTreeNode extends DBTreeNode {

    public FunctionDatabaseTreeNode(Item item) {
        super(item);
        item.setTagName(Constants.FUNCTION);
    }

    @Override
    List<TreeNode> fetchChildNodes(Connection connection) {
        return null;
    }
}

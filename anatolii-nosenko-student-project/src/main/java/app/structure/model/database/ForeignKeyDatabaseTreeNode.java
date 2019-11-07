package app.structure.model.database;

import app.literals.Constants;
import app.structure.model.Item;
import app.structure.model.TreeNode;
import java.sql.Connection;
import java.util.List;

public class ForeignKeyDatabaseTreeNode extends DBTreeNode {

    public ForeignKeyDatabaseTreeNode(Item item) {
        super(item);
        item.setTagName(Constants.FOREIGN_KEY);
        setFinalNode(true);
    }

    @Override
    List<TreeNode> fetchChildNodes(Connection connection) {
        return null;
    }
}

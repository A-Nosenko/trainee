package app.structure.model.database;

import app.literals.Constants;
import app.database.query.QueryManager;
import app.structure.model.Item;
import app.structure.model.TreeNode;
import java.sql.Connection;
import java.util.Collections;
import java.util.List;
import java.util.Map;

public class ColumnDatabaseTreeNode extends DBTreeNode {

    public ColumnDatabaseTreeNode(Item item) {
        super(item);
        item.setTagName(Constants.COLUMN);
        setFinalNode(true);
    }

    @Override
    List<TreeNode> fetchChildNodes(Connection connection) {
        Map<String, String> attributes = QueryManager
            .getInstance()
            .getForeignKeyAttributes(
                getItem().getAttribute(Constants.DATABASE_NAME), getItem().getAttribute(Constants.TABLE_NAME),
                getItem().getAttribute(Constants.COLUMN_ATTRIBUTES[0]), connection);

        Item foreignKeyItem = new Item();
        for (Map.Entry<String, String> entry : attributes.entrySet()) {
            foreignKeyItem.setAttribute(entry.getKey(), entry.getValue());
        }

        return Collections.singletonList(new ForeignKeyDatabaseTreeNode(foreignKeyItem));
    }
}

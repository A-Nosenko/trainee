package app.structure.model.database;

import static app.literals.Constants.COLUMN;
import static app.literals.Constants.COLUMN_ATTRIBUTES;
import static app.literals.Constants.DATABASE_NAME;
import static app.literals.Constants.TABLE_NAME;
import app.database.query.QueryManager;
import app.structure.model.Item;
import app.structure.model.TreeNode;
import java.sql.Connection;
import java.util.Collections;
import java.util.List;
import java.util.Map;

class ColumnDatabaseTreeNode extends DBTreeNode {

    ColumnDatabaseTreeNode(Item item) {
        super(item);
        item.setTagName(COLUMN);
    }

    @Override
    List<TreeNode> fetchChildNodes(Connection connection) {
        Map<String, String> attributes = QueryManager
            .getInstance()
            .getForeignKeyAttributes(
                getItem().getAttribute(DATABASE_NAME), getItem().getAttribute(TABLE_NAME),
                getItem().getAttribute(COLUMN_ATTRIBUTES[0]), connection);

        if (attributes == null) {
            return null;
        }

        Item foreignKeyItem = new Item();
        for (Map.Entry<String, String> entry : attributes.entrySet()) {
            foreignKeyItem.setAttribute(entry.getKey(), entry.getValue());
        }

        return Collections.singletonList(new ForeignKeyDatabaseTreeNode(foreignKeyItem));
    }
}

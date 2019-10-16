package app.structure.model.database;

import static app.literals.Constants.COLUMNS;
import static app.literals.Constants.DATABASE_NAME;
import static app.literals.Constants.TABLE_NAME;
import app.database.query.QueryManager;
import app.structure.model.Item;
import app.structure.model.TreeNode;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

class ColumnsDatabaseTreeNode extends DBTreeNode {

    ColumnsDatabaseTreeNode(Item item) {
        super(item);
        item.setTagName(COLUMNS);
    }

    @Override
    List<TreeNode> fetchChildNodes(Connection connection) {
        List<TreeNode> treeNodes = new ArrayList<>();
        String databaseName = getItem().getAttribute(DATABASE_NAME);
        String tableName = getItem().getAttribute(TABLE_NAME);
        List<Map<String, String>> columnsList = QueryManager
            .getInstance()
            .getColumnAttributes(databaseName, tableName, connection);
        for (Map<String, String> columnAttributes : columnsList) {
            Item column = new Item();
            column.setAttribute(DATABASE_NAME, databaseName);
            column.setAttribute(TABLE_NAME, tableName);
            for (Map.Entry<String, String> attributes : columnAttributes.entrySet()) {
                column.setAttribute(attributes.getKey(), attributes.getValue());
            }
            treeNodes.add(new ColumnDatabaseTreeNode(column));
        }

        return treeNodes;
    }
}

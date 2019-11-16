package app.structure.model.database;

import app.literals.Constants;
import app.database.query.QueryManager;
import app.structure.model.Item;
import app.structure.model.TreeNode;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class ColumnsDatabaseTreeNode extends DBTreeNode {

    public ColumnsDatabaseTreeNode(Item item) {
        super(item);
        item.setTagName(Constants.COLUMNS);
    }

    @Override
    List<TreeNode> fetchChildNodes(Connection connection) {
        List<TreeNode> treeNodes = new ArrayList<>();
        String databaseName = getItem().getAttribute(Constants.DATABASE_NAME);
        String tableName = getItem().getAttribute(Constants.TABLE_NAME);
        List<Map<String, String>> columnsList = QueryManager
            .getInstance()
            .getColumnAttributes(databaseName, tableName, connection);
        for (Map<String, String> columnAttributes : columnsList) {
            Item column = new Item();
            column.setAttribute(Constants.DATABASE_NAME, databaseName);
            column.setAttribute(Constants.TABLE_NAME, tableName);
            for (Map.Entry<String, String> attributes : columnAttributes.entrySet()) {
                column.setAttribute(attributes.getKey(), attributes.getValue());
            }
            treeNodes.add(new ColumnDatabaseTreeNode(column));
        }

        return treeNodes;
    }
}

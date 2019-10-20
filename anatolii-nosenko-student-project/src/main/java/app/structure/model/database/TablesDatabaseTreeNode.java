package app.structure.model.database;

import app.literals.Constants;
import app.database.query.QueryManager;
import app.structure.model.Item;
import app.structure.model.TreeNode;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;

class TablesDatabaseTreeNode extends DBTreeNode {

    TablesDatabaseTreeNode(Item item) {
        super(item);
        item.setTagName(Constants.TABLES);
    }

    @Override
    List<TreeNode> fetchChildNodes(Connection connection) {
        List<TreeNode> treeNodes = new ArrayList<>();

        String databaseName = getItem().getAttribute(Constants.DATABASE_NAME);
        List<String> tablesNames = QueryManager
            .getInstance()
            .getTablesNames(databaseName, connection);
        for (String tableName : tablesNames) {
            Item table = new Item();
            table.setAttribute(Constants.TABLE_NAME, tableName);
            table.setAttribute(Constants.DATABASE_NAME, databaseName);
            table.setAttribute(Constants.LAST_INSERT_ID,
                String.valueOf(QueryManager
                    .getInstance()
                    .getLastInsertId(databaseName, tableName, connection)));
            treeNodes.add(new TableDatabaseTreeNode(table));
        }

        return treeNodes;
    }
}

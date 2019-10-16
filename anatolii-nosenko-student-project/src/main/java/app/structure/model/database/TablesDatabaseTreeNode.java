package app.structure.model.database;

import static app.literals.Constants.DATABASE_NAME;
import static app.literals.Constants.LAST_INSERT_ID;
import static app.literals.Constants.TABLES;
import static app.literals.Constants.TABLE_NAME;
import app.database.query.QueryManager;
import app.structure.model.Item;
import app.structure.model.TreeNode;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;

class TablesDatabaseTreeNode extends DBTreeNode {

    TablesDatabaseTreeNode(Item item) {
        super(item);
        item.setTagName(TABLES);
    }

    @Override
    List<TreeNode> fetchChildNodes(Connection connection) {
        List<TreeNode> treeNodes = new ArrayList<>();

        String databaseName = getItem().getAttribute(DATABASE_NAME);
        List<String> tablesNames = QueryManager
            .getInstance()
            .getTablesNames(databaseName, connection);
        for (String tableName : tablesNames) {
            Item table = new Item();
            table.setAttribute(TABLE_NAME, tableName);
            table.setAttribute(DATABASE_NAME, databaseName);
            table.setAttribute(LAST_INSERT_ID,
                String.valueOf(QueryManager
                    .getInstance()
                    .getLastInsertId(databaseName, tableName, connection)));
            treeNodes.add(new TableDatabaseTreeNode(table));
        }

        return treeNodes;
    }
}

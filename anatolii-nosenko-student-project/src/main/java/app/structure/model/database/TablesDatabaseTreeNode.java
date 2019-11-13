package app.structure.model.database;

import app.database.query.DDL;
import app.literals.Constants;
import app.database.query.QueryManager;
import app.structure.model.Item;
import app.structure.model.TreeNode;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;

public class TablesDatabaseTreeNode extends DBTreeNode {

    public TablesDatabaseTreeNode(Item item) {
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
            table.setTagName(table.getTagName().concat(" ").concat(tableName));
            table.setAttribute(Constants.DDL, QueryManager
                .getInstance()
                .getDDL(databaseName, DDL.TABLE, tableName, connection));
        }

        return treeNodes;
    }
}

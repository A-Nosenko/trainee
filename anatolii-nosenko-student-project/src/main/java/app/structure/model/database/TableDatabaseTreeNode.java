package app.structure.model.database;

import static app.literals.Constants.DATABASE_NAME;
import static app.literals.Constants.TABLE;
import static app.literals.Constants.TABLE_NAME;
import app.structure.model.Item;
import app.structure.model.TreeNode;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;

class TableDatabaseTreeNode extends DBTreeNode {

    TableDatabaseTreeNode(Item item) {
        super(item);
        item.setTagName(TABLE);
    }

    @Override
    List<TreeNode> fetchChildNodes(Connection connection) {
        List<TreeNode> treeNodes = new ArrayList<>();

        String databaseName = getItem().getAttribute(DATABASE_NAME);
        String tableName = getItem().getAttribute(TABLE_NAME);

        Item columns = new Item();
        columns.setAttribute(DATABASE_NAME, databaseName);
        columns.setAttribute(TABLE_NAME, tableName);
        treeNodes.add(new ColumnsDatabaseTreeNode(columns));

        Item ddl = new Item();
        ddl.setAttribute(TABLE_NAME, tableName);
        treeNodes.add(new TableDDLDatabaseTreeNode(ddl));

        Item viewsItem = new Item();
        viewsItem.setAttribute(TABLE_NAME, tableName);
        treeNodes.add(new ViewsDatabaseTreeNode(viewsItem));

        Item triggersItem = new Item();
        triggersItem.setAttribute(DATABASE_NAME, databaseName);
        triggersItem.setAttribute(TABLE_NAME, tableName);
        treeNodes.add(new TriggersDatabaseTreeNode(triggersItem));

        return treeNodes;
    }
}

package app.structure.model.database;

import app.literals.Constants;
import app.structure.model.Item;
import app.structure.model.TreeNode;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;

public class TableDatabaseTreeNode extends DBTreeNode {

    public TableDatabaseTreeNode(Item item) {
        super(item);
        item.setTagName(Constants.TABLE);
    }

    @Override
    List<TreeNode> fetchChildNodes(Connection connection) {
        List<TreeNode> treeNodes = new ArrayList<>();

        String databaseName = getItem().getAttribute(Constants.DATABASE_NAME);
        String tableName = getItem().getAttribute(Constants.TABLE_NAME);

        Item columns = new Item();
        columns.setAttribute(Constants.DATABASE_NAME, databaseName);
        columns.setAttribute(Constants.TABLE_NAME, tableName);
        treeNodes.add(new ColumnsDatabaseTreeNode(columns));

        Item viewsItem = new Item();
        viewsItem.setAttribute(Constants.TABLE_NAME, tableName);
        treeNodes.add(new ViewsDatabaseTreeNode(viewsItem));

        Item triggersItem = new Item();
        triggersItem.setAttribute(Constants.DATABASE_NAME, databaseName);
        triggersItem.setAttribute(Constants.TABLE_NAME, tableName);
        treeNodes.add(new TriggersDatabaseTreeNode(triggersItem));

        return treeNodes;
    }
}

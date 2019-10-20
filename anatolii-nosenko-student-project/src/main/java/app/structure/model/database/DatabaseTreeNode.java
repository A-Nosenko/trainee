package app.structure.model.database;

import app.literals.Constants;
import app.structure.model.Item;
import app.structure.model.TreeNode;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;

class DatabaseTreeNode extends DBTreeNode {

    DatabaseTreeNode(Item item) {
        super(item);
        item.setTagName(Constants.DATABASE);
    }

    @Override
    public List<TreeNode> fetchChildNodes(Connection connection) {
        List<TreeNode> treeNodes = new ArrayList<>();

        String databaseName = getItem().getAttribute(Constants.DATABASE_NAME);

        Item functionsItem = new Item();
        functionsItem.setAttribute(Constants.DATABASE_NAME, databaseName);
        treeNodes.add(new FunctionsDatabaseTreeNode(functionsItem));

        Item storedProceduresItem = new Item();
        storedProceduresItem.setAttribute(Constants.DATABASE_NAME, databaseName);
        treeNodes.add(new StoredProceduresDatabaseTreeNode(storedProceduresItem));

        Item tablesItem = new Item();
        tablesItem.setAttribute(Constants.DATABASE_NAME, databaseName);
        treeNodes.add(new TablesDatabaseTreeNode(tablesItem));

        return treeNodes;
    }
}

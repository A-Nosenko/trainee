package app.structure.model.database;

import static app.literals.Constants.DATABASE;
import static app.literals.Constants.DATABASE_NAME;
import app.structure.model.Item;
import app.structure.model.TreeNode;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;

class DatabaseTreeNode extends DBTreeNode {

    DatabaseTreeNode(Item item) {
        super(item);
        item.setTagName(DATABASE);
    }

    @Override
    public List<TreeNode> fetchChildNodes(Connection connection) {
        List<TreeNode> treeNodes = new ArrayList<>();

        String databaseName = getItem().getAttribute(DATABASE_NAME);

        Item functionsItem = new Item();
        functionsItem.setAttribute(DATABASE_NAME, databaseName);
        treeNodes.add(new FunctionsDatabaseTreeNode(functionsItem));

        Item storedProceduresItem = new Item();
        storedProceduresItem.setAttribute(DATABASE_NAME, databaseName);
        treeNodes.add(new StoredProceduresDatabaseTreeNode(storedProceduresItem));

        Item tablesItem = new Item();
        tablesItem.setAttribute(DATABASE_NAME, databaseName);
        treeNodes.add(new TablesDatabaseTreeNode(tablesItem));

        return treeNodes;
    }
}

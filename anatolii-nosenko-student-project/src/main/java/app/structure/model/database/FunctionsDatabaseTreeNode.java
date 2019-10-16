package app.structure.model.database;

import static app.literals.Constants.DATABASE_NAME;
import static app.literals.Constants.FUNCTIONS;
import app.database.query.QueryManager;
import app.structure.model.Item;
import app.structure.model.TreeNode;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

class FunctionsDatabaseTreeNode extends DBTreeNode {

    FunctionsDatabaseTreeNode(Item item) {
        super(item);
        item.setTagName(FUNCTIONS);
    }

    @Override
    List<TreeNode> fetchChildNodes(Connection connection) {
        List<TreeNode> treeNodes = new ArrayList<>();
        List<Map<String, String>> functions = QueryManager
            .getInstance()
            .getDatabaseFunctions(getItem().getAttribute(DATABASE_NAME), connection);
        for (Map<String, String> function : functions) {
            Item functionItem = new Item();
            for (Map.Entry<String, String> entry : function.entrySet()) {
                functionItem.setAttribute(entry.getKey(), entry.getValue());
            }
            treeNodes.add(new FunctionDatabaseTreeNode(functionItem));
        }
        return treeNodes;
    }
}

package app.structure.model.database;

import app.literals.Constants;
import app.database.query.QueryManager;
import app.structure.model.Item;
import app.structure.model.TreeNode;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class FunctionsDatabaseTreeNode extends DBTreeNode {

    public FunctionsDatabaseTreeNode(Item item) {
        super(item);
        item.setTagName(Constants.FUNCTIONS);
    }

    @Override
    List<TreeNode> fetchChildNodes(Connection connection) {
        List<TreeNode> treeNodes = new ArrayList<>();
        List<Map<String, String>> functions = QueryManager
            .getInstance()
            .getDatabaseFunctions(getItem().getAttribute(Constants.DATABASE_NAME), connection);
        for (Map<String, String> function : functions) {
            Item functionItem = new Item();
            for (Map.Entry<String, String> entry : function.entrySet()) {
                if (entry.getKey().equals(Constants.FUNCTION_ATTRIBUTES[15])) {
                    functionItem.setAttribute(Constants.DDL, entry.getValue());
                } else {
                    functionItem.setAttribute(entry.getKey(), entry.getValue());
                }
            }
            treeNodes.add(new FunctionDatabaseTreeNode(functionItem));
            functionItem.setTagName(functionItem.getTagName().concat(" ").concat(function.get(Constants.FUNCTION_ATTRIBUTES[0])));
        }
        return treeNodes;
    }
}

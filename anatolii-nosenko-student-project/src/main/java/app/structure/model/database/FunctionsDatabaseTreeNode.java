package app.structure.model.database;

import app.database.query.DDL;
import app.database.query.QueryManager;
import app.exception.AppException;
import app.literals.Constants;
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
                functionItem.setAttribute(entry.getKey(), entry.getValue());
            }
            try {
                functionItem.setAttribute(Constants.DDL, QueryManager
                    .getInstance()
                    .getDDL(
                        getItem().getAttribute(Constants.DATABASE_NAME), DDL.FUNCTION,
                        functionItem.getAttribute(Constants.FUNCTION_ATTRIBUTES[3]), connection
                    )
                );
            } catch (AppException e) {
                continue;
            }
            treeNodes.add(new FunctionDatabaseTreeNode(functionItem));
        }
        return treeNodes;
    }
}

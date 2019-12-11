package app.structure.model.database;

import app.literals.Constants;
import app.database.query.QueryManager;
import app.structure.model.Item;
import app.structure.model.TreeNode;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class ViewsDatabaseTreeNode extends DBTreeNode {

    public ViewsDatabaseTreeNode(Item item) {
        super(item);
        item.setTagName(Constants.VIEWS);
    }

    @Override
    List<TreeNode> fetchChildNodes(Connection connection) {
        List<TreeNode> treeNodes = new ArrayList<>();
        List<Map<String, String>> views = QueryManager
            .getInstance()
            .getTableViews(getItem().getAttribute(Constants.TABLE_NAME), connection);
        for (Map<String, String> view : views) {
            Item viewItem = new Item();
            for (Map.Entry<String, String> entry : view.entrySet()) {
                if (entry.getKey().equals(Constants.VIEW_ATTRIBUTES[3])) {
                    viewItem.setAttribute(Constants.DDL, Constants.VIEW_DDL_PREFIX.concat(entry.getValue()));
                } else {
                    viewItem.setAttribute(entry.getKey(), entry.getValue());
                }
            }
            treeNodes.add(new ViewDatabaseTreeNode(viewItem));
        }
        return treeNodes;
    }
}

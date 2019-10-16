package app.structure.model.database;

import static app.literals.Constants.TABLE_NAME;
import static app.literals.Constants.VIEWS;
import app.database.query.QueryManager;
import app.structure.model.Item;
import app.structure.model.TreeNode;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

class ViewsDatabaseTreeNode extends DBTreeNode {

    ViewsDatabaseTreeNode(Item item) {
        super(item);
        item.setTagName(VIEWS);
    }

    @Override
    List<TreeNode> fetchChildNodes(Connection connection) {
        List<TreeNode> treeNodes = new ArrayList<>();
        List<Map<String, String>> views = QueryManager
            .getInstance()
            .getTableViews(getItem().getAttribute(TABLE_NAME), connection);
        for (Map<String, String> view : views) {
            Item viewItem = new Item();
            for (Map.Entry<String, String> entry : view.entrySet()) {
                viewItem.setAttribute(entry.getKey(), entry.getValue());
            }
            treeNodes.add(new ViewDatabaseTreeNode(viewItem));
        }
        return treeNodes;
    }
}

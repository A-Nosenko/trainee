package app.structure.model.database;

import app.literals.Constants;
import app.database.query.QueryManager;
import app.structure.model.Item;
import app.structure.model.TreeNode;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

class TriggersDatabaseTreeNode extends DBTreeNode {

    TriggersDatabaseTreeNode(Item item) {
        super(item);
        item.setTagName(Constants.TRIGGERS);
    }

    @Override
    List<TreeNode> fetchChildNodes(Connection connection) {
        List<Map<String, String>> triggersMap = QueryManager
            .getInstance()
            .getTriggersAttributes(
                getItem().getAttribute(Constants.DATABASE_NAME),
                getItem().getAttribute(Constants.TABLE_NAME),
                connection);
        if (triggersMap.isEmpty()) {
            return null;
        }
        List<TreeNode> treeNodes = new ArrayList<>();
        for (Map<String, String> triggerMap : triggersMap) {
            Item triggerItem = new Item();
            for (Map.Entry<String, String> triggerAttributes : triggerMap.entrySet()) {
                triggerItem.setAttribute(triggerAttributes.getKey(), triggerAttributes.getValue());
            }
            treeNodes.add(new TriggerDatabaseTreeNode(triggerItem));
        }

        return treeNodes;
    }
}
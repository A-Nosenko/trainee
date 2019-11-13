package app.structure.model.database;

import app.database.query.DDL;
import app.literals.Constants;
import app.database.query.QueryManager;
import app.structure.model.Item;
import app.structure.model.TreeNode;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class TriggersDatabaseTreeNode extends DBTreeNode {

    public TriggersDatabaseTreeNode(Item item) {
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
            triggerItem.setAttribute(Constants.DDL,
                QueryManager
                    .getInstance()
                    .getDDL(getItem().getAttribute(Constants.DATABASE_NAME),
                        DDL.TRIGGER,
                        triggerMap.get(Constants.TRIGGER_ATTRIBUTES[2]), connection)
            );
            treeNodes.add(new TriggerDatabaseTreeNode(triggerItem));
            triggerItem.setTagName(triggerItem.getTagName().concat(" ").concat(triggerMap.get(Constants.TRIGGER_ATTRIBUTES[2])));
        }

        return treeNodes;
    }
}

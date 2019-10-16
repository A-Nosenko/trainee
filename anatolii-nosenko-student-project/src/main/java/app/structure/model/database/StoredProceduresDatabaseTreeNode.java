package app.structure.model.database;

import static app.literals.Constants.DATABASE_NAME;
import static app.literals.Constants.STORED_PROCEDURES;
import app.database.query.QueryManager;
import app.structure.model.Item;
import app.structure.model.TreeNode;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

class StoredProceduresDatabaseTreeNode extends DBTreeNode {

    StoredProceduresDatabaseTreeNode(Item item) {
        super(item);
        item.setTagName(STORED_PROCEDURES);
    }

    @Override
    List<TreeNode> fetchChildNodes(Connection connection) {
        List<TreeNode> treeNodes = new ArrayList<>();
        List<Map<String, String>> procedures = QueryManager
            .getInstance()
            .getDatabaseProcedures(getItem().getAttribute(DATABASE_NAME), connection);
        for (Map<String, String> procedure : procedures) {
            Item procedureItem = new Item();
            for (Map.Entry<String, String> entry : procedure.entrySet()) {
                procedureItem.setAttribute(entry.getKey(), entry.getValue());
            }
            treeNodes.add(new StoredProcedureDatabaseTreeNode(procedureItem));
        }
        return treeNodes;
    }
}

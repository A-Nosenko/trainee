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

public class StoredProceduresDatabaseTreeNode extends DBTreeNode {

    public StoredProceduresDatabaseTreeNode(Item item) {
        super(item);
        item.setTagName(Constants.STORED_PROCEDURES);
    }

    @Override
    List<TreeNode> fetchChildNodes(Connection connection) {
        List<TreeNode> treeNodes = new ArrayList<>();
        List<Map<String, String>> procedures = QueryManager
            .getInstance()
            .getDatabaseStoredProcedures(getItem().getAttribute(Constants.DATABASE_NAME), connection);
        for (Map<String, String> procedure : procedures) {
            Item procedureItem = new Item();
            for (Map.Entry<String, String> entry : procedure.entrySet()) {
                procedureItem.setAttribute(entry.getKey(), entry.getValue());
            }
            procedureItem.setAttribute(Constants.DDL,
                QueryManager
                    .getInstance()
                    .getDDL(getItem().getAttribute(Constants.DATABASE_NAME),
                        DDL.PROCEDURE, procedure.get(Constants.STORED_PROCEDURE_ATTRIBUTES[1]),
                        connection)
            );
            treeNodes.add(new StoredProcedureDatabaseTreeNode(procedureItem));
            procedureItem.setTagName(
                procedureItem.getTagName()
                    .concat(" ")
                    .concat(procedure.get(Constants.STORED_PROCEDURE_ATTRIBUTES[1])));
        }
        return treeNodes;
    }
}

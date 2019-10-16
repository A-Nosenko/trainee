package app.structure.model.database;

import static app.literals.Constants.DDL;
import static app.literals.Constants.TABLE_NAME;
import app.database.query.QueryManager;
import app.structure.model.Item;
import app.structure.model.TreeNode;
import app.structure.model.base_node.TreeNodeBase;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;

class TableDDLDatabaseTreeNode extends DBTreeNode {

    TableDDLDatabaseTreeNode(Item item) {
        super(item);
        item.setTagName(DDL);
    }

    @Override
    List<TreeNode> fetchChildNodes(Connection connection) {
        List<TreeNode> treeNodes = new ArrayList<>();
        Item ddlContent = new Item();
        ddlContent.setContent(QueryManager.getInstance().getTableDDL(getItem().getAttribute(TABLE_NAME), connection));
        treeNodes.add(new TreeNodeBase(ddlContent));
        return treeNodes;
    }
}

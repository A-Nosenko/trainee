package app.structure.model.database;

import app.literals.Constants;
import app.database.query.QueryManager;
import app.structure.model.Item;
import app.structure.model.TreeNode;
import app.structure.model.base.node.BaseTreeNode;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;

class TableDDLDatabaseTreeNode extends DBTreeNode {

    TableDDLDatabaseTreeNode(Item item) {
        super(item);
        item.setTagName(Constants.DDL);
    }

    @Override
    List<TreeNode> fetchChildNodes(Connection connection) {
        List<TreeNode> treeNodes = new ArrayList<>();
        Item ddlContent = new Item();
        ddlContent.setContent(QueryManager
            .getInstance()
            .getTableDDL(getItem()
                .getAttribute(Constants.DATABASE_NAME), getItem().getAttribute(Constants.TABLE_NAME), connection));
        treeNodes.add(new BaseTreeNode(ddlContent));
        return treeNodes;
    }
}

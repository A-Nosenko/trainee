package app.structure.model.database;

import app.exception.AppException;
import app.structure.model.Item;
import app.structure.model.TreeNode;
import java.sql.Connection;
import java.util.List;

/**
 * Tree node to be fetched from database.
 */
abstract class DBTreeNode extends TreeNode {

    DBTreeNode(Item item) {
        super(item);
    }

    @Override
    public List<TreeNode> initChildNodes(Object datasource, boolean lazyInitialisation) {

        if (!(datasource instanceof Connection)) {
            throw new AppException("Can't fetch child tree nodes, connection expected.");
        }

        List<TreeNode> nextNodes = fetchChildNodes((Connection) datasource);
        if (nextNodes != null) {
            this.getChildTreeNodes().addAll(fetchChildNodes((Connection) datasource));
        }

        if (!lazyInitialisation) {
            for (TreeNode treeNode : this.getChildTreeNodes()) {
                treeNode.initChildNodes(datasource, false);
            }
        }

        return this.getChildTreeNodes();
    }

    abstract List<TreeNode> fetchChildNodes(Connection connection);
}

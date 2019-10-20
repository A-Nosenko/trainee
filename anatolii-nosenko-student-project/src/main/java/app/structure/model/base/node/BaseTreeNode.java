package app.structure.model.base.node;

import app.structure.model.Item;
import app.structure.model.TreeNode;
import java.util.List;

/**
 * Base tree node realization, without own method to initialise child nodes.
 */
public class BaseTreeNode extends TreeNode {

    public BaseTreeNode(Item item) {
        super(item);
    }

    @Override
    public List<TreeNode> initChildNodes(Object datasource, boolean lazyInitialisation) {
        return null;
    }
}

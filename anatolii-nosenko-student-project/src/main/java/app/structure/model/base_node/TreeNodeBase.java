package app.structure.model.base_node;

import app.structure.model.Item;
import app.structure.model.TreeNode;

/**
 * Base tree node realization, without own method to initialise child nodes.
 */
public class TreeNodeBase extends TreeNode {

    public TreeNodeBase(Item item) {
        super(item);
    }

    @Override
    public void initChildNodes(Object datasource, boolean lazyInitialisation) {
    }
}

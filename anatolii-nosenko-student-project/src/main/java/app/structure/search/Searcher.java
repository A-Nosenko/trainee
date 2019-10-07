package app.structure.search;

import app.structure.model.Item;
import app.structure.model.TreeNode;

public interface Searcher {
    TreeNode find(TreeNode treeNode, Item item);
}

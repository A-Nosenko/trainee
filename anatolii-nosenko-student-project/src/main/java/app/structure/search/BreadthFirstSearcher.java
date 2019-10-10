package app.structure.search;

import app.structure.exception.AppException;
import app.structure.model.Item;
import app.structure.model.TreeNode;
import java.util.LinkedList;
import java.util.Queue;

/**
 * Searcher implementation with Breadth first search algorithm.
 */
public class BreadthFirstSearcher implements Searcher {
    @Override
    public TreeNode find(TreeNode treeNode, Item item) {
        if (treeNode == null) {
            throw new AppException("Null treeNode!");
        } else if (item == null) {
            throw new AppException("Null item!");
        }

        TreeNode current;
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(treeNode);
        do {
            current = queue.poll();
            if (current != null && current.getItem() != null) {
                System.out.print(current.getItem().getUniqueId() + " => ");
                if (current.getItem().equals(item)) {
                    System.out.println();
                    return current;
                }
            }
            if (current != null) {
                for (TreeNode child : current.getChildTreeNodes()) {
                    queue.offer(child);
                }
            }
        } while (!queue.isEmpty());

        return null;
    }
}

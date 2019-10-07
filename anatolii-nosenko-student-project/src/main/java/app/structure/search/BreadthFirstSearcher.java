package app.structure.search;

import app.structure.model.Item;
import app.structure.model.TreeNode;
import java.util.LinkedList;
import java.util.Queue;

public class BreadthFirstSearcher implements Searcher {
    @Override
    public TreeNode find(TreeNode treeNode, Item item) {
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

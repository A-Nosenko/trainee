package app.structure.search;

import app.exception.AppException;
import app.structure.model.Item;
import app.structure.model.TreeModel;
import app.structure.model.base.node.BaseTreeNode;
import org.junit.Test;

public class DepthFirstSearcherTest {

    @Test
    public void find() {
        TreeModel model = new TreeModel(new BreadthFirstSearcher());
        Item item_1 = new Item();
        item_1.setTagName("qq");

        Item item_2 = new Item();
        item_2.setTagName("qqq");

        Item item_3 = new Item();
        item_3.setTagName("qqqq");

        model.add(new BaseTreeNode(item_1));
        model.add(item_1.getUniqueId(), new BaseTreeNode(item_2));
        model.add(item_2.getUniqueId(), new BaseTreeNode(item_3));

        Searcher searcher = new DepthFirstSearcher();
        assert (item_2.toString()
            .equals(searcher.find(model.getRoot(), item_2.getUniqueId()).getItem().toString()));

    }

    @Test(expected = AppException.class)
    public void testException() {
        new DepthFirstSearcher().find(null, 0);
    }
}

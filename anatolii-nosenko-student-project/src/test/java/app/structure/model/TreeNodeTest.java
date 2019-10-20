package app.structure.model;

import app.exception.AppException;
import app.literals.Constants;
import app.structure.model.base.node.BaseTreeNode;
import app.structure.search.DepthFirstSearcher;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import org.junit.Test;

public class TreeNodeTest {

    @Test
    public void add() {
        TreeModel treeModel = new TreeModel(new DepthFirstSearcher());
        Item item = new Item();
        item.setTagName("test");
        treeModel.add(new BaseTreeNode(item));
        assertFalse(treeModel.add(item.getUniqueId(), null));
        assertFalse(treeModel.add(-1, new BaseTreeNode(new Item())));
    }

    @Test
    public void getRoot() {
        TreeModel treeModel = new TreeModel(new DepthFirstSearcher());
        Item item = new Item();
        item.setTagName("test");
        treeModel.add(new BaseTreeNode(item));
        assertFalse(treeModel.add(item.getUniqueId(), null));
        assertFalse(treeModel.add(-1, new BaseTreeNode(new Item())));

        Item item1 = new Item();
        item.setTagName("next");
        TreeNode node = new BaseTreeNode(item1);
        treeModel.add(item.getUniqueId(), node);
        assertEquals(item, node.getRoot().getItem());
    }

    @Test(expected = AppException.class)
    public void addWithoutParentTegNameException() {
        TreeModel treeModel = new TreeModel(new DepthFirstSearcher());
        Item item = new Item();
        treeModel.add(new BaseTreeNode(item));
        treeModel.add(item.getUniqueId(), new BaseTreeNode(new Item()));
    }

    @Test(expected = AppException.class)
    public void addWithParentContentException() {
        TreeModel treeModel = new TreeModel(new DepthFirstSearcher());
        Item item = new Item();
        item.setContent("test");
        treeModel.add(new BaseTreeNode(item));
        treeModel.add(item.getUniqueId(), new BaseTreeNode(new Item()));
    }

    @Test
    public void testToStringNULL_ELEMENT() {
        TreeModel treeModel = new TreeModel(new DepthFirstSearcher());
        treeModel.add(new BaseTreeNode(null));
        assertEquals(treeModel.toString(), Constants.NULL_ELEMENT);

        treeModel = new TreeModel(new DepthFirstSearcher());
        Item item = new Item();
        item.setTagName("test");
        treeModel.add(new BaseTreeNode(item));
        treeModel.add(item.getUniqueId(), new BaseTreeNode(null));
        assertEquals(treeModel.toString(),
            item.toString()
                .concat(Constants.NEW_LINE)
                .concat(Constants.NULL_ELEMENT));
    }
}
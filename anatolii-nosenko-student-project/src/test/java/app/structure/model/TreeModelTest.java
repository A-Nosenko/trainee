package app.structure.model;

import app.literals.Constants;
import app.structure.model.base.node.BaseTreeNode;
import app.structure.search.BreadthFirstSearcher;
import app.structure.search.DepthFirstSearcher;
import java.util.Map;
import static org.junit.Assert.*;
import org.junit.Test;

public class TreeModelTest {

    @Test
    public void getRoot() {
        TreeModel treeModel = new TreeModel(new BreadthFirstSearcher());
        assert (treeModel.getRoot() == null);

    }

    @Test
    public void getDeclarationMap() {
        TreeModel treeModel = new TreeModel(new BreadthFirstSearcher());
        Map<String, String> map = treeModel.getDeclarationMap();
        assertEquals(map.get(Constants.VERSION), Constants.DEFAULT_VERSION);
        assertEquals(map.get(Constants.ENCODING), Constants.DEFAULT_ENCODING);
    }

    @Test
    public void setAndGetSearcher() {
        TreeModel treeModel = new TreeModel(new BreadthFirstSearcher());
        assert (treeModel.getSearcher() instanceof BreadthFirstSearcher);
        treeModel.setSearcher(new DepthFirstSearcher());
        assert (treeModel.getSearcher() instanceof DepthFirstSearcher);
    }

    @Test
    public void add() {
        TreeModel treeModel = new TreeModel(new BreadthFirstSearcher());
        treeModel.add(new BaseTreeNode(new Item()));
        assert (treeModel.getRoot() instanceof BaseTreeNode);
    }

    @Test
    public void testAdd() {
        TreeModel treeModel = new TreeModel(new BreadthFirstSearcher());
        Item root = new Item();
        root.setTagName("root");
        treeModel.add(new BaseTreeNode(root));
        Item item = new Item();
        treeModel.add(root.getUniqueId(), new BaseTreeNode(item));
        assertEquals(treeModel.getSearcher().find(treeModel.getRoot(), item.getUniqueId()).getItem(), item);
    }

    @Test
    public void removeNode() {
        TreeModel treeModel = new TreeModel(new BreadthFirstSearcher());
        Item root = new Item();
        root.setTagName("root");
        treeModel.add(new BaseTreeNode(root));
        Item item = new Item();
        treeModel.add(root.getUniqueId(), new BaseTreeNode(item));
        assertTrue(treeModel.removeNode(item.getUniqueId()));
        assert (treeModel.getSearcher().find(treeModel.getRoot(), item.getUniqueId()) == null);
    }

    @Test
    public void removeIncorrectNode() {
        TreeModel treeModel = new TreeModel(new BreadthFirstSearcher());
        Item root = new Item();
        treeModel.add(new BaseTreeNode(root));
        assertFalse(treeModel.removeNode(-1));
        assertTrue(treeModel.removeNode(root.getUniqueId()));

        treeModel = new TreeModel(new BreadthFirstSearcher());
        assertFalse(treeModel.removeNode(0));
    }

    @Test
    public void testToString() {
        TreeModel treeModel = new TreeModel(new BreadthFirstSearcher());
        Item root = new Item();
        root.setTagName("root");
        treeModel.add(new BaseTreeNode(root));
        Item item = new Item();
        treeModel.add(root.getUniqueId(), new BaseTreeNode(item));
        assertEquals(treeModel.toString().trim(), root.toString() + Constants.NEW_LINE + item.toString());
    }
}

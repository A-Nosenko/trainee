package app.service;

import app.literals.Constants;
import app.model.TreeHolder;
import app.structure.model.Item;
import app.structure.model.TreeModel;
import app.structure.model.TreeNode;
import app.structure.model.base.node.BaseTreeNode;
import app.structure.search.BreadthFirstSearcher;
import static org.junit.Assert.assertNotNull;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import static org.mockito.Mockito.when;
import org.mockito.junit.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class TreeSavingServiceTest {
    @InjectMocks
    TreeSavingService treeSavingService = new TreeSavingService();

    @Mock
    TreeHolder treeHolder;

    @Test
    public void saveTree() {
        TreeModel treeModel = new TreeModel(new BreadthFirstSearcher());
        Item item = new Item();
        item.setTagName(Constants.DATABASES);
        TreeNode root = new BaseTreeNode(item);
        treeModel.add(root);
        treeModel.add(root.getItem().getUniqueId(), new BaseTreeNode(new Item()));
        when(treeHolder.getTreeModel()).thenReturn(treeModel);

        treeSavingService.saveTree();

        assertNotNull(treeSavingService.loadTree().getRoot());
    }
}

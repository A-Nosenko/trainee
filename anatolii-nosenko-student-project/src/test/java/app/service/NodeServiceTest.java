package app.service;

import app.model.ConnectionHolder;
import app.model.TreeHolder;
import app.structure.model.Item;
import app.structure.model.TreeModel;
import app.structure.model.TreeNode;
import app.structure.model.base.node.BaseTreeNode;
import app.structure.search.BreadthFirstSearcher;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import static org.mockito.Mockito.when;
import org.mockito.junit.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class NodeServiceTest {
    @InjectMocks
    NodeService nodeService = new NodeService();

    @Mock
    TreeHolder treeHolder;

    @Mock
    ConnectionHolder connectionHolder;

    @Test
    public void loadChildNodes() {
        TreeModel treeModel = new TreeModel(new BreadthFirstSearcher());
        Item item = new Item();
        item.setTagName("test");
        TreeNode root = new BaseTreeNode(item);
        treeModel.add(root);
        treeModel.add(root.getItem().getUniqueId(), new BaseTreeNode(new Item()));
        when(treeHolder.getTreeModel()).thenReturn(treeModel);

        nodeService.loadChildNodes(treeModel.getRoot().getItem().getUniqueId());
    }
}

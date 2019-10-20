package app.structure.model.base.node;

import app.structure.model.Item;
import static org.junit.Assert.assertNull;
import org.junit.Test;

public class BaseTreeNodeTest {

    @Test
    public void initChildNodes() {
        assertNull(new BaseTreeNode(new Item()).initChildNodes(null, true));
    }
}
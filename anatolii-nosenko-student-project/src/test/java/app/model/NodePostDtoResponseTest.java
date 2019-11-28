package app.model;

import app.structure.model.Item;
import app.structure.model.database.RootDatabasesTreeNode;
import java.util.Map;
import static junit.framework.TestCase.assertFalse;
import org.junit.AfterClass;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import org.junit.BeforeClass;
import org.junit.Test;

public class NodePostDtoResponseTest {

    private static NodePostDtoResponse nodePostDtoResponse;

    @BeforeClass
    public static void init() {
        nodePostDtoResponse = new NodePostDtoResponse(new RootDatabasesTreeNode(new Item()));
    }

    @Test
    public void getItem() {
        assertNotNull(nodePostDtoResponse.getItem());
    }

    @Test
    public void getChildTreeNodes() {
        assertNotNull(nodePostDtoResponse.getChildTreeNodes());
    }

    @Test
    public void isFinalNode() {
        assertFalse(nodePostDtoResponse.isFinalNode());
    }

    @Test
    public void isReceivedFromDatabase() {
        assertTrue(nodePostDtoResponse.isReceivedFromDatabase());
    }

    @Test
    public void isReceivedFromXML() {
        assertFalse(nodePostDtoResponse.isReceivedFromXML());
    }

    @AfterClass
    public static void reset() {
        nodePostDtoResponse = null;
    }
}
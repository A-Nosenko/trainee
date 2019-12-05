package app.model;

import app.literals.Constants;
import app.structure.model.Item;
import app.structure.model.database.DatabaseTreeNode;
import static junit.framework.TestCase.assertFalse;
import org.junit.AfterClass;
import static org.junit.Assert.assertNotNull;
import org.junit.BeforeClass;
import org.junit.Test;

public class NodePostDtoResponseTest {

    private static NodePostDtoResponse nodePostDtoResponse;

    @BeforeClass
    public static void init() {
        Item item = new Item();
        item.setAttribute(Constants.DATABASE_NAME, "test");
        nodePostDtoResponse = new NodePostDtoResponse(new DatabaseTreeNode(item));
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
        assertFalse(nodePostDtoResponse.isReceivedFromDatabase());
    }

    @Test
    public void isReceivedFromXML() {
        assertFalse(nodePostDtoResponse.isReceivedFromXML());
    }

    @Test
    public void testGetItem() {
        assertNotNull(nodePostDtoResponse.getItem());
    }

    @Test
    public void testGetChildTreeNodes() {
        assertNotNull(nodePostDtoResponse.getChildTreeNodes());
    }

    @Test
    public void testIsFinalNode() {
        assertFalse(nodePostDtoResponse.isFinalNode());
    }

    @Test
    public void testIsReceivedFromDatabase() {
        assertFalse(nodePostDtoResponse.isReceivedFromDatabase());
    }

    @Test
    public void testIsReceivedFromXML() {
        assertFalse(nodePostDtoResponse.isReceivedFromXML());
    }

    @AfterClass
    public static void reset() {
        nodePostDtoResponse = null;
    }
}

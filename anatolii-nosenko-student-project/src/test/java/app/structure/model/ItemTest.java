package app.structure.model;

import app.exception.AppException;
import java.util.Map;
import static org.junit.Assert.*;
import org.junit.Test;

public class ItemTest {

    @Test
    public void getAttribute() {
        Item item = new Item();
        assert (item.getAttribute(null) == null);
        item.setAttribute("f", "ff");
        item.setAttribute("d", "dd");
        assertEquals(item.getAttribute("d"), "dd");
        assertNull(item.getAttribute("123"));
    }

    @Test(expected = AppException.class)
    public void setAttribute() {
        Item item = new Item();
        String key = "key";
        String value = "value";
        item.setAttribute(key, value);
        assertEquals(item.getAttribute(key), value);
        item.setContent(key);
    }

    @Test(expected = AppException.class)
    public void setNullAttribute() {
        Item item = new Item();
        item.setAttribute(null, null);
    }

    @Test
    public void getContent() {
        Item item = new Item();
        assert (item.getContent() == null);
    }

    @Test(expected = AppException.class)
    public void setContent() {
        Item item = new Item();
        String content = "test";
        item.setContent(content);
        assertEquals(item.getContent(), content);
        item.setTagName(content);
    }

    @Test(expected = AppException.class)
    public void setContentAndAttribute() {
        Item item = new Item();
        String content = "test";
        item.setContent(content);
        item.setAttribute(content, content);
    }

    @Test
    public void getTagName() {
        Item item = new Item();
        assert (item.getTagName() == null);
    }

    @Test(expected = AppException.class)
    public void setTagName() {
        Item item = new Item();
        String tag = "test";
        item.setTagName(tag);
        assertEquals(item.getTagName(), tag);
        item.setContent(tag);
    }

    @Test
    public void getAttributes() {
        Item item = new Item();
        assert (item.getAttributes() instanceof Map);
    }

    @Test
    public void getUniqueId() {
        Item item = new Item();
        assert (item.getUniqueId() > 0);
    }

    @Test
    public void testEquals() {
        Item item = new Item();
        Item item1 = item;
        assertEquals(item, item1);
        assertNotEquals(null, item);
        assertNotEquals(item, null);
        assertNotEquals(new Item(), item);
        assertNotEquals(new Object(), item);
        assertNotEquals(item, new Object());
    }

    @Test
    public void testHashCode() {
        Item item = new Item();
        assertEquals(item.getUniqueId(), item.hashCode());
    }

    @Test
    public void testToString() {
        Item item = new Item();
        assertEquals(item.toString(), "Item_".concat(String.valueOf(item.getUniqueId())));
    }
}

package app.structure.model;

import app.exception.AppException;
import app.structure.utils.IdGenerator;
import java.util.Map;
import java.util.TreeMap;

/**
 * Class to represent element. Fit to hold XML.
 * Item object have unique id and data.
 * If object have content value, it can't have tag name and attributes.
 * May have only content or tag name with attributes.
 */
public class Item {
    private final long uniqueId;
    private String tagName;
    private final Map<String, String> attributes;
    private String content;

    /**
     * Item gets generated unique id in the constructor.
     */
    public Item() {
        this.uniqueId = IdGenerator.getInstance().getNext();
        attributes = new TreeMap<>();
    }

    /**
     * Method to get attribute value via key.
     *
     * @param key Attribute key.
     * @return Attribute value.
     */
    public String getAttribute(String key) {
        for (Map.Entry<String, String> entry : attributes.entrySet()) {
            if (entry.getKey().equals(key)) {
                return entry.getValue();
            }
        }
        return null;
    }

    /**
     * Method to add new attribute. Item must be without content.
     *
     * @param key   Attribute key.
     * @param value Attribute value.
     * @return Old attribute value, if item already contains attribute with specific key, or null, if key is new.
     */
    public String setAttribute(String key, String value) {
        if (key == null) {
            throw new AppException("key = null");
        }
        if (content != null) {
            throw new AppException("Can't add attribute here, content must be null.");
        }
        return attributes.put(key, value);
    }

    public String getContent() {
        return content;
    }

    /**
     * Adding String content to item. Item must be without tegName and attributes.
     *
     * @param content Content value.
     */
    public void setContent(String content) {
        if (tagName != null) {
            throw new AppException(
                "Can't add content, tag name already assigned. You should create new item, without tag name."
            );
        }

        if (attributes.size() > 0) {
            throw new AppException(
                "Can't add content, attribute already added. You should create new item, without attributes."
            );
        }
        this.content = content;
    }

    public String getTagName() {
        return tagName;
    }

    /**
     * Adding tagName to item. Item must be without content.
     *
     * @param tagName Tag name.
     */
    public void setTagName(String tagName) {
        if (content != null) {
            throw new AppException(
                "Can't set tag name, content already assigned. You should create new item, without content.");
        }
        this.tagName = tagName;
    }

    public Map<String, String> getAttributes() {
        return attributes;
    }

    public long getUniqueId() {
        return uniqueId;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        Item item = (Item) obj;
        return this.uniqueId == item.getUniqueId();
    }

    @Override
    public int hashCode() {
        return (int) uniqueId;
    }

    @Override
    public String toString() {
        return "Item_".concat(String.valueOf(uniqueId));
    }
}

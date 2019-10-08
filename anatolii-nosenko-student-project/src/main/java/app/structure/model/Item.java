package app.structure.model;

import app.structure.exception.AppException;
import app.structure.utils.IdGenerator;
import java.util.Map;
import java.util.TreeMap;

/**
 * Class to represent XML element.
 */
public class Item {
    private final int uniqueId;
    private String tagName;
    private final Map<String, String> attributes;
    private String content;

    public Item() {
        this.uniqueId = IdGenerator.getInstance().getNext();
        attributes = new TreeMap<>();
        System.out.println("Created " + this.toString());
    }

    public String getAttribute(String key) {
        for (Map.Entry<String, String> entry : attributes.entrySet()) {
            if (entry.getKey().equals(key)) {
                return entry.getValue();
            }
        }
        return null;
    }

    public String setAttribute(String key, String value) {
        if (key == null || value == null) {
            throw new AppException("key = " + key + " value = " + value);
        }
        return attributes.put(key, value);
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        if (tagName != null) {
            throw new AppException("Can't add content, tag name already assigned." +
                "You should create new item, without tag name.");
        }
        this.content = content;
    }

    public String getTagName() {
        return tagName;
    }

    public void setTagName(String tagName) {
        if (content != null) {
            throw new AppException("Can't set tag name, content already assigned." +
                "You should create new item, without content.");
        }
        this.tagName = tagName;
    }

    public Map<String, String> getAttributes() {
        return attributes;
    }

    public int getUniqueId() {
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
        return uniqueId;
    }

    @Override
    public String toString() {
        return "Item_".concat(String.valueOf(uniqueId));
    }
}

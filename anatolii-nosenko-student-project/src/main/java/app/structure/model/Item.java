package app.structure.model;

import app.structure.exception.AppException;
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
        System.out.println("Created ".concat(this.toString()));
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
        if (key == null) {
            throw new AppException("key = ".concat(key));
        }
        if (content != null) {
            throw new AppException("Can't add attribute here, content must be null.");
        }
        return attributes.put(key, value);
    }

    public String getContent() {
        return content;
    }

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

    public void setTagName(String tagName) {
        if (content != null) {
            throw new AppException(
                "Can't set tag name, content already assigned. You should create new item, without content.");
        }
        System.out.println("id: ".concat(String.valueOf(getUniqueId())).concat(" Tag name: ").concat(tagName));
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

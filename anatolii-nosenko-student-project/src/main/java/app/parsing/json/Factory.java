package app.parsing.json;

import app.literals.Constants;
import java.util.Map;

/**
 * Class to convert specific information to JSON.
 */
public final class Factory {

    private Factory() {
    }

    /**
     * Method to convert information from node to JSON.
     *
     * @param itemId     Item id of the node.
     * @param tagName    Item tag name.
     * @param content    Item content.
     * @param attributes Item attributes.
     * @return Line in JSON format with specific information.
     */
    public static String create(long itemId, String tagName,
                                String content, Map<String, String> attributes) {
        StringBuilder builder = new StringBuilder();
        builder.append(Constants.START_JSON);
        appendKeyValue(builder, Constants.ID, String.valueOf(itemId));

        if (tagName != null) {
            builder.append(Constants.COMMA);
            appendKeyValue(builder, Constants.TAG_NAME, tagName);
        }

        if (content != null) {
            builder.append(Constants.COMMA);
            appendKeyValue(builder, Constants.CONTENT, content);
        }
        if (attributes != null && !attributes.isEmpty()) {
            builder.append(Constants.COMMA);
            appendKeyValue(builder, Constants.ATTRIBUTES, attributes);
        }

        builder.append(Constants.FINISH_JSON);
        return builder.toString();
    }

    /**
     * Method to build JSON text.
     *
     * @param builder StringBuilder with JSON content.
     * @param key     Key to add in JSON content.
     * @param value   Value to add in JSON content.
     */
    public static void appendKeyValue(StringBuilder builder,
                                      String key, String value) {
        appendParameter(builder, key);
        builder.append(Constants.COLON);
        appendParameter(builder, value);
    }

    private static void appendKeyValue(StringBuilder builder,
                                       String key, Map<String, String> attributes) {
        appendParameter(builder, key);
        builder.append(Constants.COLON);
        builder.append(Constants.START_JSON);
        for (Map.Entry<String, String> attribute : attributes.entrySet()) {
            appendKeyValue(builder, attribute.getKey(), attribute.getValue());
            builder.append(Constants.COMMA);
        }
        if (!attributes.isEmpty()) {
            builder.replace(builder.length() - 2, builder.length(), "");
        }
        builder.append(Constants.FINISH_JSON);
    }

    private static void appendParameter(StringBuilder builder,
                                        String param) {
        builder.append(Constants.DOUBLE_QUOTES);
        builder.append(param);
        builder.append(Constants.DOUBLE_QUOTES);
    }
}

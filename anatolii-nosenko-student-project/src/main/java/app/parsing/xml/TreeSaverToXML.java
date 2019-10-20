package app.parsing.xml;

import app.literals.Constants;
import app.structure.model.TreeNode;
import app.structure.model.TreeModel;
import java.util.Map;
import org.apache.log4j.Logger;

/**
 * Class to recursive saving TreeModel in XML text file using StringBuilder.
 */
public class TreeSaverToXML {
    private static final Logger LOGGER = Logger.getLogger(TreeSaverToXML.class.getName());

    /**
     * Method saves TreeModel to XML.
     *
     * @param model TreeModel object to save.
     * @return XML format text.
     */
    public String save(TreeModel model) {
        LOGGER.info("Converting TreeModel object to XML String");
        StringBuilder builder = new StringBuilder();
        if (!model.getDeclarationMap().isEmpty()) {
            builder.append(Constants.START_TEG);
            builder.append(Constants.START_DECLARATION);
            for (Map.Entry<String, String> entry : model.getDeclarationMap().entrySet()) {
                builder.append(Constants.SPACE);
                builder.append(entry.getKey());
                builder.append(Constants.EQUALS);
                builder.append(Constants.QUOTE);
                builder.append(prepareText(entry.getValue()));
                builder.append(Constants.QUOTE);
            }
            builder.append(Constants.END_DECLARATION);
            builder.append(Constants.END_TEG);
            builder.append(Constants.NEW_LINE);
        }
        builder.append(save(model.getRoot()));
        return builder.toString().replace(Constants.TEG_BOUNDS, Constants.TEG_BOUNDS_TO_NEXT_LINE);
    }

    private String save(TreeNode treeNode) {
        if (treeNode == null) {
            return Constants.NULL_ELEMENT;
        }
        StringBuilder builder = new StringBuilder();
        if (treeNode.getItem().getTagName() != null) {
            builder.append(Constants.START_TEG);
            builder.append(treeNode.getItem().getTagName());

            for (Map.Entry<String, String> entry : treeNode.getItem().getAttributes().entrySet()) {
                builder.append(Constants.SPACE);
                builder.append(entry.getKey());
                builder.append(Constants.EQUALS);
                builder.append(Constants.QUOTE);
                builder.append(prepareText(entry.getValue()));
                builder.append(Constants.QUOTE);
            }
            builder.append(Constants.END_TEG);
        }

        if (treeNode.getItem().getContent() != null) {
            builder.append(prepareText(treeNode.getItem().getContent()));
        } else if (!treeNode.getChildTreeNodes().isEmpty()) {
            for (TreeNode childTreeNode : treeNode.getChildTreeNodes()) {
                builder.append(save(childTreeNode)); // Recursion to pass child nodes.
            }
        }
        if (treeNode.getItem().getTagName() != null) {
            builder.append(Constants.CLOSE_START_TEG);
            builder.append(treeNode.getItem().getTagName());
            builder.append(Constants.END_TEG);
        }
        return builder.toString();
    }

    private String prepareText(String source) {
        if (source == null) {
            return null;
        }

        return source
            .replace(Constants.XML_SPECIAL_SYMBOLS[0][0], Constants.XML_SPECIAL_SYMBOLS[0][1])
            .replace(Constants.XML_SPECIAL_SYMBOLS[1][0], Constants.XML_SPECIAL_SYMBOLS[1][1])
            .replace(Constants.XML_SPECIAL_SYMBOLS[2][0], Constants.XML_SPECIAL_SYMBOLS[2][1])
            .replace(Constants.XML_SPECIAL_SYMBOLS[3][0], Constants.XML_SPECIAL_SYMBOLS[3][1])
            .replace(Constants.XML_SPECIAL_SYMBOLS[4][0], Constants.XML_SPECIAL_SYMBOLS[4][1]);
    }
}

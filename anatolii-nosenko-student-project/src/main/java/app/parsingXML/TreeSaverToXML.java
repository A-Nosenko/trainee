package app.parsingXML;

import static app.literals.Constants.END_DECLARATION;
import static app.literals.Constants.NULL_ELEMENT;
import static app.literals.Constants.TEG_BOUNDS;
import static app.literals.Constants.TEG_BOUNDS_TO_NEXT_LINE;
import static app.literals.Constants.XML_SPECIAL_SYMBOLS;
import app.structure.model.TreeNode;
import app.structure.model.TreeModel;
import java.util.Map;

import static app.literals.Constants.CLOSE_START_TEG;
import static app.literals.Constants.END_TEG;
import static app.literals.Constants.EQUALS;
import static app.literals.Constants.NEW_LINE;
import static app.literals.Constants.QUOTE;
import static app.literals.Constants.SPACE;
import static app.literals.Constants.START_DECLARATION;
import static app.literals.Constants.START_TEG;

/**
 * Class to recursive saving TreeModel in XML text file using StringBuilder.
 */
public class TreeSaverToXML {

    /**
     * Method saves TreeModel to XML.
     * @param model TreeModel object to save.
     * @return XML format text.
     */
    public String save(TreeModel model) {
        StringBuilder builder = new StringBuilder();
        if (!model.getDeclarationMap().isEmpty()) {
            builder.append(START_TEG);
            builder.append(START_DECLARATION);
            for (Map.Entry<String, String> entry : model.getDeclarationMap().entrySet()) {
                builder.append(SPACE);
                builder.append(entry.getKey());
                builder.append(EQUALS);
                builder.append(QUOTE);
                builder.append(prepareText(entry.getValue()));
                builder.append(QUOTE);
            }
            builder.append(END_DECLARATION);
            builder.append(END_TEG);
            builder.append(NEW_LINE);
        }
        builder.append(save(model.getRoot()));
        return builder.toString().replace(TEG_BOUNDS, TEG_BOUNDS_TO_NEXT_LINE);
    }

    private String save(TreeNode treeNode) {
        if (treeNode == null) {
            return NULL_ELEMENT;
        }
        StringBuilder builder = new StringBuilder();
        if (treeNode.getItem().getTagName() != null) {
            builder.append(START_TEG);
            builder.append(treeNode.getItem().getTagName());

            for (Map.Entry<String, String> entry : treeNode.getItem().getAttributes().entrySet()) {
                builder.append(SPACE);
                builder.append(entry.getKey());
                builder.append(EQUALS);
                builder.append(QUOTE);
                builder.append(prepareText(entry.getValue()));
                builder.append(QUOTE);
            }
            builder.append(END_TEG);
        }

        if (treeNode.getItem().getContent() != null) {
            builder.append(prepareText(treeNode.getItem().getContent()));
        } else if (!treeNode.getChildTreeNodes().isEmpty()) {
            for (TreeNode childTreeNode : treeNode.getChildTreeNodes()) {
                builder.append(save(childTreeNode)); // Recursion to pass child nodes.
            }
        }
        if (treeNode.getItem().getTagName() != null) {
            builder.append(CLOSE_START_TEG);
            builder.append(treeNode.getItem().getTagName());
            builder.append(END_TEG);
        }
        return builder.toString();
    }

    private String prepareText(String source) {
        if (source == null) {
            return null;
        }

        return source
            .replace(XML_SPECIAL_SYMBOLS[0][0], XML_SPECIAL_SYMBOLS[0][1])
            .replace(XML_SPECIAL_SYMBOLS[1][0], XML_SPECIAL_SYMBOLS[1][1])
            .replace(XML_SPECIAL_SYMBOLS[2][0], XML_SPECIAL_SYMBOLS[2][1])
            .replace(XML_SPECIAL_SYMBOLS[3][0], XML_SPECIAL_SYMBOLS[3][1])
            .replace(XML_SPECIAL_SYMBOLS[4][0], XML_SPECIAL_SYMBOLS[4][1]);
    }
}

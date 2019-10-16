package app.parsingXML;

import static app.literals.Constants.ENCODING;
import static app.literals.Constants.STANDALONE;
import static app.literals.Constants.STANDALONE_YES;
import static app.literals.Constants.VERSION;
import app.structure.exception.AppException;
import app.structure.model.Item;
import app.structure.model.TreeModel;
import app.structure.model.base_node.TreeNodeBase;
import java.io.File;
import java.io.IOException;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import org.w3c.dom.Document;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.Text;
import org.xml.sax.SAXException;

/**
 * Class to read XML using DOM parser.
 */
public class TreeLoaderFromXML {

    private TreeModel treeModel;

    /**
     * Method reads XML file to TreeModel object.
     * @param inputXML File to read.
     * @param model TreeModel object to fill.
     * @return TreeModel object filled content of file.
     */
    public TreeModel load(File inputXML, TreeModel model) {
        if (inputXML == null) {
            throw new AppException("Illegal argument! inputXML is null.");
        }
        if (model == null) {
            throw new AppException("Illegal argument! model is null.");
        }
        treeModel = model;
        Document document;
        try {
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();
            document = builder.parse(inputXML);
        } catch (ParserConfigurationException | SAXException | IOException e) {
            throw new AppException(e.getMessage());
        }

        String version = document.getXmlVersion();
        if (version != null && !version.isEmpty()) {
            treeModel.getDeclarationMap().put(VERSION, version);
        }

        String encoding = document.getXmlEncoding();
        if (encoding != null && !encoding.isEmpty()) {
            treeModel.getDeclarationMap().put(ENCODING, encoding);
        }

        boolean standalone = document.getXmlStandalone();
        if (standalone) {
            treeModel.getDeclarationMap().put(STANDALONE, STANDALONE_YES);
        }

        document.getDocumentElement().normalize();
        parse(null, document.getDocumentElement());

        return treeModel;
    }

    private void parse(Item parent, Node node) {
        if (node == null) {
            throw new AppException("Illegal argument, node is null!");
        }
        short nodeType = node.getNodeType();
        if (nodeType == Node.COMMENT_NODE
            || nodeType == Node.CDATA_SECTION_NODE) {
            return;
        }
        if (nodeType == Node.TEXT_NODE
            && node.getNodeValue().trim().isEmpty()) {
            return;
        }
        Item currentItem = new Item();
        if (parent == null) {
            treeModel.add(new TreeNodeBase(currentItem));
        } else {
            treeModel.add(parent.getUniqueId(), new TreeNodeBase(currentItem));
        }

        if (!(node instanceof Text)) {
            currentItem.setTagName(node.getNodeName());
        } else {
            currentItem.setContent(node.getTextContent());
            return;
        }
        NamedNodeMap attributes = node.getAttributes();
        if (attributes != null) {
            for (int i = 0; i < attributes.getLength(); i++) {
                currentItem.setAttribute(
                    attributes.item(i).getNodeName(), attributes.item(i).getNodeValue());
            }
        }

        if (node.getNodeType() == Node.ELEMENT_NODE) {
            Node next = node.getFirstChild();
            while (next != null) {
                parse(currentItem, next);
                next = next.getNextSibling();
            }
        }
    }
}

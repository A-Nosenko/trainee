package app.parsing.xml;

import app.literals.Constants;
import app.exception.AppException;
import app.structure.model.Item;
import app.structure.model.TreeModel;
import app.structure.model.base.node.BaseTreeNode;
import java.io.File;
import java.io.IOException;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import org.apache.log4j.Logger;
import org.w3c.dom.Document;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.Text;
import org.xml.sax.SAXException;

/**
 * Class to read XML using DOM parser.
 */
public class TreeLoaderFromXML {
    private static final Logger LOGGER = Logger.getLogger(TreeLoaderFromXML.class.getName());

    private TreeModel treeModel;

    /**
     * Method reads XML file to TreeModel object.
     *
     * @param inputXML File to read.
     * @param model    TreeModel object to fill.
     */
    public void load(File inputXML, TreeModel model) {
        if (inputXML == null) {
            throw new AppException("Illegal argument! inputXML is null.");
        }
        if (model == null) {
            throw new AppException("Illegal argument! model is null.");
        }
        LOGGER.info("Reading XML file "
            .concat(inputXML.toString())
            .concat(" to TreeModel object."));
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
            treeModel.getDeclarationMap().put(Constants.VERSION, version);
        }

        String encoding = document.getXmlEncoding();
        if (encoding != null && !encoding.isEmpty()) {
            treeModel.getDeclarationMap().put(Constants.ENCODING, encoding);
        }

        boolean standalone = document.getXmlStandalone();
        if (standalone) {
            treeModel.getDeclarationMap().put(Constants.STANDALONE, Constants.STANDALONE_YES);
        }

        document.getDocumentElement().normalize();
        parse(null, document.getDocumentElement());
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
            treeModel.add(new BaseTreeNode(currentItem));
        } else {
            treeModel.add(parent.getUniqueId(), new BaseTreeNode(currentItem));
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

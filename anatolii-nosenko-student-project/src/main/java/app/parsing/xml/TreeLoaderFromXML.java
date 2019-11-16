package app.parsing.xml;

import app.literals.Constants;
import app.exception.AppException;
import app.structure.model.Item;
import app.structure.model.TreeModel;
import app.structure.model.TreeNode;
import app.structure.model.base.node.BaseTreeNode;
import app.structure.model.database.ColumnDatabaseTreeNode;
import app.structure.model.database.ColumnsDatabaseTreeNode;
import app.structure.model.database.DatabaseTreeNode;
import app.structure.model.database.ForeignKeyDatabaseTreeNode;
import app.structure.model.database.FunctionDatabaseTreeNode;
import app.structure.model.database.FunctionsDatabaseTreeNode;
import app.structure.model.database.RootDatabasesTreeNode;
import app.structure.model.database.StoredProcedureDatabaseTreeNode;
import app.structure.model.database.StoredProceduresDatabaseTreeNode;
import app.structure.model.database.TableDatabaseTreeNode;
import app.structure.model.database.TablesDatabaseTreeNode;
import app.structure.model.database.TriggerDatabaseTreeNode;
import app.structure.model.database.TriggersDatabaseTreeNode;
import app.structure.model.database.ViewDatabaseTreeNode;
import app.structure.model.database.ViewsDatabaseTreeNode;
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
            TreeNode treeNode;
            switch (node.getNodeName()) {
                case Constants.COLUMN:
                    treeNode = new ColumnDatabaseTreeNode(currentItem);
                    break;
                case Constants.COLUMNS:
                    treeNode = new ColumnsDatabaseTreeNode(currentItem);
                    break;
                case Constants.DATABASE:
                    treeNode = new DatabaseTreeNode(currentItem);
                    break;
                case Constants.FOREIGN_KEY:
                    treeNode = new ForeignKeyDatabaseTreeNode(currentItem);
                    break;
                case Constants.FUNCTION:
                    treeNode = new FunctionDatabaseTreeNode(currentItem);
                    break;
                case Constants.FUNCTIONS:
                    treeNode = new FunctionsDatabaseTreeNode(currentItem);
                    break;
                case Constants.DATABASES:
                    treeNode = new RootDatabasesTreeNode(currentItem);
                    break;
                case Constants.STORED_PROCEDURE:
                    treeNode = new StoredProcedureDatabaseTreeNode(currentItem);
                    break;
                case Constants.STORED_PROCEDURES:
                    treeNode = new StoredProceduresDatabaseTreeNode(currentItem);
                    break;
                case Constants.TABLE:
                    treeNode = new TableDatabaseTreeNode(currentItem);
                    break;
                case Constants.TABLES:
                    treeNode = new TablesDatabaseTreeNode(currentItem);
                    break;
                case Constants.TRIGGER:
                    treeNode = new TriggerDatabaseTreeNode(currentItem);
                    break;
                case Constants.TRIGGERS:
                    treeNode = new TriggersDatabaseTreeNode(currentItem);
                    break;
                case Constants.VIEW:
                    treeNode = new ViewDatabaseTreeNode(currentItem);
                    break;
                case Constants.VIEWS:
                    treeNode = new ViewsDatabaseTreeNode(currentItem);
                    break;
                default:
                    treeNode = new BaseTreeNode(currentItem);
                    break;
            }
            treeNode.setReceivedFromXML(true);
            treeModel.add(parent.getUniqueId(), treeNode);
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

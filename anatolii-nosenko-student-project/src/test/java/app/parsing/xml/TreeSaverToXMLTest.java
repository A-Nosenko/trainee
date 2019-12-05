package app.parsing.xml;

import app.file.work.TextReader;
import app.file.work.TextWriter;
import app.structure.model.TreeModel;
import app.structure.search.BreadthFirstSearcher;
import app.structure.search.DepthFirstSearcher;
import java.io.File;
import java.io.IOException;
import org.custommonkey.xmlunit.XMLTestCase;
import org.custommonkey.xmlunit.XMLUnit;
import org.junit.Test;
import org.xml.sax.SAXException;

public class TreeSaverToXMLTest extends XMLTestCase {

    private final static File INPUT = new File("src/test/test_data/full_tree.xml");
    private final static File OUT_1 = new File("src/test/test_data/out_1.xml");
    private final static File OUT_2 = new File("src/test/test_data/out_2.xml");

    @Test
    public void testXMLConversion() throws IOException, SAXException {

        TreeModel treeModel = new TreeModel(new DepthFirstSearcher());
        TreeLoaderFromXML treeLoaderFromXML = new TreeLoaderFromXML();
        treeLoaderFromXML.load(INPUT, treeModel);

        TreeSaverToXML treeSaverToXML = new TreeSaverToXML();
        String XMLFromTree = treeSaverToXML.save(treeModel);

        TextWriter textWriter = new TextWriter();
        textWriter.write(XMLFromTree, OUT_1);

        TreeModel treeModel_2 = new TreeModel(new BreadthFirstSearcher());
        treeLoaderFromXML.load(OUT_1, treeModel_2);

        String XMLFromTree_2 = treeSaverToXML.save(treeModel_2);
        textWriter.write(XMLFromTree_2, OUT_2);

        TextReader textReader = new TextReader();
        String XML_1 = textReader.read(OUT_1);
        String XML_2 = textReader.read(OUT_2);

        XMLUnit.setIgnoreWhitespace(true);
        String inputXML = textReader.read(INPUT);

        assertXMLEqual(inputXML, XML_1);

        assert (XML_1.equals(XML_2));
        OUT_1.delete();
        OUT_2.delete();
    }
}

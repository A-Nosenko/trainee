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

    @Test
    public void testXMLConversion() throws IOException, SAXException {

        TreeModel treeModel = new TreeModel(new DepthFirstSearcher());
        TreeLoaderFromXML treeLoaderFromXML = new TreeLoaderFromXML();
        treeLoaderFromXML.load(new File("src/test/test_data/input.xml"), treeModel);

        TreeSaverToXML treeSaverToXML = new TreeSaverToXML();
        String XMLFromTree = treeSaverToXML.save(treeModel);

        TextWriter textWriter = new TextWriter();
        textWriter.write(XMLFromTree, new File("src/test/test_data/out_1.xml"));

        TreeModel treeModel_2 = new TreeModel(new BreadthFirstSearcher());
        treeLoaderFromXML.load(new File("src/test/test_data/out_1.xml"), treeModel_2);

        String XMLFromTree_2 = treeSaverToXML.save(treeModel_2);
        textWriter.write(XMLFromTree_2, new File("src/test/test_data/out_2.xml"));

        TextReader textReader = new TextReader();
        String XML_1 = textReader.read(new File("src/test/test_data/out_1.xml"));
        String XML_2 = textReader.read(new File("src/test/test_data/out_2.xml"));

        XMLUnit.setIgnoreWhitespace(true);
        String inputXML = textReader.read(new File("src/test/test_data/input.xml"));

        assertXMLEqual(inputXML, XML_1);

        assert (XML_1.equals(XML_2));
    }
}

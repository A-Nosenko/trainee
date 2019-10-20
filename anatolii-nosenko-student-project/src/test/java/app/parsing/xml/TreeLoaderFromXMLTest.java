package app.parsing.xml;

import app.exception.AppException;
import app.file.work.TextWriter;
import app.structure.model.TreeModel;
import app.structure.search.DepthFirstSearcher;
import java.io.File;
import org.junit.AfterClass;
import org.junit.Test;

public class TreeLoaderFromXMLTest {
    private static final File wrongXML = new File("src/test/test_data/wrong_input.xml");

    @Test(expected = AppException.class)
    public void loadNullFileException() {
        TreeModel treeModel = new TreeModel(new DepthFirstSearcher());
        TreeLoaderFromXML treeLoaderFromXML = new TreeLoaderFromXML();
        treeLoaderFromXML.load(null, treeModel);
    }

    @Test(expected = AppException.class)
    public void loadWrongFileException() {
        new TextWriter().write("<f>some wrong xml  <///f>", wrongXML);
        TreeModel treeModel = new TreeModel(new DepthFirstSearcher());
        TreeLoaderFromXML treeLoaderFromXML = new TreeLoaderFromXML();
        treeLoaderFromXML.load(wrongXML, treeModel);
    }

    @Test(expected = AppException.class)
    public void loadNullTreeException() {
        TreeLoaderFromXML treeLoaderFromXML = new TreeLoaderFromXML();
        treeLoaderFromXML.load(new File("src/test/test_data/input.xml"), null);
    }

    @AfterClass
    public static void methodAfterClass() {
        wrongXML.delete();
    }
}
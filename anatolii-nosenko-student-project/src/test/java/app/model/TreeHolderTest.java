package app.model;

import org.junit.AfterClass;
import static org.junit.Assert.*;
import org.junit.BeforeClass;
import org.junit.Test;

public class TreeHolderTest {

    private static TreeHolder treeHolder;

    @BeforeClass
    public static void init() {
        treeHolder = new TreeHolder();
    }

    @Test
    public void getTreeModel() {
        treeHolder.setTreeModel(null);
        assertNull(treeHolder.getTreeModel());
    }

    @AfterClass
    public static void reset() {
        treeHolder = null;
    }
}
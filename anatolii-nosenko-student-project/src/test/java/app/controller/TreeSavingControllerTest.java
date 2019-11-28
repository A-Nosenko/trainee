package app.controller;

import org.junit.Test;
import static org.mockito.Mockito.mock;

public class TreeSavingControllerTest {

    @Test
    public void saveTree() {
    }

    @Test
    public void loadTree() {
        TreeSavingController treeSavingController = mock(TreeSavingController.class);
        treeSavingController.saveTree();
        treeSavingController.loadTree();
    }
}
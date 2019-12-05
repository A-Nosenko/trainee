package app.controller;

import app.service.TreeSavingService;
import static junit.framework.TestCase.assertEquals;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import static org.mockito.Mockito.when;
import org.mockito.junit.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class TreeSavingControllerTest {
    @InjectMocks
    TreeSavingController treeSavingController = new TreeSavingController();

    @Mock
    TreeSavingService treeSavingService;

    @Test
    public void saveTree() {
        when(treeSavingService.saveTree()).thenReturn(true);
        assertEquals(200, treeSavingController.saveTree().getStatusCode().value());

        when(treeSavingService.saveTree()).thenReturn(false);
        assertEquals(409, treeSavingController.saveTree().getStatusCode().value());
    }

    @Test(expected = NullPointerException.class)
    public void loadTree() {
        TreeSavingController treeSavingController = new TreeSavingController();
        treeSavingController.loadTree();
    }
}

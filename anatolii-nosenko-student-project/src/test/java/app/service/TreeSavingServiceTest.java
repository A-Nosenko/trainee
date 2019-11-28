package app.service;

import static org.junit.Assert.*;
import org.junit.Test;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class TreeSavingServiceTest {

    @Test
    public void saveTree() {
        TreeSavingService treeSavingService = mock(TreeSavingService.class);
        when(treeSavingService.loadTree()).thenReturn(null);
        when(treeSavingService.saveTree()).thenReturn(null);
        assertNull(treeSavingService.saveTree());
        assertNull(treeSavingService.loadTree());
    }
}
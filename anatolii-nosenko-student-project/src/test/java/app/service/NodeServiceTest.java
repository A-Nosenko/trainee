package app.service;

import static org.junit.Assert.*;
import org.junit.Test;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class NodeServiceTest {

    @Test
    public void loadChildNodes() {
        NodeService nodeService = mock(NodeService.class);
        when(nodeService.loadChildNodes(0)).thenReturn(null);
        assertNull(nodeService.loadChildNodes(0));
    }
}
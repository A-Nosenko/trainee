package app.controller;

import org.junit.Test;
import static org.mockito.Mockito.mock;

public class NodesControllerTest {

    @Test
    public void openNode() {
        NodesController nodesController = mock(NodesController.class);
        nodesController.openNode(0);
    }
}
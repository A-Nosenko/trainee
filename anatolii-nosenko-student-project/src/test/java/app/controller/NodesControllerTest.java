package app.controller;

import org.junit.Test;

public class NodesControllerTest {

    @Test(expected = NullPointerException.class)
    public void openNode() {
        NodesController nodesController = new NodesController();

        nodesController.openNode(0);
    }
}

package app.controller;

import app.service.NodeService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * Controller to produce opened nodes.
 */
@CrossOrigin("http://localhost:3000")
@RestController
public class NodesController {
    private static final Logger LOGGER = Logger.getLogger(NodesController.class);

    @Autowired
    private NodeService nodeService;

    /**
     * Method to open child nodes from specific node.
     *
     * @param itemId Item id from node to load child nodes.
     * @return JSON array of child nodes.
     */
    @GetMapping(value = "/open", produces = MediaType.APPLICATION_JSON_VALUE)
    public String openNode(@RequestParam(value = "id") long itemId) {
        LOGGER.info("Trying to load node with itemId = ".concat(String.valueOf(itemId)));

        return nodeService.loadChildNodes(itemId);
    }
}

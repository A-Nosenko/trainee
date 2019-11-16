package app.controller;

import app.model.ConnectionPostDtoResponse;
import app.service.TreeSavingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Controller to handle query for saving tree in XML.
 */
@CrossOrigin("http://localhost:3000")
@RestController
public class TreeSavingController {

    @Autowired
    private TreeSavingService treeSavingService;

    /**
     * Method to handle save tree query.
     *
     * @return HttpStatus.OK, if tree successfully saved in XML file.
     * HttpStatus.CONFLICT in case tree saving error.
     */
    @GetMapping(value = "/save")
    public ResponseEntity saveTree() {
        if (treeSavingService.saveTree()) {
            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.status(HttpStatus.CONFLICT).build();
        }
    }

    /**
     * Method to handle load tree query.
     *
     * @return ConnectionPostDtoResponse with tree root and information about tree source.
     */
    @GetMapping(value = "/load", produces = MediaType.APPLICATION_JSON_VALUE)
    public ConnectionPostDtoResponse loadTree() {
        return treeSavingService.loadTree();
    }
}

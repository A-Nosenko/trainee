package app.controller;

import app.literals.Constants;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

/**
 * Start page controller.
 */
@Controller
public class StartController {

    private static final Logger LOGGER = Logger.getLogger(StartController.class);

    /**
     * Method to map start page.
     *
     * @return Start page ModelAndView.
     */
    @RequestMapping(value = "/", method = RequestMethod.GET)
    public ModelAndView startPage() {
        LOGGER.info("Welcome to start page!");
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName(Constants.START_PAGE);
        return modelAndView;
    }
}

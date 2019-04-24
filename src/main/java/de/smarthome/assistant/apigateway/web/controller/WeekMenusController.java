package de.smarthome.assistant.apigateway.web.controller;

import de.smarthome.assistant.apigateway.web.dto.ListWeekMenusDto;
import de.smarthome.assistant.apigateway.web.dto.WeekMenuDto;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/weekMenus")
public class WeekMenusController {

    /**
     * Returns a list of all available menus.
     *
     * @return ListWeekMenusDto
     */
    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public ListWeekMenusDto list() {
        return null;
    }

    /**
     * Returns a single menu by id.
     *
     * @param id of the menu
     * @return WeekMenuDto
     */
    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public WeekMenuDto get(@PathVariable("id") Long id) {
        return null;
    }

}

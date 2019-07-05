package de.smarthome.assistant.apigateway.web.controller;

import de.smarthome.assistant.apigateway.component.weekmenu.WeekMenuI;
import de.smarthome.assistant.apigateway.web.dto.WeekMenuDto;
import de.smarthome.assistant.apigateway.web.dto.WeekMenuListDto;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/weekMenus")
public class WeekMenusController {

    private final WeekMenuI weekMenu;

    @Autowired
    public WeekMenusController(WeekMenuI weekMenu) {
        this.weekMenu = weekMenu;
    }

    /**
     * Returns a list of all available menus.
     *
     * @return ListWeekMenusDto
     */
    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public ResponseEntity<WeekMenuListDto> list() throws ExecutionException, InterruptedException {
        return this.weekMenu.getWeekMenuList().get().map(weekMenu -> ResponseEntity.ok().body(weekMenu))
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    /**
     * Returns a single menu found by id.
     *
     * @param id of the menu
     * @return WeekMenuDto
     */
    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public WeekMenuDto get(@PathVariable("id") Long id) {
        return null;
    }

    /**
     * Returns a list of all menus filtered by a search string.
     *
     * @param searchString a search string
     * @return ListWeekMenusDto
     */
    @RequestMapping(value = "/list/{searchstring}", method = RequestMethod.GET)
    public WeekMenuListDto filteredList(@PathVariable("searchstring") String searchString) {
        return null;
    }

}

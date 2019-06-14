package de.smarthome.assistant.apigateway.web.controller;

import de.smarthome.assistant.apigateway.configuration.AsyncConfig;
import de.smarthome.assistant.apigateway.web.dto.WeekMenuDto;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import org.springframework.scheduling.annotation.Async;
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
    @Async(AsyncConfig.TASK_EXECUTOR_CONTROLLER)
    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public CompletableFuture<List<WeekMenuDto>> list() {
        // create demo data
        final WeekMenuDto weekMenuDto_1 = new WeekMenuDto();
        weekMenuDto_1.setName("Schweinefleisch mit Klößen");
        final WeekMenuDto weekMenuDto_2 = new WeekMenuDto();
        weekMenuDto_2.setName("Grießbrei");
        List<WeekMenuDto> weekMenuDtos = new ArrayList<>();
        weekMenuDtos.add(weekMenuDto_1);
        weekMenuDtos.add(weekMenuDto_2);
        final CompletableFuture<List<WeekMenuDto>> listWeekMenusDtoCompletableFuture = new CompletableFuture<>();
        listWeekMenusDtoCompletableFuture.complete(weekMenuDtos);
        return listWeekMenusDtoCompletableFuture;
    }

    /**
     * Returns a single menu found by id.
     *
     * @param id of the menu
     * @return WeekMenuDto
     */
    @Async(AsyncConfig.TASK_EXECUTOR_CONTROLLER)
    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public CompletableFuture<WeekMenuDto> get(@PathVariable("id") Long id) {
        return null;
    }

    /**
     * Returns a list of all menus filtered by a search string.
     *
     * @param searchstring a search string
     * @return ListWeekMenusDto
     */
    @Async(AsyncConfig.TASK_EXECUTOR_CONTROLLER)
    @RequestMapping(value = "/list/{searchstring}", method = RequestMethod.GET)
    public CompletableFuture<List<WeekMenuDto>> filteredList(@PathVariable("searchstring") String searchstring) {
        return null;
    }

}

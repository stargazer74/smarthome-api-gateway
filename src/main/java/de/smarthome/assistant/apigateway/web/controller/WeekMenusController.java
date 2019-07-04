package de.smarthome.assistant.apigateway.web.controller;

import de.smarthome.assistant.apigateway.configuration.AsyncConfig;
import de.smarthome.assistant.apigateway.web.dto.Ingredient;
import de.smarthome.assistant.apigateway.web.dto.WeekMenuDto;
import de.smarthome.assistant.apigateway.web.dto.WeekMenuListDto;
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
    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public WeekMenuListDto list() {
        // create demo data
        final WeekMenuDto weekMenuDto_1 = new WeekMenuDto();
        weekMenuDto_1.setId(1L);
        weekMenuDto_1.setName("Schweinefleisch mit Klößen");
        final Ingredient ingredient_1 = new Ingredient();
        ingredient_1.setId(1L);
        ingredient_1.setName("Kartoffeln");
        ingredient_1.setAmount("200g");
        final Ingredient ingredient_2 = new Ingredient();
        ingredient_2.setId(2L);
        ingredient_2.setName("Schweinefleisch");
        ingredient_2.setAmount("500g");
        List<Ingredient> ingredients_1 = new ArrayList<>();
        ingredients_1.add(ingredient_1);
        ingredients_1.add(ingredient_2);
        weekMenuDto_1.setIngredients(ingredients_1);

        final WeekMenuDto weekMenuDto_2 = new WeekMenuDto();
        weekMenuDto_2.setId(2L);
        weekMenuDto_2.setName("Grießbrei");
        final Ingredient ingredient_3 = new Ingredient();
        ingredient_3.setId(3L);
        ingredient_3.setName("Grieß");
        ingredient_3.setAmount("200g");
        final Ingredient ingredient_4 = new Ingredient();
        ingredient_4.setId(4L);
        ingredient_4.setName("Milch");
        ingredient_4.setAmount("2l");
        List<Ingredient> ingredients_2 = new ArrayList<>();
        ingredients_2.add(ingredient_3);
        ingredients_2.add(ingredient_4);
        weekMenuDto_2.setIngredients(ingredients_2);

        List<WeekMenuDto> weekMenuDtos = new ArrayList<>();
        weekMenuDtos.add(weekMenuDto_1);
        weekMenuDtos.add(weekMenuDto_2);
        final WeekMenuListDto weekMenuListDto = new WeekMenuListDto();
        weekMenuListDto.setWeekMenuDtos(weekMenuDtos);
        return weekMenuListDto;
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

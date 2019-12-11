/*
 * Copyright (c) 2019. Chris Wohlbrecht
 *
 * MIT License
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 */

package de.smarthome.assistant.apigateway.weekmenu.controller;

import de.smarthome.assistant.apigateway.weekmenu.component.MenuI;
import de.smarthome.assistant.apigateway.weekmenu.controller.dto.DropDownValueListDto;
import de.smarthome.assistant.apigateway.weekmenu.controller.dto.MenuDto;
import de.smarthome.assistant.apigateway.weekmenu.controller.dto.MenuListDto;
import de.smarthome.assistant.apigateway.weekmenu.controller.dto.MenuRequestDto;
import de.smarthome.assistant.apigateway.weekmenu.controller.dto.MenuUpdateRequestDto;
import de.smarthome.assistant.apigateway.weekmenu.controller.util.Enum2DropDownConverter;
import de.smarthome.assistant.apigateway.weekmenu.model.type.UnitOfMeasures;
import java.net.URI;
import java.util.concurrent.ExecutionException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/week-menus")
public class MenusController {

    private final MenuI weekMenu;

    @Autowired
    public MenusController(MenuI weekMenu) {
        this.weekMenu = weekMenu;
    }

    /**
     * Returns a list of all available menus.
     *
     * @return {@link MenuListDto}
     */
    @GetMapping(value = "/list")
    public ResponseEntity<MenuListDto> list() throws ExecutionException, InterruptedException {
        return this.weekMenu.getMenuList().get().map(weekMenu -> ResponseEntity.ok().body(weekMenu))
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    /**
     * Returns a single menu found by id.
     *
     * @param id of the menu
     * @return {@link MenuDto}
     */
    @GetMapping(value = "/{id}")
    public MenuDto get(@PathVariable("id") Long id) {
        return null;
    }

    /**
     * Returns a list of all menus filtered by a search string.
     *
     * @param searchString a search string
     * @return {@link MenuListDto}
     */
    @GetMapping(value = "/list/{searchstring}")
    public MenuListDto filteredList(@PathVariable("searchstring") String searchString) {
        return null;
    }

    /**
     * Insert new given {@link MenuDto} into the database
     *
     * @param weekMenuRequestDto {@link MenuRequestDto}
     * @return {@link MenuDto}
     */
    @PostMapping
    public ResponseEntity<MenuDto> insert(@RequestBody MenuRequestDto weekMenuRequestDto) throws ExecutionException, InterruptedException {
        return this.weekMenu.insert(weekMenuRequestDto).get().map(a -> ResponseEntity.created(URI.create("/" + a.getId())).body(a))
                .orElseGet(() -> ResponseEntity.badRequest().build());
    }

    /**
     * Update existing menu in the database
     *
     * @param menuUpdateRequestDto {@link MenuUpdateRequestDto}
     */
    @ResponseStatus(HttpStatus.OK)
    @PutMapping
    public ResponseEntity<MenuDto> update(@RequestBody MenuUpdateRequestDto menuUpdateRequestDto)
            throws ExecutionException, InterruptedException {
        return this.weekMenu.update(menuUpdateRequestDto).get().map(a -> ResponseEntity.ok().body(a))
                .orElseGet(() -> ResponseEntity.badRequest().build());
    }

    /**
     * Delete menu from database
     *
     * @param menuId a menu id
     */
    @ResponseStatus(HttpStatus.OK)
    @DeleteMapping(value = "/{menuId}")
    public void delete(@PathVariable("menuId") Long menuId) {
        this.weekMenu.delete(menuId);
    }

    /**
     * returns all possible values of {@link UnitOfMeasures}
     *
     * @return {@link DropDownValueListDto}
     */
    @GetMapping(value = "/units-of-measure")
    public DropDownValueListDto getUnitsOfMeasure() {
        return Enum2DropDownConverter.toDropDownList(UnitOfMeasures.values());
    }

}

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

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import de.smarthome.assistant.apigateway.weekmenu.component.MenuI;
import de.smarthome.assistant.apigateway.weekmenu.controller.dto.MenuDto;
import de.smarthome.assistant.apigateway.weekmenu.controller.dto.MenuListDto;
import de.smarthome.assistant.apigateway.weekmenu.persistence.MenuDtoBuilder;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.CompletableFuture;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

@RunWith(SpringRunner.class)
@WebMvcTest
public class MenusControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private MenuI weekMenu;

    @Test
    public void listSuccessTest() throws Exception {
        /*
         * prepare
         */
        final MenuListDto weekMenuListDto = new MenuListDto();
        final MenuDto menuDto = new MenuDtoBuilder.Builder().asPotatoeSoup().build();
        weekMenuListDto.setMenuDtos(List.of(menuDto));
        given(weekMenu.getMenuList()).willReturn(CompletableFuture.completedFuture(Optional.of(weekMenuListDto)));

        /*
         * test
         */
        mockMvc.perform(get("/week-menus/list").contentType(MediaType.APPLICATION_JSON)).andExpect(status().isOk()).andExpect(
                jsonPath("$.menuDtos[0].ingredients[0].unitOfMeasure", is(menuDto.getIngredients().get(0).getUnitOfMeasure().name())));
    }

    @Test
    public void getSuccessTest() {
        assertTrue(true);
    }

    @Test
    public void filteredListSuccessTest() {
        assertTrue(true);
    }

    @Test
    public void updateSuccessTest() {
    }

    @Test
    public void deleteSuccessTest() throws Exception {
        /*
         * test
         */
        mockMvc.perform(delete("/week-menus/" + 1L)).andExpect(status().isOk());
    }
}

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

package de.smarthome.assistant.apigateway.weekmenu.component;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.when;

import de.smarthome.assistant.apigateway.weekmenu.controller.dto.WeekMenuListDto;
import de.smarthome.assistant.apigateway.weekmenu.model.type.UnitOfMeasures;
import de.smarthome.assistant.apigateway.weekmenu.service.external.WeekMenusService;
import de.smarthome.assistant.apigateway.weekmenu.service.external.dto.IngredientResponseDto;
import de.smarthome.assistant.apigateway.weekmenu.service.external.dto.WeekMenuListResponseDto;
import de.smarthome.assistant.apigateway.weekmenu.service.external.dto.WeekMenuResponseDto;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class WeekMenuIImplTest {

    @MockBean
    private WeekMenusService weekMenusService;

    private WeekMenuListResponseDto weekMenuListResponseDto;

    @Before
    public void setUp(){
        this.weekMenuListResponseDto = new WeekMenuListResponseDto();
        final WeekMenuResponseDto weekMenuResponseDto = new WeekMenuResponseDto();
        weekMenuResponseDto.setId(1L);
        weekMenuResponseDto.setName("Kartoffelsuppe");
        final IngredientResponseDto potatoes = new IngredientResponseDto();
        potatoes.setAmount(2.6f);
        potatoes.setId(1L);
        potatoes.setName("Kartoffeln");
        potatoes.setUnitOfMeasure(UnitOfMeasures.GRAMM);
        final IngredientResponseDto meat = new IngredientResponseDto();
        meat.setAmount(300f);
        meat.setId(2L);
        meat.setName("Schweinefleisch");
        meat.setUnitOfMeasure(UnitOfMeasures.GRAMM);
        List<IngredientResponseDto> ingredients = new ArrayList<>();
        ingredients.add(potatoes);
        ingredients.add(meat);
        weekMenuResponseDto.setIngredients(ingredients);
        weekMenuListResponseDto.setWeekMenuResponseDtos(List.of(weekMenuResponseDto));
    }

    @Test
    public void getWeekMenuListSuccessTest() throws ExecutionException, InterruptedException {
        /*
         * prepare
         */
        when(this.weekMenusService.getWeekMenuList()).thenReturn(CompletableFuture.completedFuture(Optional.of(this.weekMenuListResponseDto)));
        final WeekMenuIImpl weekMenu = new WeekMenuIImpl(this.weekMenusService);
        final CompletableFuture<Optional<WeekMenuListDto>> weekMenuList = weekMenu.getWeekMenuList();
        final Optional<WeekMenuListDto> weekMenuListDto = weekMenuList.get();

        /*
         * test
         */
        assertTrue(weekMenuListDto.isPresent());
        assertEquals(1, weekMenuListDto.get().getWeekMenuDtos().size());
        assertEquals("Kartoffelsuppe", weekMenuListDto.get().getWeekMenuDtos().get(0).getName());
        assertEquals(2, weekMenuListDto.get().getWeekMenuDtos().get(0).getIngredients().size());
        assertEquals("GRAMM", weekMenuListDto.get().getWeekMenuDtos().get(0).getIngredients().get(0).getUnitOfMeasure());
    }
}

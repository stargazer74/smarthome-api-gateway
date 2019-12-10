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
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import de.smarthome.assistant.apigateway.weekmenu.component.mapper.MenuDtoMapper;
import de.smarthome.assistant.apigateway.weekmenu.controller.dto.MenuDto;
import de.smarthome.assistant.apigateway.weekmenu.controller.dto.MenuListDto;
import de.smarthome.assistant.apigateway.weekmenu.controller.dto.MenuRequestDto;
import de.smarthome.assistant.apigateway.weekmenu.persistence.MenuDtoBuilder;
import de.smarthome.assistant.apigateway.weekmenu.service.external.MenuServiceImpl;
import de.smarthome.assistant.apigateway.weekmenu.service.external.dto.MenuListResponseDto;
import de.smarthome.assistant.apigateway.weekmenu.service.external.dto.MenuResponseDto;
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
public class MenuIImplTest {

    @MockBean
    private MenuServiceImpl weekMenusService;

    private MenuListResponseDto menuListResponseDto;

    @Before
    public void setUp() {
        this.menuListResponseDto = new MenuListResponseDto();
        MenuDto menuDto = new MenuDtoBuilder.Builder().asPotatoeSoup().build();
        final MenuResponseDto menuResponseDto = MenuDtoMapper.INSTANCE.menuDto2MenuResponseDto(menuDto);
        menuListResponseDto.setMenuResponseDtos(List.of(menuResponseDto));
    }

    @Test
    public void getMenuListSuccessTest() throws ExecutionException, InterruptedException {
        /*
         * prepare
         */
        when(this.weekMenusService.getWeekMenuList()).thenReturn(CompletableFuture.completedFuture(Optional.of(this.menuListResponseDto)));

        /*
         * call
         */
        final MenuIImpl weekMenu = new MenuIImpl(this.weekMenusService);
        final CompletableFuture<Optional<MenuListDto>> weekMenuList = weekMenu.getMenuList();
        final Optional<MenuListDto> weekMenuListDto = weekMenuList.get();

        /*
         * test
         */
        assertTrue(weekMenuListDto.isPresent());
        assertEquals(1, weekMenuListDto.get().getMenuDtos().size());
        assertEquals("Kartoffelsuppe", weekMenuListDto.get().getMenuDtos().get(0).getName());
        assertEquals(2, weekMenuListDto.get().getMenuDtos().get(0).getIngredients().size());
        assertEquals("GRAMM", weekMenuListDto.get().getMenuDtos().get(0).getIngredients().get(0).getUnitOfMeasure().name());
        assertEquals("g", weekMenuListDto.get().getMenuDtos().get(0).getIngredients().get(0).getUnitOfMeasure().getDisplayString());
    }

    @Test
    public void insertSuccessTest() throws ExecutionException, InterruptedException {
        /*
         * prepare
         */
        when(this.weekMenusService.insert(any()))
                .thenReturn(CompletableFuture.completedFuture(Optional.of(this.menuListResponseDto.getMenuResponseDtos().get(0))));

        /*
         * call
         */
        final MenuIImpl menu = new MenuIImpl(this.weekMenusService);
        final Optional<MenuDto> menuDto = menu.insert(new MenuRequestDto()).get();

        /*
         * test
         */
        assertTrue(menuDto.isPresent());
        assertEquals(2, menuDto.get().getIngredients().size());
        assertEquals("Kartoffelsuppe", menuDto.get().getName());
    }
}

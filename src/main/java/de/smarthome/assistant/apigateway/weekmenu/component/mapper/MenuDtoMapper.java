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

package de.smarthome.assistant.apigateway.weekmenu.component.mapper;

import de.smarthome.assistant.apigateway.weekmenu.controller.dto.MenuDto;
import de.smarthome.assistant.apigateway.weekmenu.controller.dto.MenuListDto;
import de.smarthome.assistant.apigateway.weekmenu.service.external.dto.MenuResponseDto;
import de.smarthome.assistant.apigateway.weekmenu.service.external.dto.MenuListResponseDto;
import java.util.List;
import java.util.stream.Collectors;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface MenuDtoMapper {

    MenuDtoMapper INSTANCE = Mappers.getMapper(MenuDtoMapper.class);

    @Mapping(source = "menuResponseDtos", target = "menuDtos", qualifiedByName = "menuListMap")
    MenuListDto menuListResponseDto2MenuListDto(MenuListResponseDto weekMenuListResponseDto);

    MenuDto menuResponseDto2MenuDto(MenuResponseDto weekMenuResponseDto);

    MenuResponseDto menuDto2MenuResponseDto(MenuDto menuDto);

    @Named("weekMenuListMap")
    default List<MenuDto> menuListMap(List<MenuResponseDto> menuResponseDtos) {
        return menuResponseDtos.stream().map(a -> {
            final MenuDto menuDto = new MenuDto();
            menuDto.setId(a.getId());
            menuDto.setName(a.getName());
            menuDto.setIngredients(a.getIngredients().stream().map(IngredientMapper.INSTANCE::ingredientResponseDto2IngredientDto)
                    .collect(Collectors.toList()));
            return menuDto;
        }).collect(Collectors.toList());
    }
}

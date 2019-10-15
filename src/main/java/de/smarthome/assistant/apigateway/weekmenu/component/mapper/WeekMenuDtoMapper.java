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

import de.smarthome.assistant.apigateway.weekmenu.controller.dto.WeekMenuDto;
import de.smarthome.assistant.apigateway.weekmenu.controller.dto.WeekMenuListDto;
import de.smarthome.assistant.apigateway.weekmenu.service.external.dto.WeekMenuListResponseDto;
import de.smarthome.assistant.apigateway.weekmenu.service.external.dto.WeekMenuResponseDto;
import java.util.List;
import java.util.stream.Collector;
import java.util.stream.Collectors;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface WeekMenuDtoMapper {

    WeekMenuDtoMapper INSTANCE = Mappers.getMapper(WeekMenuDtoMapper.class);

    @Mapping(source = "weekMenuResponseDtos", target = "weekMenuDtos", qualifiedByName = "weekMenuMap")
    WeekMenuListDto weekMenuListResponseDto2WeekMenuListDto(WeekMenuListResponseDto weekMenuListResponseDto);

    WeekMenuDto weekMenuResponseDto2WeekMenuDto(WeekMenuResponseDto weekMenuResponseDto);

    @Named("weekMenuMap")
    default List<WeekMenuDto> weekMenuMap(List<WeekMenuResponseDto> weekMenuResponseDtos) {
        return weekMenuResponseDtos.stream().map(a -> {
            final WeekMenuDto weekMenuDto = new WeekMenuDto();
            weekMenuDto.setId(a.getId());
            weekMenuDto.setName(a.getName());
            weekMenuDto.setIngredients(a.getIngredients().stream().map(IngredientMapper.INSTANCE::ingredientResponseDto2IngredientDto)
                    .collect(Collectors.toList()));
            return weekMenuDto;
        }).collect(Collectors.toList());
    }
}

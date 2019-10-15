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

package de.smarthome.assistant.apigateway.weekmenu.controller.util;


import de.smarthome.assistant.apigateway.weekmenu.controller.dto.DropDownValueDto;
import de.smarthome.assistant.apigateway.weekmenu.controller.dto.DropDownValueListDto;
import de.smarthome.assistant.apigateway.weekmenu.model.type.IConvertToDisplayName;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Enum2DropDownConverter {
    public static DropDownValueListDto toDropDownList(IConvertToDisplayName[] values) {
        DropDownValueListDto dto = new DropDownValueListDto();
        dto.setDropDownValueDtos(Stream.of(values).map(a -> {
            final DropDownValueDto dropDownValueDto = new DropDownValueDto();
            dropDownValueDto.setValue(a.toString());
            dropDownValueDto.setViewValue(a.getDisplayString());
            return dropDownValueDto;
        }).collect(Collectors.toList()));
        return dto;
    }
}

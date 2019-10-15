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

import de.smarthome.assistant.apigateway.configuration.AsyncConfig;
import de.smarthome.assistant.apigateway.weekmenu.component.mapper.WeekMenuDtoMapper;
import de.smarthome.assistant.apigateway.weekmenu.controller.dto.WeekMenuDto;
import de.smarthome.assistant.apigateway.weekmenu.controller.dto.WeekMenuListDto;
import de.smarthome.assistant.apigateway.weekmenu.controller.dto.WeekMenuRequestDto;
import de.smarthome.assistant.apigateway.weekmenu.service.external.WeekMenusService;
import java.util.Optional;
import java.util.concurrent.CompletableFuture;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

@Component
public class WeekMenuIImpl implements WeekMenuI {

    private final WeekMenusService weekMenusService;

    public WeekMenuIImpl(WeekMenusService weekMenusService) {
        this.weekMenusService = weekMenusService;
    }

    @Override
    @Async(AsyncConfig.TASK_EXECUTOR_SERVICE)
    public CompletableFuture<Optional<WeekMenuListDto>> getWeekMenuList() {
        return weekMenusService.getWeekMenuList()
                .thenApply(a -> a.map(WeekMenuDtoMapper.INSTANCE::weekMenuListResponseDto2WeekMenuListDto));
    }

    @Override
    public CompletableFuture<Optional<WeekMenuDto>> insert(WeekMenuRequestDto weekMenuRequestDto) {
        return this.weekMenusService.insert(weekMenuRequestDto)
                .thenApply(a -> a.map(WeekMenuDtoMapper.INSTANCE::weekMenuResponseDto2WeekMenuDto));
    }
}

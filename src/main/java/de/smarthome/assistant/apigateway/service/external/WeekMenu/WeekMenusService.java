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

package de.smarthome.assistant.apigateway.service.external.WeekMenu;

import de.smarthome.assistant.apigateway.configuration.AsyncConfig;
import de.smarthome.assistant.apigateway.service.external.WeekMenu.dto.IngredientResponseDto;
import de.smarthome.assistant.apigateway.service.external.WeekMenu.dto.WeekMenuListResponseDto;
import de.smarthome.assistant.apigateway.service.external.WeekMenu.dto.WeekMenuResponseDto;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.CompletableFuture;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClientException;

@Service
@PropertySource({ "classpath:conf/weekmenu.properties" })
@Slf4j
public class WeekMenusService {

    @Value("${weekmenu.service.external.url}")
    private String serviceUrl;

    @Value("${weekmenu.service.external.port}")
    private String servicePort;

    @Async(AsyncConfig.TASK_EXECUTOR_SERVICE)
    public CompletableFuture<Optional<WeekMenuListResponseDto>> getWeekMenuList(){
        try{
            log.info("Looking up weekmenu service");
//            final UriComponents uriComponents = UriComponentsBuilder.newInstance().scheme("http").host(serviceUrl).path("keineAhnung").port(servicePort)
//                    .build();
//            final RestTemplate restTemplate = new RestTemplate();
//            final String uriString = uriComponents.toUriString();
//            final WeekMenuListResponseDto weekMenuListResponseDto = restTemplate.getForObject(uriString, WeekMenuListResponseDto.class);
            final WeekMenuListResponseDto weekMenuListResponseDto1 = mockData();
            return CompletableFuture.completedFuture(Optional.of(weekMenuListResponseDto1));
        } catch (RestClientException e) {
            log.info(e.getMessage());
            return CompletableFuture.completedFuture(Optional.empty());
        }
    }

    private WeekMenuListResponseDto mockData(){
        // create demo data
        final WeekMenuResponseDto weekMenuResponseDto_1 = new WeekMenuResponseDto();
        weekMenuResponseDto_1.setId(1L);
        weekMenuResponseDto_1.setName("Schweinefleisch mit Klößen");
        final IngredientResponseDto ingredientResponse_1 = new IngredientResponseDto();
        ingredientResponse_1.setId(1L);
        ingredientResponse_1.setName("Kartoffeln");
        ingredientResponse_1.setAmount("200");
        ingredientResponse_1.setUnitOfMeasure("kg");
        ingredientResponse_1.setUnitOfMeasureString("Kilogramm");
        final IngredientResponseDto ingredientResponse_2 = new IngredientResponseDto();
        ingredientResponse_2.setId(2L);
        ingredientResponse_2.setName("Rinderfleisch");
        ingredientResponse_2.setAmount("500");
        ingredientResponse_2.setUnitOfMeasure("g");
        ingredientResponse_2.setUnitOfMeasureString("Gramm");
        List<IngredientResponseDto> ingredients_1 = new ArrayList<>();
        ingredients_1.add(ingredientResponse_1);
        ingredients_1.add(ingredientResponse_2);
        weekMenuResponseDto_1.setIngredients(ingredients_1);

        final WeekMenuResponseDto weekMenuResponseDto_2 = new WeekMenuResponseDto();
        weekMenuResponseDto_2.setId(2L);
        weekMenuResponseDto_2.setName("Grießbrei");
        final IngredientResponseDto ingredientResponse_3 = new IngredientResponseDto();
        ingredientResponse_3.setId(3L);
        ingredientResponse_3.setName("Grieß");
        ingredientResponse_3.setAmount("200");
        ingredientResponse_3.setUnitOfMeasure("l");
        ingredientResponse_3.setUnitOfMeasureString("Liter");
        final IngredientResponseDto ingredientResponse_4 = new IngredientResponseDto();
        ingredientResponse_4.setId(4L);
        ingredientResponse_4.setName("Zucker");
        ingredientResponse_4.setAmount("2");
        ingredientResponse_4.setUnitOfMeasure("l");
        ingredientResponse_4.setUnitOfMeasureString("Liter");
        List<IngredientResponseDto> ingredients_2 = new ArrayList<>();
        ingredients_2.add(ingredientResponse_3);
        ingredients_2.add(ingredientResponse_4);
        weekMenuResponseDto_2.setIngredients(ingredients_2);

        List<WeekMenuResponseDto> weekMenuResponseDtos = new ArrayList<>();
        weekMenuResponseDtos.add(weekMenuResponseDto_1);
        weekMenuResponseDtos.add(weekMenuResponseDto_2);
        WeekMenuListResponseDto weekMenuListResponseDto = new WeekMenuListResponseDto();
        weekMenuListResponseDto.setWeekMenuResponseDtos(weekMenuResponseDtos);
        return weekMenuListResponseDto;
    }

}

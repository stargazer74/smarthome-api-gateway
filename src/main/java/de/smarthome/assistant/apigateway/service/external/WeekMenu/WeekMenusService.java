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
import de.smarthome.assistant.apigateway.service.external.WeekMenu.dto.WeekMenuListResponseDto;
import java.util.Optional;
import java.util.concurrent.CompletableFuture;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;

@Service
@PropertySource({"classpath:conf/weekmenu.properties"})
@Slf4j
public class WeekMenusService {

    @Value("${weekmenu.service.external.url}")
    private String serviceUrl;

    @Value("${weekmenu.service.external.port}")
    private String servicePort;

    @Async(AsyncConfig.TASK_EXECUTOR_SERVICE)
    public CompletableFuture<Optional<WeekMenuListResponseDto>> getWeekMenuList() {
        try {
            log.info("Looking up weekmenu service");
            final UriComponents uriComponents = UriComponentsBuilder.newInstance().scheme("http").host(serviceUrl).path("/menu-service/list").port(servicePort)
                    .build();
            final RestTemplate restTemplate = new RestTemplate();
            final String uriString = uriComponents.toUriString();
            final WeekMenuListResponseDto weekMenuListResponseDto = restTemplate.getForObject(uriString, WeekMenuListResponseDto.class);
            return CompletableFuture.completedFuture(Optional.ofNullable(weekMenuListResponseDto));
        } catch (RestClientException e) {
            log.info("Can't connect to weekmenu service due the following error: " + e.getMessage());
            return CompletableFuture.completedFuture(Optional.empty());
        }
    }
}

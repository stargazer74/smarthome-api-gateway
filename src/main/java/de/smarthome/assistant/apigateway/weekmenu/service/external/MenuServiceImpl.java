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

package de.smarthome.assistant.apigateway.weekmenu.service.external;

import de.smarthome.assistant.apigateway.configuration.AsyncConfig;
import de.smarthome.assistant.apigateway.weekmenu.controller.dto.MenuDto;
import de.smarthome.assistant.apigateway.weekmenu.controller.dto.MenuRequestDto;
import de.smarthome.assistant.apigateway.weekmenu.controller.dto.MenuUpdateRequestDto;
import de.smarthome.assistant.apigateway.weekmenu.service.external.dto.MenuListResponseDto;
import de.smarthome.assistant.apigateway.weekmenu.service.external.dto.MenuResponseDto;
import java.util.Optional;
import java.util.concurrent.CompletableFuture;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;

@Service
@PropertySource({ "classpath:conf/weekmenu.properties" })
@Slf4j
public class MenuServiceImpl implements MenuServiceI{

    @Value("${weekmenu.service.external.url}")
    private String serviceUrl;

    @Value("${weekmenu.service.external.port}")
    private String servicePort;

    /**
     * calling the week menu service and try to get all week menus
     *
     * @return {@link MenuListResponseDto}
     */
    @Async(AsyncConfig.TASK_EXECUTOR_SERVICE)
    @Override
    public CompletableFuture<Optional<MenuListResponseDto>> getWeekMenuList() {
        try {
            log.info("Looking up weekmenu service for list of week menus");
            final UriComponents uriComponents = UriComponentsBuilder.newInstance().scheme("http").host(serviceUrl).path("/menu/list")
                    .port(servicePort).build();
            final RestTemplate restTemplate = new RestTemplate();
            final String uriString = uriComponents.toUriString();
            final MenuListResponseDto weekMenuListResponseDto = restTemplate.getForObject(uriString, MenuListResponseDto.class);
            return CompletableFuture.completedFuture(Optional.ofNullable(weekMenuListResponseDto));
        } catch (RestClientException e) {
            log.info("Can't connect to weekmenu service due the following error: " + e.getMessage());
            return CompletableFuture.completedFuture(Optional.empty());
        }
    }

    /**
     *  Calling the week menu service and try to insert a new week menu into the database.
     *
     * @param weekMenuRequestDto {@link MenuRequestDto}
     * @return {@link MenuResponseDto}
     */
    @Async(AsyncConfig.TASK_EXECUTOR_SERVICE)
    @Override
    public CompletableFuture<Optional<MenuResponseDto>> insert(MenuRequestDto weekMenuRequestDto) {
        try {
            log.info("Looking up weekmenu service to insert a week menu");
            final UriComponents uriComponents = UriComponentsBuilder.newInstance().scheme("http").host(serviceUrl).path("/menu")
                    .port(servicePort).build();
            final RestTemplate restTemplate = new RestTemplate();
            final String uriString = uriComponents.toUriString();
            final MenuResponseDto weekMenuResponseDto = restTemplate.postForObject(uriString, weekMenuRequestDto, MenuResponseDto.class);
            return CompletableFuture.completedFuture(Optional.ofNullable(weekMenuResponseDto));
        } catch (RestClientException e) {
            log.info("Can't connect to weekmenu service due the following error: " + e.getMessage());
            return CompletableFuture.completedFuture(Optional.empty());
        }
    }

    /**
     * Update an existing menu
     *
     * @param menuUpdateRequestDto {@link MenuUpdateRequestDto}
     */
    @Async(AsyncConfig.TASK_EXECUTOR_SERVICE)
    @Override
    public CompletableFuture<Optional<MenuResponseDto>> update(MenuUpdateRequestDto menuUpdateRequestDto) {
        try {
            log.info("Looking up weekmenu service to update a week menu");
            final UriComponents uriComponents = UriComponentsBuilder.newInstance().scheme("http").host(serviceUrl).path("/menu")
                    .port(servicePort).build();
            final RestTemplate restTemplate = new RestTemplate();
            final String uriString = uriComponents.toUriString();
            final HttpHeaders httpHeaders = new HttpHeaders();
            final HttpEntity<MenuUpdateRequestDto> menuResponseDtoHttpEntity = new HttpEntity<>(menuUpdateRequestDto, httpHeaders);
            final ResponseEntity<MenuResponseDto> menuResponseDtoResponseEntity = restTemplate
                    .exchange(uriString, HttpMethod.PUT, menuResponseDtoHttpEntity, MenuResponseDto.class);
            final MenuResponseDto body = menuResponseDtoResponseEntity.getBody();
            return CompletableFuture.completedFuture(Optional.ofNullable(body));
        } catch (RestClientException e) {
            log.info("Can't connect to weekmenu service due the following error: " + e.getMessage());
            return CompletableFuture.completedFuture(Optional.empty());
        }
    }

    /**
     * delete a menu by given id
     *
     * @param menuId menu id to be deleted
     */
    @Override
    public void delete(Long menuId) {
        log.info("Looking up weekmenu service to delete a week menu with id: " + menuId);
        final UriComponents uriComponents = UriComponentsBuilder.newInstance().scheme("http").host(serviceUrl).path("/menu/" + menuId)
                .port(servicePort).build();
        final RestTemplate restTemplate = new RestTemplate();
        final String uriString = uriComponents.toUriString();
        restTemplate.delete(uriString);
    }
}

package de.smarthome.assistant.apigateway.component.weekmenu;

import de.smarthome.assistant.apigateway.component.weekmenu.mapper.WeekMenuDtoMapper;
import de.smarthome.assistant.apigateway.configuration.AsyncConfig;
import de.smarthome.assistant.apigateway.service.external.WeekMenu.WeekMenusService;
import de.smarthome.assistant.apigateway.web.dto.WeekMenuListDto;
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
}

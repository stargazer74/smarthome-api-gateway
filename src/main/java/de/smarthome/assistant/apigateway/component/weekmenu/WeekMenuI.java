package de.smarthome.assistant.apigateway.component.weekmenu;

import de.smarthome.assistant.apigateway.web.dto.WeekMenuListDto;
import java.util.Optional;
import java.util.concurrent.CompletableFuture;

public interface WeekMenuI {

    CompletableFuture<Optional<WeekMenuListDto>> getWeekMenuList();

}

package de.smarthome.assistant.apigateway.web.dto;

import java.util.List;
import javax.validation.constraints.NotNull;

public class ListWeekMenusDto {

    @NotNull
    private List<WeekMenuDto> weekMenuDtos;

    public List<WeekMenuDto> getWeekMenuDtos() {
        return weekMenuDtos;
    }

    public void setWeekMenuDtos(List<WeekMenuDto> weekMenuDtos) {
        this.weekMenuDtos = weekMenuDtos;
    }
}

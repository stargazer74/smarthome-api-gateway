package de.smarthome.assistant.apigateway.web.dto;

import java.util.ArrayList;
import java.util.List;
import javax.validation.constraints.NotNull;

public class WeekMenuListDto {

    @NotNull
    private List<WeekMenuDto> weekMenuDtos = new ArrayList<>();

    public List<WeekMenuDto> getWeekMenuDtos() {
        return weekMenuDtos;
    }

    public void setWeekMenuDtos(List<WeekMenuDto> weekMenuDtos) {
        this.weekMenuDtos = weekMenuDtos;
    }
}

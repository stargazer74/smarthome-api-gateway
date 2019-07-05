package de.smarthome.assistant.apigateway.service.external.WeekMenu.dto;

import java.util.List;

public class WeekMenuListResponseDto {

    public List<WeekMenuResponseDto> weekMenuResponseDtos;

    public List<WeekMenuResponseDto> getWeekMenuResponseDtos() {
        return weekMenuResponseDtos;
    }

    public void setWeekMenuResponseDtos(List<WeekMenuResponseDto> weekMenuResponseDtos) {
        this.weekMenuResponseDtos = weekMenuResponseDtos;
    }
}

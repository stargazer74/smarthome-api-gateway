package de.smarthome.assistant.apigateway.component.weekmenu.mapper;

import de.smarthome.assistant.apigateway.service.external.WeekMenu.dto.WeekMenuListResponseDto;
import de.smarthome.assistant.apigateway.web.dto.WeekMenuListDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface WeekMenuDtoMapper {

    WeekMenuDtoMapper INSTANCE = Mappers.getMapper(WeekMenuDtoMapper.class);

    @Mapping(source = "weekMenuResponseDtos", target = "weekMenuDtos")
    WeekMenuListDto weekMenuListResponseDto2WeekMenuListDto(WeekMenuListResponseDto weekMenuListResponseDto);
}

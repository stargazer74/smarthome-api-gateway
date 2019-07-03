package de.smarthome.assistant.apigateway.web.dto;

import javax.validation.constraints.NotEmpty;
import lombok.EqualsAndHashCode;

public class WeekMenuDto {

    @NotEmpty
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}

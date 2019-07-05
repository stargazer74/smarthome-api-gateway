package de.smarthome.assistant.apigateway.service.external.WeekMenu.dto;

import java.util.List;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

public class WeekMenuResponseDto {

    @NotNull
    private Long id;

    @NotEmpty
    private String name;

    private List<IngredientResponseDto> ingredientResponseDtos;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<IngredientResponseDto> getIngredientResponseDtos() {
        return ingredientResponseDtos;
    }

    public void setIngredientResponseDtos(List<IngredientResponseDto> ingredientResponseDtos) {
        this.ingredientResponseDtos = ingredientResponseDtos;
    }
}

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

package de.smarthome.assistant.apigateway.weekmenu.persistence;

import de.smarthome.assistant.apigateway.weekmenu.controller.dto.IngredientDto;
import de.smarthome.assistant.apigateway.weekmenu.controller.dto.MenuDto;
import de.smarthome.assistant.apigateway.weekmenu.model.type.UnitOfMeasures;
import java.util.ArrayList;
import java.util.List;
import org.apache.maven.model.Build;

public class MenuDtoBuilder {

    private MenuDtoBuilder() {
    }

    public static class Builder {

        private MenuDto menuDto = new MenuDto();

        private String name;

        private Long id;

        private List<IngredientDto> ingredientDtoList = new ArrayList<>();

        public Builder withIngredient(IngredientDto ingredientDto) {
            this.ingredientDtoList.add(ingredientDto);
            return this;
        }

        public Builder withName(String name) {
            this.name = name;
            return this;
        }

        public Builder withId(Long id) {
            this.id = id;
            return this;
        }

        public Builder asPotatoeSoup() {
            this.withName("Kartoffelsuppe");
            this.withId(1L);
            final IngredientDto potatoes = new IngredientDto();
            potatoes.setAmount(2.6f);
            potatoes.setId(1L);
            potatoes.setName("Kartoffeln");
            potatoes.setUnitOfMeasure(UnitOfMeasures.GRAMM);
            final IngredientDto meat = new IngredientDto();
            meat.setAmount(300f);
            meat.setId(2L);
            meat.setName("Schweinefleisch");
            meat.setUnitOfMeasure(UnitOfMeasures.GRAMM);
            this.withIngredient(potatoes);
            this.withIngredient(meat);
            return this;
        }

        public MenuDto build() {
            this.menuDto.setName(this.name);
            this.menuDto.setId(this.id);
            this.menuDto.setIngredients(this.ingredientDtoList);
            return this.menuDto;
        }
    }
}

package com.core.kubejsvoltaic.wrapper;

import com.core.kubejsvoltaic.extension.GasIngredientKJS;
import dev.latvian.mods.kubejs.typings.Info;
import voltaic.common.recipe.recipeutils.GasIngredient;

public interface GasIngredientWrapper {
    @Info("Returns an instance of Voltaic's GasIngredient of the input")
    static GasIngredient of(GasIngredient ingredient) {
        return ingredient;
    }

    @Info("Returns an instance of Voltaic's GasIngredient of the input, with the given amount")
    static GasIngredient of(GasIngredient ingredient, int amount) {
        return ((GasIngredientKJS) ingredient).kjs$withAmount(amount);
    }

    @Info("Returns an instance of Voltaic's GasIngredient of the input, with the given amount and temperature")
    static GasIngredient of(GasIngredient ingredient, int amount, int temperature) {
        return ((GasIngredientKJS) ingredient).kjs$withAmountAndTemperature(amount, temperature);
    }

    @Info("Returns an instance of Voltaic's GasIngredient of the input, with the given amount, temperature and pressure")
    static GasIngredient of(GasIngredient ingredient, int amount, int temperature, int pressure) {
        return ((GasIngredientKJS) ingredient).kjs$withAmountTemperatureAndPressure(amount, temperature, pressure);
    }
}

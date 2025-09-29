package com.core.kubejsvoltaic.mixin;

import com.core.kubejsvoltaic.extension.GasIngredientKJS;
import net.minecraft.tags.TagKey;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import voltaic.api.gas.Gas;
import voltaic.common.recipe.recipeutils.GasIngredient;

@Mixin(GasIngredient.class)
public class GasIngredientMixin implements GasIngredientKJS {
    @Shadow(remap = false) private TagKey<Gas> tag;
    @Shadow(remap = false) private Gas gas;
    @Shadow(remap = false) private int amount;
    @Shadow(remap = false) private int temperature;
    @Shadow(remap = false) private int pressure;

    @Override
    public GasIngredient kjs$self() {
        return (GasIngredient) (Object) this;
    }

    @Override
    public GasIngredient kjs$copy() {
        if (tag != null) {
            return new GasIngredient(tag, amount, temperature, pressure);
        }

        return new GasIngredient(gas, amount, temperature, pressure);
    }

    @Override
    public int kjs$getAmount() {
        return amount;
    }

    @Override
    public GasIngredient kjs$withAmount(int amount) {
        GasIngredient copy = kjs$copy();
        ((GasIngredientMixin) (Object) copy).amount = amount;
        return copy;
    }

    @Override
    public int kjs$getPressure() {
        return pressure;
    }

    @Override
    public GasIngredient kjs$withPressure(int pressure) {
        kjsvoltaic$checkValidPressure(pressure);

        GasIngredient copy = kjs$copy();
        ((GasIngredientMixin) (Object) copy).pressure = pressure;
        return copy;
    }

    @Override
    public int kjs$getTemperature() {
        return temperature;
    }

    @Override
    public GasIngredient kjs$withTemperature(int temperature) {
        kjsvoltaic$checkValidTemperature(temperature);

        GasIngredient copy = kjs$copy();
        ((GasIngredientMixin) (Object) copy).temperature = temperature;
        return copy;
    }

    @Override
    public GasIngredient kjs$withAmountAndPressure(int amount, int pressure) {
        kjsvoltaic$checkValidPressure(pressure);

        GasIngredient copy = kjs$copy();
        GasIngredientMixin mixin = (GasIngredientMixin) (Object) copy;

        mixin.amount = amount;
        mixin.pressure = pressure;
        return copy;
    }

    @Override
    public GasIngredient kjs$withAmountAndTemperature(int amount, int temperature) {
        kjsvoltaic$checkValidTemperature(temperature);

        GasIngredient copy = kjs$copy();
        GasIngredientMixin mixin = (GasIngredientMixin) (Object) copy;

        mixin.amount = amount;
        mixin.temperature = temperature;
        return copy;
    }

    @Override
    public GasIngredient kjs$withTemperatureAndPressure(int temperature, int pressure) {
        kjsvoltaic$checkValidTemperature(temperature);
        kjsvoltaic$checkValidPressure(pressure);

        GasIngredient copy = kjs$copy();
        GasIngredientMixin mixin = (GasIngredientMixin) (Object) copy;

        mixin.temperature = temperature;
        mixin.pressure = pressure;
        return copy;
    }

    @Override
    public GasIngredient kjs$withAmountTemperatureAndPressure(int amount, int temperature, int pressure) {
        kjsvoltaic$checkValidTemperature(temperature);
        kjsvoltaic$checkValidPressure(pressure);

        GasIngredient copy = kjs$copy();
        GasIngredientMixin mixin = (GasIngredientMixin) (Object) copy;

        mixin.amount = amount;
        mixin.temperature = temperature;
        mixin.pressure = pressure;
        return copy;
    }

    @Unique
    private void kjsvoltaic$checkValidPressure(int pressure) {
        if (pressure < 1) {
            throw new UnsupportedOperationException("You cannot have a pressure less than 1");
        }
    }

    @Unique
    private void kjsvoltaic$checkValidTemperature(int temperature) {
        if (temperature < 1) {
            throw new UnsupportedOperationException("The temperature cannot drop below absolute zero");
        }
    }
}

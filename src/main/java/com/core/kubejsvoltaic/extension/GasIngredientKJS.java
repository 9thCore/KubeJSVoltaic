package com.core.kubejsvoltaic.extension;

import com.core.kubejsvoltaic.recipe.match.GasMatch;
import com.core.kubejsvoltaic.util.gas.GasUtil;
import com.mojang.serialization.Codec;
import dev.latvian.mods.kubejs.core.NoMixinException;
import dev.latvian.mods.kubejs.recipe.match.Replaceable;
import dev.latvian.mods.kubejs.typings.Info;
import dev.latvian.mods.kubejs.util.WithCodec;
import dev.latvian.mods.rhino.Context;
import dev.latvian.mods.rhino.util.RemapPrefixForJS;
import voltaic.api.gas.GasStack;
import voltaic.common.recipe.recipeutils.GasIngredient;

@RemapPrefixForJS("kjs$")
public interface GasIngredientKJS extends WithCodec, Replaceable, GasMatch {

    default GasIngredient kjs$self() { throw new NoMixinException(); }
    default GasIngredient kjs$copy() { throw new NoMixinException(); }

    default int kjs$getAmount() { throw new NoMixinException(); }

    default int kjs$getPressure() { throw new NoMixinException(); }

    default int kjs$getTemperature() { throw new NoMixinException(); }

    @Info("Return a copy of this GasIngredient with the given amount")
    default GasIngredient kjs$withAmount(int amount) { throw new NoMixinException(); }

    @Info("Return a copy of this GasIngredient with the given pressure")
    default GasIngredient kjs$withPressure(int pressure) { throw new NoMixinException(); }

    @Info("Return a copy of this GasIngredient with the given temperature")
    default GasIngredient kjs$withTemperature(int temperature) { throw new NoMixinException(); }

    @Info("Return a copy of this GasIngredient with the given amount and temperature")
    default GasIngredient kjs$withAmountAndTemperature(int amount, int temperature) {throw new NoMixinException(); }

    @Info("Return a copy of this GasIngredient with the given amount and pressure")
    default GasIngredient kjs$withAmountAndPressure(int amount, int pressure) {throw new NoMixinException(); }

    @Info("Return a copy of this GasIngredient with the given temperature and pressure")
    default GasIngredient kjs$withTemperatureAndPressure(int temperature, int pressure) {throw new NoMixinException(); }

    @Info("Return a copy of this GasIngredient with the given amount, temperature and pressure")
    default GasIngredient kjs$withAmountTemperatureAndPressure(int amount, int temperature, int pressure) {throw new NoMixinException(); }

    @Override
    default Codec<?> getCodec(Context cx) {
        return GasIngredient.CODEC.codec();
    }

    @Override
    default Object replaceThisWith(Context cx, Object with) {
        var t = kjs$self();
        var r = GasUtil.wrapIngredient(with);

        if (!r.equals(t)) {
            return r;
        }

        return this;
    }

    @Override
    default boolean matches(Context cx, GasStack stack, boolean exact) {
        if (stack.isEmpty()) {
            return false;
        }

        return kjs$self().testGas(stack, exact, exact);
    }

    @Override
    default boolean matches(Context cx, GasIngredient in, boolean exact) {
        if (in == null) {
            return false;
        }

        for (GasStack stack : in.getMatchingGases()) {
            if (matches(cx, stack, exact)) {
                return true;
            }
        }

        return false;
    }
}

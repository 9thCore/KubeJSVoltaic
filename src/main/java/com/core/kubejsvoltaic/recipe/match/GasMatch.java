package com.core.kubejsvoltaic.recipe.match;

import dev.latvian.mods.kubejs.recipe.match.ReplacementMatch;
import dev.latvian.mods.rhino.Context;
import net.neoforged.neoforge.fluids.FluidType;
import voltaic.api.gas.Gas;
import voltaic.api.gas.GasStack;
import voltaic.common.recipe.recipeutils.GasIngredient;

public interface GasMatch extends ReplacementMatch {
    boolean matches(Context cx, GasStack stack, boolean exact);

    boolean matches(Context cx, GasIngredient ingredient, boolean exact);

    default boolean matches(Context cx, Gas gas, boolean exact) {
        return !gas.isEmpty() && matches(cx, new GasStack(gas, FluidType.BUCKET_VOLUME, Gas.ROOM_TEMPERATURE, Gas.PRESSURE_AT_SEA_LEVEL), exact);
    }

    default boolean matchesAny(Context cx, Iterable<Gas> gasses, boolean exact) {
        for (var gas : gasses) {
            if (matches(cx, gas, exact)) {
                return true;
            }
        }

        return false;
    }
}
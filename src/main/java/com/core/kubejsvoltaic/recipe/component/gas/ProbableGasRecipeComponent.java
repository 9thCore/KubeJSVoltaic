package com.core.kubejsvoltaic.recipe.component.gas;

import com.core.kubejsvoltaic.KubeJSVoltaic;
import com.core.kubejsvoltaic.recipe.match.GasMatch;
import com.core.kubejsvoltaic.wrapper.ProbableGasWrapper;
import com.mojang.serialization.Codec;
import dev.latvian.mods.kubejs.recipe.KubeRecipe;
import dev.latvian.mods.kubejs.recipe.component.RecipeComponent;
import dev.latvian.mods.kubejs.recipe.match.ReplacementMatchInfo;
import dev.latvian.mods.rhino.Context;
import dev.latvian.mods.rhino.type.TypeInfo;
import net.minecraft.resources.ResourceLocation;
import voltaic.common.recipe.recipeutils.ProbableGas;

public record ProbableGasRecipeComponent(ResourceLocation Id) implements RecipeComponent<ProbableGas> {
    public static final TypeInfo TYPE_INFO = TypeInfo.of(ProbableGas.class);

    public static final ProbableGasRecipeComponent PROBABLE_GAS = new ProbableGasRecipeComponent(KubeJSVoltaic.Id("probable_gas"));

    @Override
    public Codec<ProbableGas> codec() {
        return ProbableGas.CODEC;
    }

    @Override
    public TypeInfo typeInfo() {
        return TYPE_INFO;
    }

    @Override
    public ProbableGas wrap(Context cx, KubeRecipe recipe, Object from) {
        ProbableGas gas = ProbableGasWrapper.from(from);
        if (gas != null) {
            return gas;
        }

        return RecipeComponent.super.wrap(cx, recipe, from);
    }

    @Override
    public boolean matches(Context cx, KubeRecipe recipe, ProbableGas value, ReplacementMatchInfo match) {
        return !isEmpty(value) && match.match() instanceof GasMatch m && m.matches(cx, value.getFullStack(), match.exact());
    }

    @Override
    public ProbableGas replace(Context cx, KubeRecipe recipe, ProbableGas original, ReplacementMatchInfo match, Object with) {
        if (!matches(cx, recipe, original, match)) {
            return original;
        }

        ProbableGas replacement = ProbableGasWrapper.from(with);
        if (replacement == null) {
            return original;
        }

        return replacement;
    }

    @Override
    public boolean isEmpty(ProbableGas value) {
        return value.getFullStack().isEmpty();
    }

    @Override
    public String toString() {
        return Id.toString();
    }
}

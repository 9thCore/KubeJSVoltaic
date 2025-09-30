package com.core.kubejsvoltaic.recipe.component.gas;

import com.core.kubejsvoltaic.KubeJSVoltaic;
import com.core.kubejsvoltaic.recipe.match.GasMatch;
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
    public boolean matches(Context cx, KubeRecipe recipe, ProbableGas value, ReplacementMatchInfo match) {
        return !isEmpty(value) && match.match() instanceof GasMatch m && m.matches(cx, value.getFullStack(), match.exact());
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

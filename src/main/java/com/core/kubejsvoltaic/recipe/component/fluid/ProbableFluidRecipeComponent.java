package com.core.kubejsvoltaic.recipe.component.fluid;

import com.core.kubejsvoltaic.KubeJSVoltaic;
import com.mojang.serialization.Codec;
import dev.latvian.mods.kubejs.recipe.KubeRecipe;
import dev.latvian.mods.kubejs.recipe.component.RecipeComponent;
import dev.latvian.mods.kubejs.recipe.match.FluidMatch;
import dev.latvian.mods.kubejs.recipe.match.ReplacementMatchInfo;
import dev.latvian.mods.rhino.Context;
import dev.latvian.mods.rhino.type.TypeInfo;
import net.minecraft.resources.ResourceLocation;
import voltaic.common.recipe.recipeutils.ProbableFluid;

public record ProbableFluidRecipeComponent(ResourceLocation Id) implements RecipeComponent<ProbableFluid> {
    public static final TypeInfo TYPE_INFO = TypeInfo.of(ProbableFluid.class);

    public static final ProbableFluidRecipeComponent PROBABLE_FLUID = new ProbableFluidRecipeComponent(KubeJSVoltaic.Id("probable_fluid"));

    @Override
    public Codec<ProbableFluid> codec() {
        return ProbableFluid.CODEC;
    }

    @Override
    public TypeInfo typeInfo() {
        return TYPE_INFO;
    }

    @Override
    public boolean matches(Context cx, KubeRecipe recipe, ProbableFluid value, ReplacementMatchInfo match) {
        return !isEmpty(value) && match.match() instanceof FluidMatch m && m.matches(cx, value.getFullStack(), match.exact());
    }

    @Override
    public boolean isEmpty(ProbableFluid value) {
        return value.getFullStack().isEmpty();
    }

    @Override
    public String toString() {
        return Id.toString();
    }
}

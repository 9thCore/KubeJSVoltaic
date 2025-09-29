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
import voltaic.common.recipe.recipeutils.GasIngredient;

public record GasIngredientRecipeComponent(ResourceLocation Id) implements RecipeComponent<GasIngredient> {
    public static TypeInfo TYPE_INFO = TypeInfo.of(GasIngredient.class);

    public static GasIngredientRecipeComponent GAS_INGREDIENT = new GasIngredientRecipeComponent(KubeJSVoltaic.Id("gas_ingredient"));

    @Override
    public Codec<GasIngredient> codec() {
        return GasIngredient.CODEC.codec();
    }

    @Override
    public TypeInfo typeInfo() {
        return TYPE_INFO;
    }

    @Override
    public boolean matches(Context cx, KubeRecipe recipe, GasIngredient value, ReplacementMatchInfo match) {
        return !isEmpty(value) && match.match() instanceof GasMatch m && m.matches(cx, value, match.exact());
    }

    @Override
    public boolean isEmpty(GasIngredient value) {
        return value.getGasStack().isEmpty();
    }

    @Override
    public String toString() {
        return Id.toString();
    }
}

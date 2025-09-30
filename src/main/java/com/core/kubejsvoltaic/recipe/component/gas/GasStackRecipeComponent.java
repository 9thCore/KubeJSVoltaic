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
import voltaic.api.gas.GasStack;

public record GasStackRecipeComponent(ResourceLocation Id) implements RecipeComponent<GasStack> {
    public static final TypeInfo TYPE_INFO = TypeInfo.of(GasStack.class);

    public static final GasStackRecipeComponent GAS_STACK = new GasStackRecipeComponent(KubeJSVoltaic.Id("gas_stack"));

    @Override
    public Codec<GasStack> codec() {
        return GasStack.CODEC;
    }

    @Override
    public TypeInfo typeInfo() {
        return TYPE_INFO;
    }

    @Override
    public boolean matches(Context cx, KubeRecipe recipe, GasStack value, ReplacementMatchInfo match) {
        return !isEmpty(value) && match.match() instanceof GasMatch m && m.matches(cx, value, match.exact());
    }

    @Override
    public boolean isEmpty(GasStack value) {
        return value.isEmpty();
    }

    @Override
    public String toString() {
        return Id.toString();
    }
}

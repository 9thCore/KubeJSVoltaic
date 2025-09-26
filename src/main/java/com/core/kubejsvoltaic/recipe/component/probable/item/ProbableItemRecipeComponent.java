package com.core.kubejsvoltaic.recipe.component.probable.item;

import com.core.kubejsvoltaic.KubeJSVoltaic;
import com.core.kubejsvoltaic.wrapper.ProbableItemWrapper;
import com.mojang.serialization.Codec;
import dev.latvian.mods.kubejs.bindings.ItemWrapper;
import dev.latvian.mods.kubejs.recipe.KubeRecipe;
import dev.latvian.mods.kubejs.recipe.component.RecipeComponent;
import dev.latvian.mods.kubejs.recipe.match.ItemMatch;
import dev.latvian.mods.kubejs.recipe.match.ReplacementMatchInfo;
import dev.latvian.mods.rhino.Context;
import dev.latvian.mods.rhino.type.TypeInfo;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ItemStack;
import voltaic.common.recipe.recipeutils.ProbableItem;

public record ProbableItemRecipeComponent(ResourceLocation Id) implements RecipeComponent<ProbableItem> {
    public static final TypeInfo TYPE_INFO = TypeInfo.of(ProbableItem.class);

    public static final ProbableItemRecipeComponent PROBABLE_ITEM = new ProbableItemRecipeComponent(KubeJSVoltaic.Id("probable_item"));

    @Override
    public Codec<ProbableItem> codec() {
        return ProbableItem.CODEC;
    }

    @Override
    public TypeInfo typeInfo() {
        return TYPE_INFO;
    }

    @Override
    public ProbableItem wrap(Context cx, KubeRecipe recipe, Object from) {
        ProbableItem item = ProbableItemWrapper.from(from);
        if (item != null) {
            return item;
        }

        return RecipeComponent.super.wrap(cx, recipe, from);
    }

    @Override
    public boolean matches(Context cx, KubeRecipe recipe, ProbableItem value, ReplacementMatchInfo match) {
        return !isEmpty(value) && match.match() instanceof ItemMatch m && m.matches(cx, value.getFullStack(), match.exact());
    }

    @Override
    public boolean isEmpty(ProbableItem value) {
        return value.getFullStack().isEmpty();
    }

    @Override
    public String toString() {
        return Id.toString();
    }
}

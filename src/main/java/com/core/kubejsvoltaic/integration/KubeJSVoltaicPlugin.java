package com.core.kubejsvoltaic.integration;

import com.core.kubejsvoltaic.recipe.component.probable.item.ProbableItemRecipeComponent;
import com.core.kubejsvoltaic.wrapper.ProbableItemWrapper;
import dev.latvian.mods.kubejs.plugin.KubeJSPlugin;
import dev.latvian.mods.kubejs.recipe.schema.RecipeComponentFactoryRegistry;
import dev.latvian.mods.kubejs.script.BindingRegistry;

public class KubeJSVoltaicPlugin implements KubeJSPlugin {
    @Override
    public void registerRecipeComponents(RecipeComponentFactoryRegistry registry) {
        registry.register(ProbableItemRecipeComponent.PROBABLE_ITEM);
    }

    @Override
    public void registerBindings(BindingRegistry bindings) {
        bindings.add("VoltaicProbableItem", ProbableItemWrapper.class);
    }
}

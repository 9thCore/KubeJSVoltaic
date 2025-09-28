package com.core.kubejsvoltaic.integration;

import com.core.kubejsvoltaic.recipe.component.GasStackRecipeComponent;
import com.core.kubejsvoltaic.recipe.component.probable.gas.ProbableGasRecipeComponent;
import com.core.kubejsvoltaic.recipe.component.probable.item.ProbableItemRecipeComponent;
import com.core.kubejsvoltaic.util.gas.GasUtil;
import com.core.kubejsvoltaic.wrapper.ProbableGasWrapper;
import com.core.kubejsvoltaic.wrapper.ProbableItemWrapper;
import dev.latvian.mods.kubejs.plugin.KubeJSPlugin;
import dev.latvian.mods.kubejs.recipe.schema.RecipeComponentFactoryRegistry;
import dev.latvian.mods.kubejs.script.BindingRegistry;
import dev.latvian.mods.kubejs.script.TypeWrapperRegistry;
import voltaic.api.gas.GasStack;
import voltaic.common.recipe.recipeutils.ProbableGas;
import voltaic.common.recipe.recipeutils.ProbableItem;

public class KubeJSVoltaicPlugin implements KubeJSPlugin {
    @Override
    public void registerRecipeComponents(RecipeComponentFactoryRegistry registry) {
        registry.register(ProbableItemRecipeComponent.PROBABLE_ITEM);
        registry.register(ProbableGasRecipeComponent.PROBABLE_GAS);

        registry.register(GasStackRecipeComponent.GAS_STACK);
    }

    @Override
    public void registerTypeWrappers(TypeWrapperRegistry registry) {
        registry.register(GasStack.class, GasUtil::wrap);
        registry.register(ProbableItem.class, ProbableItemWrapper::from);
        registry.register(ProbableGas.class, ProbableGasWrapper::from);
    }

    @Override
    public void registerBindings(BindingRegistry bindings) {
        bindings.add("VoltaicProbableItem", ProbableItemWrapper.class);
        bindings.add("VoltaicProbableGas", ProbableGasWrapper.class);
    }
}

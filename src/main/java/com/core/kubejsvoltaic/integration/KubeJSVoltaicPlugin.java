package com.core.kubejsvoltaic.integration;

import com.core.kubejsvoltaic.recipe.component.fluid.ProbableFluidRecipeComponent;
import com.core.kubejsvoltaic.recipe.component.gas.GasIngredientRecipeComponent;
import com.core.kubejsvoltaic.recipe.component.gas.GasStackRecipeComponent;
import com.core.kubejsvoltaic.recipe.component.gas.ProbableGasRecipeComponent;
import com.core.kubejsvoltaic.recipe.component.item.ProbableItemRecipeComponent;
import com.core.kubejsvoltaic.util.gas.GasUtil;
import com.core.kubejsvoltaic.wrapper.*;
import dev.latvian.mods.kubejs.plugin.KubeJSPlugin;
import dev.latvian.mods.kubejs.recipe.schema.RecipeComponentFactoryRegistry;
import dev.latvian.mods.kubejs.script.BindingRegistry;
import dev.latvian.mods.kubejs.script.TypeWrapperRegistry;
import voltaic.api.gas.GasStack;
import voltaic.common.recipe.recipeutils.GasIngredient;
import voltaic.common.recipe.recipeutils.ProbableFluid;
import voltaic.common.recipe.recipeutils.ProbableGas;
import voltaic.common.recipe.recipeutils.ProbableItem;

public class KubeJSVoltaicPlugin implements KubeJSPlugin {
    @Override
    public void registerRecipeComponents(RecipeComponentFactoryRegistry registry) {
        registry.register(ProbableItemRecipeComponent.PROBABLE_ITEM);
        registry.register(ProbableGasRecipeComponent.PROBABLE_GAS);
        registry.register(ProbableFluidRecipeComponent.PROBABLE_FLUID);

        registry.register(GasStackRecipeComponent.GAS_STACK);

        registry.register(GasIngredientRecipeComponent.GAS_INGREDIENT);
    }

    @Override
    public void registerTypeWrappers(TypeWrapperRegistry registry) {
        registry.register(GasStack.class, GasUtil::wrap);
        registry.register(GasIngredient.class, GasUtil::wrapIngredient);
        registry.register(ProbableItem.class, ProbableItemWrapper::from);
        registry.register(ProbableGas.class, ProbableGasWrapper::from);
        registry.register(ProbableFluid.class, ProbableFluidWrapper::from);
    }

    @Override
    public void registerBindings(BindingRegistry bindings) {
        bindings.add("VoltaicGasIngredient", GasIngredientWrapper.class);
        bindings.add("VoltaicGasStack", GasStackWrapper.class);
        bindings.add("VoltaicProbableItem", ProbableItemWrapper.class);
        bindings.add("VoltaicProbableGas", ProbableGasWrapper.class);
        bindings.add("VoltaicProbableFluid", ProbableFluidWrapper.class);
    }
}

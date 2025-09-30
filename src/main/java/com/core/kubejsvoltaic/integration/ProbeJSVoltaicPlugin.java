package com.core.kubejsvoltaic.integration;

import moe.wolfgirl.probejs.lang.typescript.ScriptDump;
import moe.wolfgirl.probejs.lang.typescript.code.type.Types;
import moe.wolfgirl.probejs.plugin.ProbeJSPlugin;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.material.Fluid;
import net.neoforged.neoforge.fluids.FluidStack;
import voltaic.api.gas.Gas;
import voltaic.api.gas.GasStack;
import voltaic.common.recipe.recipeutils.GasIngredient;
import voltaic.common.recipe.recipeutils.ProbableFluid;
import voltaic.common.recipe.recipeutils.ProbableGas;
import voltaic.common.recipe.recipeutils.ProbableItem;

public class ProbeJSVoltaicPlugin extends ProbeJSPlugin {
    @Override
    public void assignType(ScriptDump scriptDump) {
        scriptDump.assignType(GasStack.class, Types.type(Gas.class));
        scriptDump.assignType(GasStack.class, Types.object()
                .member("amount", true, Types.NUMBER)
                .member("temp", true, Types.NUMBER)
                .member("pressure", true, Types.NUMBER)
                .member("gas", Types.type(Gas.class))
                .build());

        scriptDump.assignType(ProbableItem.class, Types.type(ItemStack.class));
        scriptDump.assignType(ProbableItem.class, Types.object()
                .member("chance", true, Types.NUMBER)
                .member("item", Types.type(ItemStack.class))
                .build());

        scriptDump.assignType(ProbableFluid.class, Types.type(FluidStack.class));
        scriptDump.assignType(ProbableFluid.class, Types.object()
                .member("chance", true, Types.NUMBER)
                .member("amount", true, Types.NUMBER)
                .member("fluid", Types.type(Fluid.class))
                .build());

        scriptDump.assignType(ProbableGas.class, Types.type(GasStack.class));
        scriptDump.assignType(ProbableGas.class, Types.object()
                .member("chance", true, Types.NUMBER)
                .member("amount", true, Types.NUMBER)
                .member("temp", true, Types.NUMBER)
                .member("pressure", true, Types.NUMBER)
                .member("gas", Types.type(Gas.class))
                .build());

        scriptDump.assignType(GasIngredient.class, Types.object()
                .member("amount", true, Types.NUMBER)
                .member("temp", true, Types.NUMBER)
                .member("pressure", true, Types.NUMBER)
                .member("gas", Types.type(Gas.class))
                .member("tag", Types.primitive("`#${Special.VoltaicGasesTag}`"))
                .build());

        scriptDump.assignType(GasIngredient.class, Types.primitive("`#${Special.VoltaicGasesTag}`"));
        scriptDump.assignType(GasIngredient.class, Types.type(GasStack.class));
    }
}

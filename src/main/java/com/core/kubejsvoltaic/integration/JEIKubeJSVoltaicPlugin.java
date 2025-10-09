package com.core.kubejsvoltaic.integration;

import com.core.kubejsvoltaic.KubeJSVoltaic;
import com.core.kubejsvoltaic.gas.GasBuilder;
import mezz.jei.api.IModPlugin;
import mezz.jei.api.JeiPlugin;
import mezz.jei.api.registration.IExtraIngredientRegistration;
import net.minecraft.resources.ResourceLocation;
import net.neoforged.neoforge.fluids.FluidType;
import org.jetbrains.annotations.NotNull;
import voltaic.api.gas.Gas;
import voltaic.api.gas.GasStack;
import voltaic.compatibility.jei.utils.ingredients.VoltaicJeiTypes;

@JeiPlugin
public class JEIKubeJSVoltaicPlugin implements IModPlugin {
    public static final ResourceLocation ID = KubeJSVoltaic.Id("jei_plugin");

    @Override
    public @NotNull ResourceLocation getPluginUid() {
        return ID;
    }

    @Override
    public void registerExtraIngredients(@NotNull IExtraIngredientRegistration registration) {
        registration.addExtraIngredients(
                VoltaicJeiTypes.GAS_STACK,
                GasBuilder.BUILT_GASSES
                        .stream()
                        .map(gas -> new GasStack(gas, FluidType.BUCKET_VOLUME, Gas.ROOM_TEMPERATURE, Gas.PRESSURE_AT_SEA_LEVEL))
                        .toList());
    }
}

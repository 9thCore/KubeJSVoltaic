package com.core.kubejsvoltaic.gas;

import dev.latvian.mods.kubejs.color.KubeColor;
import dev.latvian.mods.kubejs.registry.BuilderBase;
import dev.latvian.mods.kubejs.typings.Info;
import dev.latvian.mods.rhino.util.HideFromJS;
import net.minecraft.ChatFormatting;
import net.minecraft.core.Holder;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.material.Fluid;
import net.minecraft.world.level.material.Fluids;
import org.jetbrains.annotations.NotNull;
import voltaic.api.gas.Gas;
import voltaic.prefab.utilities.math.Color;

import java.util.ArrayList;
import java.util.List;

public class GasBuilder extends BuilderBase<Gas> {
    @HideFromJS
    public static List<Gas> BUILT_GASSES = new ArrayList<>();

    private int condensationTemp = 0;
    private Fluid condensationFluid = Fluids.EMPTY;
    private Item item = Items.AIR;
    private Color color = Color.WHITE;

    public GasBuilder(ResourceLocation id) {
        super(id);
    }

    @Info("Allow gas to condensate to the given fluid, if the gas is below the given temperature")
    public GasBuilder condensate(@NotNull Fluid condensationFluid, int condensationTemp) {
        this.condensationFluid = condensationFluid;
        this.condensationTemp = condensationTemp;
        return this;
    }

    @Info("Set the color of this gas, visible in item viewers")
    public GasBuilder color(KubeColor color) {
        this.color = new Color(color.kjs$getARGB());
        return this;
    }

    @HideFromJS
    @Info("Default gas container")
    public GasBuilder container(Item item) {
        this.item = item;
        return this;
    }

    @Override
    public Gas createObject() {
        if (condensationFluid != Fluids.EMPTY) {
            Gas gas = new Gas(
                    Holder.direct(item),
                    Component.translatable(getBuilderTranslationKey()),
                    condensationTemp,
                    color,
                    Holder.direct(condensationFluid)
            );

            BUILT_GASSES.add(gas);

            return gas;
        }

        Gas gas = new Gas(
                Holder.direct(item),
                Component.translatable(getBuilderTranslationKey()),
                color
        );

        BUILT_GASSES.add(gas);

        return gas;
    }
}

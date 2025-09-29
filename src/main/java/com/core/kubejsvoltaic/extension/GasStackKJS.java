package com.core.kubejsvoltaic.extension;

import com.core.kubejsvoltaic.recipe.match.GasMatch;
import com.core.kubejsvoltaic.util.gas.GasUtil;
import com.mojang.serialization.Codec;
import dev.latvian.mods.kubejs.core.NoMixinException;
import dev.latvian.mods.kubejs.core.RegistryObjectKJS;
import dev.latvian.mods.kubejs.recipe.match.Replaceable;
import dev.latvian.mods.kubejs.typings.Info;
import dev.latvian.mods.kubejs.util.WithCodec;
import dev.latvian.mods.rhino.Context;
import dev.latvian.mods.rhino.util.RemapPrefixForJS;
import dev.latvian.mods.rhino.util.ReturnsSelf;
import dev.latvian.mods.rhino.util.SpecialEquality;
import net.minecraft.core.Holder;
import net.minecraft.core.Registry;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import voltaic.api.gas.Gas;
import voltaic.api.gas.GasStack;
import voltaic.common.recipe.recipeutils.GasIngredient;
import voltaic.registers.VoltaicGases;

@RemapPrefixForJS("kjs$")
public interface GasStackKJS extends
        SpecialEquality,
        WithCodec,
        Replaceable,
        GasMatch,
        RegistryObjectKJS<Gas> {
    default GasStack kjs$self() {
        throw new NoMixinException();
    }

    @Override
    default boolean specialEquals(Context cx, Object o, boolean shallow) {
        return kjs$equalsGas(GasUtil.gasStackFrom(o));
    }

    @Info("Performs a full equality check between this and the given stack")
    default boolean kjs$equals(GasStack stack) {
        if (stack == null) {
            return false;
        }

        GasStack self = kjs$self();
        return self.isSameGas(stack)
                && self.isSameAmount(stack)
                && self.isSameTemperature(stack)
                && self.isSamePressure(stack);
    }

    @Info("Checks if this stack holds the same gas as the given stack")
    default boolean kjs$equalsGas(GasStack stack) {
        if (stack == null) {
            return false;
        }

        return kjs$self().is(stack.getGas());
    }

    @Info("Checks if this stack holds the same amount of gas as the given stack")
    default boolean kjs$equalsAmount(GasStack stack) {
        if (stack == null) {
            return false;
        }

        return kjs$self().isSameAmount(stack);
    }

    @Info("Checks if this stack's gas is at the same pressure as the given stack")
    default boolean kjs$equalsPressure(GasStack stack) {
        if (stack == null) {
            return false;
        }

        return kjs$self().isSamePressure(stack);
    }

    @Info("Set this stack's pressure (equivalent to .bringPressureTo(pressure))")
    default void kjs$setPressure(int pressure) {
        kjs$self().bringPressureTo(pressure);
    }

    @Info("Checks if this stack's gas is at the same temperature as the given stack")
    default boolean kjs$equalsTemperature(GasStack stack) {
        if (stack == null) {
            return false;
        }

        return kjs$self().isSameTemperature(stack);
    }

    @Info("Set this stack's temperature (equivalent to .heat(temperature - .getTemperature()))")
    default void kjs$setTemperature(int temperature) {
        kjs$self().heat(temperature - kjs$self().getTemperature());
    }

    @Override
    default ResourceKey<Registry<Gas>> kjs$getRegistryId() {
        return VoltaicGases.GAS_REGISTRY_KEY;
    }

    @Override
    default Registry<Gas> kjs$getRegistry() {
        return VoltaicGases.GAS_REGISTRY;
    }

    @Override
    default ResourceLocation kjs$getIdLocation() {
        return VoltaicGases.GAS_REGISTRY.getKey(kjs$self().getGas());
    }

    @Override
    default Holder<Gas> kjs$asHolder() {
        return kjs$self().getGasHolder();
    }

    @Override
    default ResourceKey<Gas> kjs$getKey() {
        return kjs$self().getGasHolder().getKey();
    }

    @Info("Return a copy of this stack with the given amount")
    @ReturnsSelf(copy = true)
    default GasStack kjs$withAmount(int amount) {
        if (amount <= 0) {
            return GasStack.EMPTY;
        }

        var stack = kjs$self().copy();
        stack.setAmount(amount);
        return stack;
    }

    @Info("Return a copy of this stack with the given pressure")
    @ReturnsSelf(copy = true)
    default GasStack kjs$withPressure(int pressure) {
        var stack = kjs$self().copy();
        stack.bringPressureTo(pressure);
        return stack;
    }

    @Info("Return a copy of this stack with the given temperature")
    @ReturnsSelf(copy = true)
    default GasStack kjs$withTemperature(int temperature) {
        GasStackKJS stack = (GasStackKJS) kjs$self().copy();
        stack.kjs$setTemperature(temperature);
        return stack.kjs$self();
    }

    @Info("Return this stack as a GasIngredient")
    default GasIngredient kjs$asIngredient() {
        return new GasIngredient(kjs$self());
    }

    @Override
    default Codec<GasStack> getCodec(Context cx) {
        return GasStack.CODEC;
    }

    @Override
    default Object replaceThisWith(Context cx, Object with) {
        GasStack self = kjs$self();
        GasStack other = GasUtil.gasStackFrom(with);

        if (other == null) {
            return self;
        }

        return other;
    }

    @Override
    default boolean matches(Context cx, GasStack stack, boolean exact) {
        return kjs$self().getGas() == stack.getGas();
    }

    @Override
    default boolean matches(Context cx, GasIngredient ingredient, boolean exact) {
        return ingredient.test(kjs$self());
    }
}

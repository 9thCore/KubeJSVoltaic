package com.core.kubejsvoltaic.util.item;

import com.core.kubejsvoltaic.util.RegexUtil;
import dev.latvian.mods.kubejs.fluid.FluidLike;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.material.Fluid;
import net.neoforged.neoforge.fluids.FluidStack;
import net.neoforged.neoforge.fluids.FluidType;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public final class FluidUtil {
    public static String FLUID_MATCH_COMPONENT = "([a-z0-9/._:-]+)";
    public static String COUNT_MATCH_COMPONENT = "(\\d+)x";
    public static Pattern COUNT_ITEM = prefixFluid(COUNT_MATCH_COMPONENT);

    public static Pattern prefixFluid(String... prefix) {
        return RegexUtil.getPattern(prefix, FLUID_MATCH_COMPONENT);
    }

    public static FluidStack fluidStackFrom(Object from) {
        if (from instanceof FluidStack stack) {
            return stack;
        } else if (from instanceof FluidLike fluid) {
            return new FluidStack(fluid.kjs$getFluid(), FluidType.BUCKET_VOLUME);
        } else if (from instanceof CharSequence sequence) {
            return fluidStackFrom(sequence);
        }

        return null;
    }

    public static FluidStack fluidStackFrom(CharSequence sequence) {
        Matcher countItemMatcher = COUNT_ITEM.matcher(sequence);

        if (!countItemMatcher.find()) {
            return fluidStackFrom(sequence, FluidType.BUCKET_VOLUME);
        } else {
            return fluidStackFrom(countItemMatcher.group(2), countItemMatcher.group(1));
        }
    }

    public static FluidStack fluidStackFrom(CharSequence sequence, int count) {
        return fromFluid(BuiltInRegistries.FLUID.get(ResourceLocation.parse(sequence.toString())), count);
    }

    public static FluidStack fluidStackFrom(CharSequence unparsedItem, String unparsedCount) {
        return fluidStackFrom(unparsedItem, Integer.parseInt(unparsedCount));
    }

    private static FluidStack fromFluid(Fluid fluid, int count) {
        if (fluid == null) {
            return null;
        }

        return new FluidStack(fluid, count);
    }
}

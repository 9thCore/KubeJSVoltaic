package com.core.kubejsvoltaic.wrapper;

import com.core.kubejsvoltaic.util.fluid.FluidUtil;
import dev.latvian.mods.kubejs.typings.Info;
import dev.latvian.mods.rhino.util.HideFromJS;
import net.neoforged.neoforge.fluids.FluidStack;
import net.neoforged.neoforge.fluids.FluidType;
import voltaic.common.recipe.recipeutils.ProbableFluid;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public interface ProbableFluidWrapper {
    @HideFromJS
    static ProbableFluid EMPTY = new ProbableFluid(FluidStack.EMPTY, 0.0d);
    @HideFromJS
    static String CHANCE_MATCH_COMPONENT = "(\\d*\\.?\\d*)%";
    @HideFromJS
    static Pattern CHANCE_FLUID = FluidUtil.prefixFluid(CHANCE_MATCH_COMPONENT);
    @HideFromJS
    static Pattern CHANCE_COUNT_FLUID = FluidUtil.prefixFluid(CHANCE_MATCH_COMPONENT, FluidUtil.COUNT_MATCH_COMPONENT);
    @HideFromJS
    static Pattern COUNT_CHANCE_FLUID = FluidUtil.prefixFluid(FluidUtil.COUNT_MATCH_COMPONENT, CHANCE_MATCH_COMPONENT);

    @Info("Returns Voltaic's ProbableFluid of the input, with a chance of 100%")
    static ProbableFluid of(FluidStack stack) {
        return of(stack, 1d);
    }

    @Info("Returns Voltaic's ProbableFluid of the input, with the given chance in the range [0, 1]")
    static ProbableFluid of(FluidStack stack, double chance) {
        return new ProbableFluid(stack, chance);
    }

    @HideFromJS
    static ProbableFluid from(Object from) {
        if (from == null) {
            return EMPTY;
        }

        if (from instanceof ProbableFluid probableFluid) {
            return probableFluid;
        } else if (from instanceof CharSequence sequence) {
            ProbableFluid probableFluid = probableFluidFrom(sequence);
            if (probableFluid != null) {
                return probableFluid;
            }
        }

        FluidStack stack = FluidUtil.fluidStackFrom(from);
        if (stack == null) {
            return EMPTY;
        }

        return new ProbableFluid(stack, 1d);
    }

    private static ProbableFluid probableFluidFrom(CharSequence sequence) {
        Matcher chanceMatcher = CHANCE_FLUID.matcher(sequence);
        if (chanceMatcher.find()) {
            double chance = Double.parseDouble(chanceMatcher.group(1)) / 100.0d;
            FluidStack stack = FluidUtil.fluidStackFrom(chanceMatcher.group(2), FluidType.BUCKET_VOLUME);
            return new ProbableFluid(stack, chance);
        }

        Matcher chanceCountMatcher = CHANCE_COUNT_FLUID.matcher(sequence);
        if (chanceCountMatcher.find()) {
            double chance = Double.parseDouble(chanceCountMatcher.group(1)) / 100.0d;
            FluidStack stack = FluidUtil.fluidStackFrom(chanceCountMatcher.group(3), chanceCountMatcher.group(2));
            return new ProbableFluid(stack, chance);
        }

        Matcher countChanceMatcher = COUNT_CHANCE_FLUID.matcher(sequence);
        if (countChanceMatcher.find()) {
            double chance = Double.parseDouble(countChanceMatcher.group(2)) / 100.0d;
            FluidStack stack = FluidUtil.fluidStackFrom(countChanceMatcher.group(3), countChanceMatcher.group(1));
            return new ProbableFluid(stack, chance);
        }

        return null;
    }
}

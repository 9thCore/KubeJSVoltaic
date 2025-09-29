package com.core.kubejsvoltaic.wrapper;

import com.core.kubejsvoltaic.util.gas.GasUtil;
import com.google.gson.JsonObject;
import dev.latvian.mods.kubejs.script.ConsoleJS;
import dev.latvian.mods.kubejs.typings.Info;
import dev.latvian.mods.rhino.util.HideFromJS;
import net.neoforged.neoforge.fluids.FluidType;
import org.jline.utils.Log;
import voltaic.api.gas.GasStack;
import voltaic.common.recipe.recipeutils.ProbableGas;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public interface ProbableGasWrapper {
    @HideFromJS
    static ProbableGas EMPTY = new ProbableGas(GasStack.EMPTY, 0.0d);
    @HideFromJS
    static String JSON_CHANCE_KEY = "chance";
    @HideFromJS
    static String CHANCE_MATCH_COMPONENT = "(\\d*\\.?\\d*)%";
    @HideFromJS
    static Pattern CHANCE_GAS = GasUtil.prefixGas(CHANCE_MATCH_COMPONENT);
    @HideFromJS
    static Pattern CHANCE_COUNT_GAS = GasUtil.prefixGas(CHANCE_MATCH_COMPONENT, GasUtil.COUNT_MATCH_COMPONENT);
    @HideFromJS
    static Pattern COUNT_CHANCE_GAS = GasUtil.prefixGas(GasUtil.COUNT_MATCH_COMPONENT, CHANCE_MATCH_COMPONENT);

    @Info("Returns Voltaic's ProbableGas of the input, with a chance of 100%")
    static ProbableGas of(GasStack stack) {
        return of(stack, 1d);
    }

    @Info("Returns Voltaic's ProbableGas of the input, with the given chance in the range [0, 1]")
    static ProbableGas of(GasStack stack, double chance) {
        return new ProbableGas(stack, chance);
    }

    @HideFromJS
    static ProbableGas from(Object from) {
        if (from == null) {
            return EMPTY;
        }

        if (from instanceof ProbableGas probableGas) {
            return probableGas;
        } else if (from instanceof JsonObject json) {
            if (GasUtil.isEmpty(json)) {
                return EMPTY;
            }

            GasStack stack = GasUtil.gasStackFrom(from);
            double chance = json.has(JSON_CHANCE_KEY) ? json.get(JSON_CHANCE_KEY).getAsDouble() : 1.0d;
            return new ProbableGas(stack, chance);
        } else if (from instanceof CharSequence sequence) {
            ProbableGas probableGas = probableGasFrom(sequence);
            if (probableGas != null) {
                return probableGas;
            }
        }

        GasStack stack = GasUtil.gasStackFrom(from);
        if (stack == null) {
            return EMPTY;
        }

        return new ProbableGas(stack, 1.0d);
    }

    private static ProbableGas probableGasFrom(CharSequence sequence) {
        Matcher chanceMatcher = CHANCE_GAS.matcher(sequence);
        if (chanceMatcher.find()) {
            double chance = Double.parseDouble(chanceMatcher.group(1)) / 100.0d;
            GasStack stack = GasUtil.gasStackFrom(chanceMatcher.group(2), FluidType.BUCKET_VOLUME);
            return new ProbableGas(stack, chance);
        }

        Matcher chanceCountMatcher = CHANCE_COUNT_GAS.matcher(sequence);
        if (chanceCountMatcher.find()) {
            double chance = Double.parseDouble(chanceCountMatcher.group(1)) / 100.0d;
            GasStack stack = GasUtil.gasStackFrom(chanceCountMatcher.group(3), chanceCountMatcher.group(2));
            return new ProbableGas(stack, chance);
        }

        Matcher countChanceMatcher = COUNT_CHANCE_GAS.matcher(sequence);
        if (countChanceMatcher.find()) {
            double chance = Double.parseDouble(countChanceMatcher.group(2)) / 100.0d;
            GasStack stack = GasUtil.gasStackFrom(countChanceMatcher.group(3), countChanceMatcher.group(1));
            return new ProbableGas(stack, chance);
        }

        return null;
    }
}

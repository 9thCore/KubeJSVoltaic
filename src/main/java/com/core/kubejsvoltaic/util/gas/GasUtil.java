package com.core.kubejsvoltaic.util.gas;

import com.core.kubejsvoltaic.util.RegexUtil;
import com.google.gson.JsonObject;
import net.minecraft.resources.ResourceLocation;
import net.neoforged.neoforge.fluids.FluidType;
import voltaic.api.gas.Gas;
import voltaic.api.gas.GasStack;
import voltaic.registers.VoltaicGases;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public final class GasUtil {
    public static String JSON_AMOUNT_KEY = "amount";
    public static String JSON_GAS_KEY = "gas";
    public static String JSON_PRESSURE_KEY = "pressure";
    public static String JSON_TEMP_KEY = "temp";
    public static String GAS_MATCH_COMPONENT = "([a-z0-9/._:-]+)";
    public static String COUNT_MATCH_COMPONENT = "(\\d+)x";
    public static Pattern COUNT_GAS = prefixGas(COUNT_MATCH_COMPONENT);

    public static Pattern prefixGas(String... prefix) {
        return RegexUtil.getPattern(prefix, GAS_MATCH_COMPONENT);
    }

    public static GasStack wrap(Object from) {
        return gasStackFrom(from);
    }

    public static GasStack gasStackFrom(Object from) {
        if (from instanceof GasStack stack) {
            return stack;
        } else if (from instanceof Gas gas) {
            return new GasStack(gas, FluidType.BUCKET_VOLUME, Gas.ROOM_TEMPERATURE, Gas.PRESSURE_AT_SEA_LEVEL);
        } else if (from instanceof JsonObject json) {
            String gas = json.get(JSON_GAS_KEY).getAsString();
            int amount = json.has(JSON_AMOUNT_KEY) ? json.get(JSON_AMOUNT_KEY).getAsInt() : FluidType.BUCKET_VOLUME;
            int pressure = json.has(JSON_PRESSURE_KEY) ? json.get(JSON_PRESSURE_KEY).getAsInt() : Gas.PRESSURE_AT_SEA_LEVEL;
            int temp = json.has(JSON_TEMP_KEY) ? json.get(JSON_TEMP_KEY).getAsInt() : Gas.ROOM_TEMPERATURE;
            return gasStackFrom(gas, amount, temp, pressure);
        } else if (from instanceof CharSequence sequence) {
            return gasStackFrom(sequence);
        }

        return null;
    }

    public static GasStack gasStackFrom(CharSequence sequence) {
        Matcher countGas = COUNT_GAS.matcher(sequence);

        if (!countGas.find()) {
            return gasStackFrom(sequence, null, null, null);
        }

        return gasStackFrom(countGas.group(2), countGas.group(1), null, null);
    }

    public static GasStack gasStackFrom(CharSequence sequence, int amount) {
        return fromGas(VoltaicGases.GAS_REGISTRY.get(ResourceLocation.parse(sequence.toString())), amount, Gas.ROOM_TEMPERATURE, Gas.PRESSURE_AT_SEA_LEVEL);
    }

    public static GasStack gasStackFrom(CharSequence sequence, int amount, int temperature, int pressure) {
        return fromGas(VoltaicGases.GAS_REGISTRY.get(ResourceLocation.parse(sequence.toString())), amount, temperature, pressure);
    }

    public static GasStack gasStackFrom(CharSequence unparsedGas, String unparsedAmount) {
        return gasStackFrom(unparsedGas, unparsedAmount, null, null);
    }

    public static GasStack gasStackFrom(CharSequence unparsedGas, String unparsedAmount, String unparsedTemperature, String unparsedPressure) {
        return gasStackFrom(
                unparsedGas,
                unparsedAmount == null ? FluidType.BUCKET_VOLUME : Integer.parseInt(unparsedAmount),
                unparsedTemperature == null ? Gas.ROOM_TEMPERATURE : Integer.parseInt(unparsedTemperature),
                unparsedPressure == null ? Gas.PRESSURE_AT_SEA_LEVEL : Integer.parseInt(unparsedPressure));
    }

    private static GasStack fromGas(Gas gas, int amount, int temperature, int pressure) {
        if (gas == null) {
            return null;
        }

        return new GasStack(gas, amount, temperature, pressure);
    }
}

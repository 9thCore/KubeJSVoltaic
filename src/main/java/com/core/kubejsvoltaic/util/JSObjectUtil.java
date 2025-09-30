package com.core.kubejsvoltaic.util;

import dev.latvian.mods.rhino.NativeObject;

/// Rhino sucks
public final class JSObjectUtil {
    /// Rhino sucks
    public static <T> T getValue(NativeObject object, Object key, Class<T> clazz) {
        return getValue(object, key, null, clazz);
    }

    /// Rhino sucks
    public static <T> T getValue(NativeObject object, Object key, T defaultValue, Class<T> clazz) {
        if (!object.containsKey(key)) {
            return defaultValue;
        }

        Object value = object.get(key);

        try {
            return clazz.cast(value);
        } catch (Exception ignored) {

        }

        return defaultValue;
    }

    /// Rhino sucks
    public static <T> T getValue(NativeObject object, T defaultValue, Class<T> clazz, Object... keys) {
        for (Object key : keys) {
            T value = getValue(object, key, null, clazz);
            if (value != null) {
                return value;
            }
        }

        return defaultValue;
    }

    /// Rhino sucks
    public static int getIntegerValue(NativeObject object, Object key, int defaultValue) {
        return getValue(object, key, (double) defaultValue, Double.class).intValue();
    }

    /// Rhino sucks
    public static int getIntegerValue(NativeObject object, int defaultValue, Object... keys) {
        for (Object key : keys) {
            int value = getIntegerValue(object, key, defaultValue);
            if (value != defaultValue) {
                return value;
            }
        }

        return defaultValue;
    }

    /// Rhino sucks
    public static double getDoubleValue(NativeObject object, Object key, double defaultValue) {
        return getValue(object, key, defaultValue, Double.class);
    }
}

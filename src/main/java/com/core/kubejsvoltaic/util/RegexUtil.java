package com.core.kubejsvoltaic.util;

import java.util.regex.Pattern;

public final class RegexUtil {
    public static Pattern getPattern(String[] prefix, String postfix) {
        StringBuilder builder = new StringBuilder("^");

        for (int i = 0; i < prefix.length; i++) {
            builder.append(prefix[i]);
            builder.append(" ");
        }

        builder.append(postfix);
        builder.append("$");

        return Pattern.compile(builder.toString());
    }
}

package com.core.kubejsvoltaic.util.item;

import com.core.kubejsvoltaic.util.RegexUtil;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.ItemLike;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public final class ItemUtil {
    public static String ITEM_MATCH_COMPONENT = "([a-z0-9/._:-]+)";
    public static String COUNT_MATCH_COMPONENT = "(\\d+)x";
    public static Pattern COUNT_ITEM = prefixItem(COUNT_MATCH_COMPONENT);

    public static Pattern prefixItem(String... prefix) {
        return RegexUtil.getPattern(prefix, ITEM_MATCH_COMPONENT);
    }

    public static ItemStack itemStackFrom(Object from) {
        if (from instanceof ItemStack stack) {
            return stack;
        } else if (from instanceof ItemLike item) {
            return item.asItem().getDefaultInstance();
        } else if (from instanceof CharSequence sequence) {
            return ItemUtil.itemStackFrom(sequence);
        }

        return null;
    }

    public static ItemStack itemStackFrom(CharSequence sequence) {
        Matcher countItemMatcher = COUNT_ITEM.matcher(sequence);

        if (!countItemMatcher.find()) {
            return itemStackFrom(sequence, 1);
        } else {
            return itemStackFrom(countItemMatcher.group(2), countItemMatcher.group(1));
        }
    }

    public static ItemStack itemStackFrom(CharSequence sequence, int count) {
        return fromItem(BuiltInRegistries.ITEM.get(ResourceLocation.parse(sequence.toString())), count);
    }

    public static ItemStack itemStackFrom(CharSequence unparsedItem, String unparsedCount) {
        return itemStackFrom(unparsedItem, Integer.parseInt(unparsedCount));
    }

    private static ItemStack fromItem(Item item, int count) {
        if (item == null) {
            return null;
        }

        return new ItemStack(item, count);
    }
}

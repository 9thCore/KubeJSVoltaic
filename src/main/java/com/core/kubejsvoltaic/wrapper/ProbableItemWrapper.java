package com.core.kubejsvoltaic.wrapper;

import com.core.kubejsvoltaic.util.JSObjectUtil;
import com.core.kubejsvoltaic.util.item.ItemUtil;
import dev.latvian.mods.kubejs.typings.Info;
import dev.latvian.mods.rhino.NativeObject;
import dev.latvian.mods.rhino.util.HideFromJS;
import net.minecraft.world.item.ItemStack;
import voltaic.common.recipe.recipeutils.ProbableItem;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public interface ProbableItemWrapper {
    @HideFromJS
    static ProbableItem EMPTY = new ProbableItem(ItemStack.EMPTY, 0.0d);
    @HideFromJS
    static String JSON_CHANCE_KEY = "chance";
    @HideFromJS
    static String CHANCE_MATCH_COMPONENT = "(\\d*\\.?\\d*)%";
    @HideFromJS
    static Pattern CHANCE_ITEM = ItemUtil.prefixItem(CHANCE_MATCH_COMPONENT);
    @HideFromJS
    static Pattern CHANCE_COUNT_ITEM = ItemUtil.prefixItem(CHANCE_MATCH_COMPONENT, ItemUtil.COUNT_MATCH_COMPONENT);
    @HideFromJS
    static Pattern COUNT_CHANCE_ITEM = ItemUtil.prefixItem(ItemUtil.COUNT_MATCH_COMPONENT, CHANCE_MATCH_COMPONENT);

    @Info("Returns Voltaic's ProbableItem of the input, with a chance of 100%")
    static ProbableItem of(ProbableItem probableItem) {
        return of(probableItem, probableItem.getChance());
    }

    @Info("Returns Voltaic's ProbableItem of the input, with the given chance in the range [0, 1]")
    static ProbableItem of(ProbableItem probableItem, double chance) {
        return new ProbableItem(probableItem.getFullStack(), chance);
    }

    @HideFromJS
    static ProbableItem from(Object from) {
        if (from == null) {
            return EMPTY;
        }

        if (from instanceof ProbableItem probableItem) {
            return probableItem;
        } else if (from instanceof CharSequence sequence) {
            ProbableItem probableItem = probableItemFrom(sequence);
            if (probableItem != null) {
                return probableItem;
            }
        } else if (from instanceof NativeObject object) {
            ItemStack stack = ItemUtil.itemStackFrom(object);
            if (stack != null) {
                double chance = JSObjectUtil.getDoubleValue(object, JSON_CHANCE_KEY, 1.0d);
                return new ProbableItem(stack, chance);
            }
            return EMPTY;
        }

        ItemStack stack = ItemUtil.itemStackFrom(from);
        if (stack == null) {
            return EMPTY;
        }

        return new ProbableItem(stack, 1d);
    }

    private static ProbableItem probableItemFrom(CharSequence sequence) {
        Matcher chanceMatcher = CHANCE_ITEM.matcher(sequence);
        if (chanceMatcher.find()) {
            double chance = Double.parseDouble(chanceMatcher.group(1)) / 100.0d;
            ItemStack stack = ItemUtil.itemStackFrom(chanceMatcher.group(2), 1);
            return new ProbableItem(stack, chance);
        }

        Matcher chanceCountMatcher = CHANCE_COUNT_ITEM.matcher(sequence);
        if (chanceCountMatcher.find()) {
            double chance = Double.parseDouble(chanceCountMatcher.group(1)) / 100.0d;
            ItemStack stack = ItemUtil.itemStackFrom(chanceCountMatcher.group(3), chanceCountMatcher.group(2));
            return new ProbableItem(stack, chance);
        }

        Matcher countChanceMatcher = COUNT_CHANCE_ITEM.matcher(sequence);
        if (countChanceMatcher.find()) {
            double chance = Double.parseDouble(countChanceMatcher.group(2)) / 100.0d;
            ItemStack stack = ItemUtil.itemStackFrom(countChanceMatcher.group(3), countChanceMatcher.group(1));
            return new ProbableItem(stack, chance);
        }

        return null;
    }
}

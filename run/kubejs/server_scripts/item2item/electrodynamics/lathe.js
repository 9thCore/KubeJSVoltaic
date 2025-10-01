if (Platform.getMods().containsKey("electrodynamics")) {
    ServerEvents.recipes(event => {
        event.recipes.electrodynamics.lathe_recipe(
            "4x minecraft:stick",
            "2x #minecraft:logs"
        )
        .ticks(60)
        .usagePerTick(250)
        .itemByproducts("25% stripped_oak_log");

        event.replaceInput({
            mod: "electrodynamics",
            type: "electrodynamics:lathe_recipe"
        }, Ingredient.of("#c:ingots/hslasteel"), "minecraft:iron_ingot");
    });
}
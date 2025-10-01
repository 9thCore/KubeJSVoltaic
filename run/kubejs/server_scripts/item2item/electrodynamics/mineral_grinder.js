if (Platform.getMods().containsKey("electrodynamics")) {
    ServerEvents.recipes(event => {
        event.recipes.electrodynamics.mineral_grinder_recipe(
            "minecraft:vine",
            "minecraft:mossy_cobblestone"
        )
        .ticks(60)
        .usagePerTick(250)
        .itemByproducts("25% vine");

        event.recipes.electrodynamics.mineral_grinder_recipe(
            "electrodynamics:dustiron",
            "electrodynamics:plateiron"
        )
        .ticks(20)
        .usagePerTick(750)
        .itemByproducts("20% electrodynamics:dustiron");
    });
}
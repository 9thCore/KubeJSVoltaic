if (Platform.getMods().containsKey("electrodynamics")) {
    ServerEvents.recipes(event => {
        event.recipes.electrodynamics.mineral_washer_recipe(
            "750x minecraft:water",
            "2x cobblestone",
            "250x minecraft:lava"
        )
        .ticks(100)
        .usagePerTick(500);
    });
}
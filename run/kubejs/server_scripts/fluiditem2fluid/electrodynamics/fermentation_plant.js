if (Platform.getMods().containsKey("electrodynamics")) {
    ServerEvents.recipes(event => {
        event.recipes.electrodynamics.fermentation_plant_recipe(
            "270x electrodynamics:fluidethanol",
            "4x fermented_spider_eye",
            "250x electrodynamics:fluidethanol"
        )
        .ticks(100)
        .usagePerTick(500);
    });
}
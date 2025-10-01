if (Platform.getMods().containsKey("nuclearscience")) {
    ServerEvents.recipes(event => {
        event.recipes.nuclearscience.radioactive_processor_recipe(
            "mossy_cobblestone",
            "cobblestone",
            "250x water"
        )
        .ticks(100)
        .usagePerTick(200);
    });
}
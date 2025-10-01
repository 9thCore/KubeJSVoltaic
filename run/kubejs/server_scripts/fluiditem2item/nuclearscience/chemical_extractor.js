if (Platform.getMods().containsKey("nuclearscience")) {
    ServerEvents.recipes(event => {
        event.recipes.nuclearscience.chemical_extractor_recipe(
            "minecraft:torch",
            "stick",
            "50x lava"
        )
        .ticks(50)
        .usagePerTick(200);
    });
}
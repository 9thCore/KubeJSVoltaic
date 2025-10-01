if (Platform.getMods().containsKey("nuclearscience")) {
    ServerEvents.recipes(event => {
        // Requires 3 inputs!
        event.recipes.nuclearscience.msrfuel_preprocessor_recipe(
            "64x torch",
            ["electrodynamics:coalcoke", "charcoal", "stick"],
            "25x lava"
        )
        .ticks(50)
        .usagePerTick(200);
    });
}
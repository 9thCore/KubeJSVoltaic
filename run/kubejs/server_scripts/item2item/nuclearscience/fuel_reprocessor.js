if (Platform.getMods().containsKey("nuclearscience")) {
    ServerEvents.recipes(event => {
        event.recipes.nuclearscience.fuel_reprocessor_recipe(
            "charcoal",
            "charcoal"
        )
        .ticks(50)
        .usagePerTick(200)
        .itemByproducts("10% electrodynamics:coalcoke");
    });
}
ServerEvents.recipes(event => {
    for (let i = 0; i < 1; i++) {
        event.recipes.electrodynamics.electrolytic_separator_recipe(
            "250x electrodynamics:hydrogen",
            "250x lava"
        )
        .gasByproducts("electrodynamics:hydrogen") // uses an implicit 1000mB of hydrogen at room temperature and pressure, if not given (can't have an empty byproduct for this recipe)
        .ticks(60)
        .usagePerTick(250);
    }
});
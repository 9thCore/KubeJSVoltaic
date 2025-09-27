ServerEvents.recipes(event => {
    event.recipes.electrodynamics.chemical_crystallizer_recipe(
        "dripstone_block",
        "250x water"
    )
    .ticks(60)
    .usagePerTick(250);
});
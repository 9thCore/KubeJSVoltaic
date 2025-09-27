ServerEvents.recipes(event => {
    event.recipes.electrodynamics.chemical_mixer_recipe(
        "500x water",
        "electrodynamics:coil",
        "250x water"
    )
    .ticks(100)
    .usagePerTick(500);
});
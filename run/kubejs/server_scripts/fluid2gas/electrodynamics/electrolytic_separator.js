ServerEvents.recipes(event => {
    event.recipes.electrodynamics.electrolytic_separator_recipe(
        "250x electrodynamics:hydrogen",
        "250x lava"
    )
    // defaults to 1000mB of hydrogen gas at room temperature and sea level pressure (limitation, recipe requires two gasses)
    // use .gasByproducts("") to use an empty gas!
    .gasByproducts("")
    //.gasByproducts("15% electrodynamics:hydrogen") // chances don't seem to work fully for this one
    .ticks(60)
    .usagePerTick(250);

    // water -> 3x argon + 2x hydrogen (lol)
    event.replaceOutput({
        mod: "electrodynamics",
        type: "electrodynamics:electrolytic_separator_recipe"
    }, "electrodynamics:oxygen", "3x electrodynamics:argon");
});
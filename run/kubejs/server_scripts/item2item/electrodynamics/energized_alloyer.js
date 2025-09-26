ServerEvents.recipes(event => {
    event.recipes.electrodynamics.energized_alloyer_recipe(
        "6x blaze_powder",
        ["3x minecraft:glowstone_dust", Item.of("lava_bucket")]
    )
    .ticks(60)
    .usagePerTick(250)
    .itemByproducts("bucket");
});
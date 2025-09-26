ServerEvents.recipes(event => {
    event.recipes.electrodynamics.mineral_crusher_recipe(
        "minecraft:end_stone",
        "end_stone"
    )
    .ticks(60)
    .usagePerTick(250)
    .itemByproducts("15% end_stone");

    event.replaceOutput({
        mod: "electrodynamics",
        type: "electrodynamics:mineral_crusher_recipe"
    }, "flint", "bucket");
});
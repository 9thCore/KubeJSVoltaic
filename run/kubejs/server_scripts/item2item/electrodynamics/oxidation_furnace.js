ServerEvents.recipes(event => {
    event.recipes.electrodynamics.oxidation_furnace_recipe(
        "minecraft:mossy_cobblestone",
        ["vine", "cobblestone"]
    )
    .ticks(1200)
    .usagePerTick(10)
    .experience(1.5)
    .itemByproducts("16% minecraft:stone");

    event.recipes.electrodynamics.oxidation_furnace_recipe(
        "minecraft:redstone_block",
        ["glowstone", "4x redstone"]
    )
    .ticks(10)
    .usagePerTick(6000)
    .itemByproducts("4x glowstone_dust");
});
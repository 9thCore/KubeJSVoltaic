ServerEvents.recipes(event => {
    event.recipes.electrodynamics.wire_mill_recipe(
        "minecraft:grass_block",
        Item.of("minecraft:dirt", 2)
    )
    .ticks(50)
    .usagePerTick(200)
    .experience(0.5)
    .itemByproducts(VoltaicProbableItem.of("minecraft:gold_nugget", 0.5))
});
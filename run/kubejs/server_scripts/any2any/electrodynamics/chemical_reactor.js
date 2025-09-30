ServerEvents.recipes(event => {
    event.recipes.electrodynamics.chemical_reactor_recipe()
    .itemInputs(["minecraft:acacia_door", "acacia_button"])
    .gasInputs("#c:hydrogen")
    .fluidInputs("750x minecraft:lava")
    .itemOutput("acacia_boat")
    .gasOutput("150x electrodynamics:oxygen")
    .gasByproducts(VoltaicProbableGas.of("250x electrodynamics:oxygen", 0.5))
    .fluidOutput("150x electrodynamics:fluidammonia")
    .fluidByproducts("100x 25% minecraft:milk")
    .itemByproducts("16% 3x minecraft:stone")
    .usagePerTick(1000)
    .ticks(200);
});
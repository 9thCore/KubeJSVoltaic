if (Platform.getMods().containsKey("nuclearscience")) {
    ServerEvents.recipes(event => {
        event.recipes.nuclearscience.nuclear_boiler_recipe(
            VoltaicGasStack.of("electrodynamics:steam", 5000, 100000, 893),
            "nuclearscience:actinium225",
            "1000x water"
        )
        .ticks(100)
        .usagePerTick(200);
    });
}
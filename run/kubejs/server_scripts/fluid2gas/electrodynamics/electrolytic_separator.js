if (Platform.getMods().containsKey("electrodynamics")) {
    ServerEvents.recipes(event => {
        event.recipes.electrodynamics.electrolytic_separator_recipe(
            "250x electrodynamics:hydrogen",
            "250x lava"
        )
        .ticks(60)
        .usagePerTick(250);

        // water -> 3x argon + 2x hydrogen (lol)
        event.replaceOutput({
            mod: "electrodynamics",
            type: "electrodynamics:electrolytic_separator_recipe"
        }, "electrodynamics:oxygen", "3x electrodynamics:argon");
    });
}
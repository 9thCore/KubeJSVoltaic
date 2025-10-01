if (Platform.getMods().containsKey("electrodynamics")) {
    ServerEvents.recipes(event => {
        // dirt + cobblestone -> 2 coarse dirt
        event.recipes.electrodynamics.reinforced_alloyer_recipe(
            "2x minecraft:coarse_dirt",
            ["dirt", "cobblestone"]
        )
        .ticks(10)
        .usagePerTick(200)
        .experience(0.5);

        // all steel is replaced with obsidian
        event.replaceInput(
            {
                "mod": "electrodynamics",
                "type": "electrodynamics:reinforced_alloyer_recipe"
            },
            "electrodynamics:coalcoke",
            "obsidian"
        );

        // 8 steel ingot + vanadium ingot -> 4 iron blocks, 75% metallic slag
        event.replaceOutput(
            {
                "mod": "electrodynamics",
                "type": "electrodynamics:reinforced_alloyer_recipe"
            },
            "electrodynamics:ingotvanadiumsteel",
            "4x minecraft:iron_block"
        );
    });
}
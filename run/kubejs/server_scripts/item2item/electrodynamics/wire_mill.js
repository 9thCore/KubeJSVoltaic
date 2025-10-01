if (Platform.getMods().containsKey("electrodynamics")) {
    ServerEvents.recipes(event => {
        // 2 dirt -> 1 grass, 50% gold nugget
        event.recipes.electrodynamics.wire_mill_recipe(
            "minecraft:grass_block",
            Item.of("minecraft:dirt", 2)
        )
        .ticks(50)
        .usagePerTick(200)
        .experience(0.5)
        .itemByproducts(VoltaicProbableItem.of("minecraft:gold_nugget", 0.5));

        // 9 stone -> titanium coil
        event.replaceInput(
            {
                "mod": "electrodynamics",
                "type": "electrodynamics:wire_mill_recipe"
            },
            "electrodynamics:ingottitanium",
            "minecraft:stone"
        );

        // copper ingot -> acacia door, 75% acacia button
        event.replaceOutput(
            {
                "mod": "electrodynamics",
                "type": "electrodynamics:wire_mill_recipe"
            },
            "electrodynamics:wirecopper",
            "minecraft:acacia_door"
        );

        // copper ingot -> acacia door, 75% acacia button
        event.replaceOutput(
            {
                "mod": "electrodynamics",
                "type": "electrodynamics:wire_mill_recipe"
            },
            "electrodynamics:nuggetcopper",
            VoltaicProbableItem.of("acacia_button", 0.75)
        );
    });
}
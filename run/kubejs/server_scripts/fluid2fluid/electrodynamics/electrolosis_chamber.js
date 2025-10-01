if (Platform.getMods().containsKey("electrodynamics")) {
    ServerEvents.recipes(event => {
        // these amounts only really change what's shown in JEI,
        // and how much fluid there must be in the input. (if "2x minecraft:water" for the input, then at least 2 units of water must be present before conversion happens)
        // but it's still a 1:1 conversion rate!
        // (nothing special happens if output is increased)
        event.recipes.electrodynamics.electrolosis_chamber_recipe(
            "1x minecraft:lava",
            "1x minecraft:water"
        );
    });
}
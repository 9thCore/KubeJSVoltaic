if (Platform.getMods().containsKey("blastcraft")) {
    ServerEvents.recipes(event => {
        event.recipes.blastcraft.blast_compressor_recipe(
            "blaze_rod",
            "12x blaze_powder"
        )
        .ticks(60)
        .usagePerTick(250)
        .itemByproducts(["50% 3x torch"]);
    });
}
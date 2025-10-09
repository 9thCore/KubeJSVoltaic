StartupEvents.registry("voltaic:gases", event => {
    event.create("milk_gas")
    .displayName("milk Gas   yum")
    .condensate("minecraft:milk", 100)
    .color(Color.YELLOW);
});
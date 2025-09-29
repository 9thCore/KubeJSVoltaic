ServerEvents.loaded(event => {
    let stack = VoltaicGasStack.of("electrodynamics:hydrogen");
    console.log(`We have ${stack}`);

    stack.heat(100);
    console.log(`Heated up by 100K, now it's ${stack}`);

    stack.setTemperature(100);
    console.log(`Set temperature to 100K, now it's ${stack}`);

    let stack2 = VoltaicGasStack.of("electrodynamics:oxygen");
    console.log(`We have second gas ${stack2}`);

    stack2.setPressure(50);
    console.log(`Set pressure to 50 ATM, now it's ${stack2}`);


    let ingredient = VoltaicGasIngredient.of("750x #c:nitrogen");
    console.log(`We have first ingredient ${ingredient}`);

    let ingredient2 = ingredient.withPressure(5);
    console.log(`Copied to second ingredient with 5 ATM, now it's ${ingredient2}`);

    let ingredient3 = ingredient2.withTemperature(1900);
    console.log(`Copied to third ingredient with 1900 K, which is ${ingredient3}`);

    let ingredient4 = VoltaicGasIngredient.of("electrodynamics:hydrogen", 1500, 980, 17);

    console.log(`We have fourth, new ingredient ${ingredient4}`);
});
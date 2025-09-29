ServerEvents.loaded(event => {
    const stack = VoltaicGasStack.of("electrodynamics:hydrogen");
    console.log(`We have ${stack}`);

    stack.heat(100);
    console.log(`Heated up by 100K, now it's ${stack}`);

    stack.setTemperature(100);
    console.log(`Set temperature to 100K, now it's ${stack}`);

    const stack2 = VoltaicGasStack.of("electrodynamics:oxygen");
    console.log(`We have second gas ${stack2}`);

    stack2.setPressure(50);
    console.log(`Set pressure to 50 ATM, now it's ${stack2}`);
});
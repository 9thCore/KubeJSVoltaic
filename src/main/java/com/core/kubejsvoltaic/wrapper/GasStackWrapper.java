package com.core.kubejsvoltaic.wrapper;

import dev.latvian.mods.kubejs.typings.Info;
import voltaic.api.gas.GasStack;

public interface GasStackWrapper {
    @Info("Returns an instance of Voltaic's GasStack of the input")
     static GasStack of(GasStack stack) {
        return of(stack, stack.getAmount(), stack.getTemperature(), stack.getPressure());
    }

    @Info("Returns an instance of Voltaic's GasStack of the input, with the given amount")
    static GasStack of(GasStack stack, int amount) {
        return of(stack, amount, stack.getTemperature(), stack.getPressure());
    }

    @Info("Returns an instance of Voltaic's GasStack of the input, with the given amount and temperature")
    static GasStack of(GasStack stack, int amount, int temperature) {
        return of(stack, amount, temperature, stack.getPressure());
    }

    @Info("Returns an instance of Voltaic's GasStack of the input, with the given amount, temperature and pressure")
    static GasStack of(GasStack stack, int amount, int temperature, int pressure) {
        return new GasStack(stack.getGas(), amount, temperature, pressure);
    }
}

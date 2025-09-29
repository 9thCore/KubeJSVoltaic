package com.core.kubejsvoltaic.mixin;

import com.core.kubejsvoltaic.extension.GasStackKJS;
import org.spongepowered.asm.mixin.Mixin;
import voltaic.api.gas.GasStack;

@Mixin(GasStack.class)
public abstract class GasStackMixin implements GasStackKJS {
    @Override
    public GasStack kjs$self() {
        return (GasStack) (Object) this;
    }
}

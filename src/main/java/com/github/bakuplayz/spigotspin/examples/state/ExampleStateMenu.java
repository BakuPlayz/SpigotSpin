package com.github.bakuplayz.spigotspin.examples.state;

import com.github.bakuplayz.spigotspin.menu.menus.SizeType;
import com.github.bakuplayz.spigotspin.menu.menus.abstracts.AbstractStateMenu;
import org.jetbrains.annotations.NotNull;

public final class ExampleStateMenu extends AbstractStateMenu<ExampleState, ExampleStateHandler> {


    public ExampleStateMenu() {
        super("State example");
    }


    @Override
    public void setItems() {
        setItem(9, new ExampleItem("Test item"), (ignored, player) -> stateHandler.incrementCounter(), ExampleStateFlag.COUNT);
    }


    @Override
    public SizeType getSizeType() {
        return SizeType.DYNAMIC;
    }


    @NotNull
    @Override
    public ExampleStateHandler createStateHandler() {
        return new ExampleStateHandler(this);
    }
}

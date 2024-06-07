package com.github.bakuplayz.spigotspin.examples.shared;

import com.github.bakuplayz.spigotspin.abstraction.menu.menus.AbstractDynamicSharedMenu;

public final class ExampleSharedMenu extends AbstractDynamicSharedMenu<ExampleState> {

    private final ExampleStateHandler stateHandler;


    public ExampleSharedMenu() {
        super("State example");
        this.stateHandler = new ExampleStateHandler(this);
    }


    @Override
    public void setItems() {
        setItem(9, new ExampleItem("Test item"), (ignored, player) -> stateHandler.incrementCounter(), ExampleStateFlag.COUNT);
    }

}

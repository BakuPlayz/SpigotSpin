package dev.bakuplayz.spigotspin.examples.state;

import dev.bakuplayz.spigotspin.abstraction.menu.menus.AbstractDynamicStateMenu;

public final class ExampleStateMenu extends AbstractDynamicStateMenu<ExampleState> {

    private final ExampleStateHandler stateHandler;


    public ExampleStateMenu() {
        super("State example");
        this.stateHandler = new ExampleStateHandler(this);
    }


    @Override
    public void setItems() {
        setItem(9, new ExampleItem("Test item"), (ignored, player) -> stateHandler.incrementCounter(), ExampleStateFlag.COUNT);
    }

}

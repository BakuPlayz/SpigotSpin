package dev.bakuplayz.spigotspin.examples.shared;

import dev.bakuplayz.spigotspin.abstraction.menus.AbstractDynamicSharedMenu;

public final class ExampleSharedMenu extends AbstractDynamicSharedMenu<ExampleState> {

    private final ExampleStateHandler stateHandler;


    public ExampleSharedMenu() {
        super("State example");
        this.stateHandler = new ExampleStateHandler(this);
    }


    @Override
    public void setItems() {
        setItem(9, new ExampleItem("Test item"), (ignored) -> stateHandler.incrementCounter(), ExampleStateFlag.COUNT);
    }

}

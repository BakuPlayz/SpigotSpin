package com.github.bakuplayz.spigotspin.examples.shared;

import com.github.bakuplayz.spigotspin.menu.menus.SizeType;
import com.github.bakuplayz.spigotspin.menu.menus.abstracts.AbstractSharedMenu;

public final class ExampleSharedMenu extends AbstractSharedMenu<ExampleState> {

    private final ExampleStateHandler stateHandler;


    public ExampleSharedMenu() {
        super("State example");
        this.stateHandler = new ExampleStateHandler(this);
    }


    @Override
    public void setItems() {
        setItem(9, new ExampleItem("Test item"), (ignored, player) -> stateHandler.incrementCounter(), ExampleStateFlag.COUNT);
    }


    @Override
    public SizeType getSizeType() {
        return SizeType.DYNAMIC;
    }

}

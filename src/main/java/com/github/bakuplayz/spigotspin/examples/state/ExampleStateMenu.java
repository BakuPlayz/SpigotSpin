package com.github.bakuplayz.spigotspin.examples.state;

import com.github.bakuplayz.spigotspin.menu.menus.SizeType;
import com.github.bakuplayz.spigotspin.menu.menus.abstracts.AbstractStateMenu;

public final class ExampleStateMenu extends AbstractStateMenu<ExampleState> {

    private final ExampleStateHandler stateHandler;


    public ExampleStateMenu() {
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

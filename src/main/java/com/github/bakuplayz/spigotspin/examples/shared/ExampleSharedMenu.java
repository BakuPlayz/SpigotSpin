package com.github.bakuplayz.spigotspin.examples.shared;

import com.github.bakuplayz.spigotspin.menu.menus.SizeType;
import com.github.bakuplayz.spigotspin.menu.menus.abstracts.AbstractSharedMenu;
import org.jetbrains.annotations.NotNull;

public final class ExampleSharedMenu extends AbstractSharedMenu<ExampleState, ExampleStateHandler> {


    public ExampleSharedMenu() {
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

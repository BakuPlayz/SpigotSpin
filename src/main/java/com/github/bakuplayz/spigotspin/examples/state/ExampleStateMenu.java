package com.github.bakuplayz.spigotspin.examples.state;

import com.github.bakuplayz.spigotspin.menu.items.actions.ClickableAction;
import com.github.bakuplayz.spigotspin.menu.common.SizeType;
import com.github.bakuplayz.spigotspin.menu.abstracts.AbstractStateMenu;
import org.jetbrains.annotations.NotNull;

public final class ExampleStateMenu extends AbstractStateMenu<ExampleState, ExampleStateHandler> {


    public ExampleStateMenu() {
        super("State example");
    }


    @Override
    public void setItems() {
        setItem(9, new ExampleItem("Test item"), getExampleAction(), ExampleStateFlag.COUNT);
    }


    @NotNull
    public ClickableAction<ExampleItem> getExampleAction() {
        return (ignored, player) -> stateHandler.incrementCounter();
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

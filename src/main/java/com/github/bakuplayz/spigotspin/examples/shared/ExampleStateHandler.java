package com.github.bakuplayz.spigotspin.examples.shared;

import com.github.bakuplayz.spigotspin.menu.menus.common.state.MenuStateHandler;
import com.github.bakuplayz.spigotspin.menu.menus.common.state.MenuStateObserver;
import org.jetbrains.annotations.NotNull;

public final class ExampleStateHandler extends MenuStateHandler<ExampleState, MenuStateObserver<ExampleState>> {

    public ExampleStateHandler(@NotNull MenuStateObserver<ExampleState> observer) {
        super(observer, new ExampleState());
    }


    public void incrementCounter() {
        updateState(state.getCount(), (counter) -> counter + 1, ExampleStateFlag.COUNT);
    }


    @Override
    protected ExampleState onUpdateState(@NotNull Object partialState, int flag) {
        if (flag == ExampleStateFlag.COUNT) {
            state.setCount(infer(partialState));
        }

        return state;
    }

}

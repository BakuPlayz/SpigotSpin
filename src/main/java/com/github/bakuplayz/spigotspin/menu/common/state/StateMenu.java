package com.github.bakuplayz.spigotspin.menu.common.state;

import com.github.bakuplayz.spigotspin.menu.Menu;
import com.github.bakuplayz.spigotspin.menu.items.actions.ClickableAction;
import com.github.bakuplayz.spigotspin.menu.items.state.ClickableStateItem;
import com.github.bakuplayz.spigotspin.menu.items.state.StateItem;
import org.jetbrains.annotations.NotNull;

import java.util.List;

public interface StateMenu<S extends MenuState, SH extends MenuStateHandler<S, ? extends MenuStateObserver<S>>> extends Menu {

    SH createStateHandler();


    void setItem(int position, @NotNull StateItem<S> item, int flag);


    void setItemIf(boolean setIfTrue, int position, @NotNull StateItem<S> item, int flag);


    void setItem(int position, @NotNull StateItem<S> item, @NotNull List<Integer> flags);


    void setItemIf(boolean setIfTrue, int position, @NotNull StateItem<S> item, @NotNull List<Integer> flags);


    <I extends ClickableStateItem<S>> void setItem(int position, @NotNull I item, @NotNull ClickableAction<I> action, int flag);


    <I extends ClickableStateItem<S>> void setItemIf(boolean setIfTrue, int position, @NotNull I item, @NotNull ClickableAction<I> action, int flag);


    <I extends ClickableStateItem<S>> void setItem(int position, @NotNull I item, @NotNull ClickableAction<I> action, @NotNull List<Integer> flags);


    <I extends ClickableStateItem<S>> void setItemIf(boolean setIfTrue, int position, @NotNull I item, @NotNull ClickableAction<I> action, @NotNull List<Integer> flags);

}

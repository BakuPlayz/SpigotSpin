package com.github.bakuplayz.spigotspin.menu.menus.common.state;

import com.github.bakuplayz.spigotspin.menu.items.actions.ClickableAction;
import com.github.bakuplayz.spigotspin.menu.items.state.ClickableStateItem;
import com.github.bakuplayz.spigotspin.menu.items.state.StateItem;
import com.github.bakuplayz.spigotspin.menu.menus.Menu;
import org.jetbrains.annotations.NotNull;

import java.util.List;

public interface StateMenu<S extends MenuState> extends Menu {

    void setItem(int position, @NotNull StateItem<S> item, int flag);


    void setItem(int position, @NotNull ClickableStateItem<S> item, @NotNull ClickableAction<ClickableStateItem<S>> action, int flag);


    void setItem(int position, @NotNull ClickableStateItem<S> item, @NotNull ClickableAction<ClickableStateItem<S>> action, @NotNull List<Integer> flags);

}

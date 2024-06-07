package com.github.bakuplayz.spigotspin.abstraction.menu.menus.state;

import com.github.bakuplayz.spigotspin.abstraction.menu.items.actions.ClickableAction;
import com.github.bakuplayz.spigotspin.abstraction.menu.items.state.ClickableStateItem;
import com.github.bakuplayz.spigotspin.abstraction.menu.items.state.StateItem;
import com.github.bakuplayz.spigotspin.abstraction.menu.menus.DynamicMenu;
import com.github.bakuplayz.spigotspin.abstraction.menu.menus.plain.DynamicPlainMenu;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Range;

import java.util.List;

public interface DynamicStateMenu<S extends MenuState> extends DynamicMenu, DynamicPlainMenu {

    void setItem(
            @Range(from = 0, to = DYNAMIC_MENU_MAX_SIZE) int position,
            @NotNull StateItem<S> item,
            int flag
    );


    void setItem(
            @Range(from = 0, to = DYNAMIC_MENU_MAX_SIZE) int position,
            @NotNull ClickableStateItem<S> item,
            @NotNull ClickableAction<ClickableStateItem<S>> action,
            int flag
    );


    void setItem(
            @Range(from = 0, to = DYNAMIC_MENU_MAX_SIZE) int position,
            @NotNull ClickableStateItem<S> item,
            @NotNull ClickableAction<ClickableStateItem<S>> action,
            @NotNull List<Integer> flags
    );

}

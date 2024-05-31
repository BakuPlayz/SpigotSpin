package dev.bakuplayz.spigotspin.abstraction.menus.state;

import dev.bakuplayz.spigotspin.abstraction.items.actions.ClickableAction;
import dev.bakuplayz.spigotspin.abstraction.items.state.ClickableStateItem;
import dev.bakuplayz.spigotspin.abstraction.items.state.StateItem;
import dev.bakuplayz.spigotspin.abstraction.menus.DynamicMenu;
import dev.bakuplayz.spigotspin.abstraction.menus.plain.DynamicPlainMenu;
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
            @NotNull ClickableAction action,
            int flag
    );


    void setItem(
            @Range(from = 0, to = DYNAMIC_MENU_MAX_SIZE) int position,
            @NotNull ClickableStateItem<S> item,
            @NotNull ClickableAction action,
            @NotNull List<Integer> flags
    );

}

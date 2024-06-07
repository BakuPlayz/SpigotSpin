package com.github.bakuplayz.spigotspin.abstraction.menu.menus.plain;

import com.github.bakuplayz.spigotspin.abstraction.menu.items.ClickableItem;
import com.github.bakuplayz.spigotspin.abstraction.menu.items.DraggableItem;
import com.github.bakuplayz.spigotspin.abstraction.menu.items.Item;
import com.github.bakuplayz.spigotspin.abstraction.menu.items.actions.ClickableAction;
import com.github.bakuplayz.spigotspin.abstraction.menu.items.actions.DraggableAction;
import com.github.bakuplayz.spigotspin.abstraction.menu.menus.DynamicMenu;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Range;

public interface DynamicPlainMenu extends DynamicMenu {

    void setItem(@Range(from = 0, to = DYNAMIC_MENU_MAX_SIZE) int position, @NotNull Item item);


    void setItem(@Range(from = 0, to = DYNAMIC_MENU_MAX_SIZE) int position, @NotNull DraggableItem item, @NotNull DraggableAction action);


    void setItem(@Range(from = 0, to = DYNAMIC_MENU_MAX_SIZE) int position, @NotNull ClickableItem item, @NotNull ClickableAction<ClickableItem> action);

}

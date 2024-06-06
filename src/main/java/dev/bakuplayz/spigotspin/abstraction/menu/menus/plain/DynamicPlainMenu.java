package dev.bakuplayz.spigotspin.abstraction.menu.menus.plain;

import dev.bakuplayz.spigotspin.abstraction.menu.items.ClickableItem;
import dev.bakuplayz.spigotspin.abstraction.menu.items.DraggableItem;
import dev.bakuplayz.spigotspin.abstraction.menu.items.Item;
import dev.bakuplayz.spigotspin.abstraction.menu.items.actions.ClickableAction;
import dev.bakuplayz.spigotspin.abstraction.menu.items.actions.DraggableAction;
import dev.bakuplayz.spigotspin.abstraction.menu.menus.DynamicMenu;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Range;

public interface DynamicPlainMenu extends DynamicMenu {

    void setItem(@Range(from = 0, to = DYNAMIC_MENU_MAX_SIZE) int position, @NotNull Item item);


    void setItem(@Range(from = 0, to = DYNAMIC_MENU_MAX_SIZE) int position, @NotNull DraggableItem item, @NotNull DraggableAction action);


    void setItem(@Range(from = 0, to = DYNAMIC_MENU_MAX_SIZE) int position, @NotNull ClickableItem item, @NotNull ClickableAction<ClickableItem> action);

}

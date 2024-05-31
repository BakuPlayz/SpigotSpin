package dev.bakuplayz.spigotspin.abstraction.menus.plain;

import dev.bakuplayz.spigotspin.abstraction.items.ClickableItem;
import dev.bakuplayz.spigotspin.abstraction.items.DraggableItem;
import dev.bakuplayz.spigotspin.abstraction.items.Item;
import dev.bakuplayz.spigotspin.abstraction.items.actions.ClickableAction;
import dev.bakuplayz.spigotspin.abstraction.items.actions.DraggableAction;
import dev.bakuplayz.spigotspin.abstraction.menus.DynamicMenu;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Range;

public interface DynamicPlainMenu extends DynamicMenu {

    void setItem(@Range(from = 0, to = DYNAMIC_MENU_MAX_SIZE) int position, @NotNull Item item);


    void setItem(@Range(from = 0, to = DYNAMIC_MENU_MAX_SIZE) int position, @NotNull DraggableItem item, @NotNull DraggableAction action);


    void setItem(@Range(from = 0, to = DYNAMIC_MENU_MAX_SIZE) int position, @NotNull ClickableItem item, @NotNull ClickableAction action);

}

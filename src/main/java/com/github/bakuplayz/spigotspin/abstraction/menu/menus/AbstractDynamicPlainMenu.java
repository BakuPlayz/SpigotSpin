package com.github.bakuplayz.spigotspin.abstraction.menu.menus;

import com.github.bakuplayz.spigotspin.abstraction.menu.items.ClickableItem;
import com.github.bakuplayz.spigotspin.abstraction.menu.items.DraggableItem;
import com.github.bakuplayz.spigotspin.abstraction.menu.items.Item;
import com.github.bakuplayz.spigotspin.abstraction.menu.items.actions.ClickableAction;
import com.github.bakuplayz.spigotspin.abstraction.menu.items.actions.DraggableAction;
import com.github.bakuplayz.spigotspin.abstraction.menu.items.actions.ItemAction;
import com.github.bakuplayz.spigotspin.abstraction.menu.menus.plain.DynamicPlainMenu;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Range;

public abstract class AbstractDynamicPlainMenu extends AbstractDynamicMenu implements DynamicPlainMenu {

    protected AbstractDynamicPlainMenu(@NotNull String title) {
        super(title);
    }


    @Override
    public final void setItem(@Range(from = 0, to = DYNAMIC_MENU_MAX_SIZE) int position, @NotNull Item item) {
        item.setPosition(position);
        items.put(position, item);
    }


    @Override
    public final void setItem(@Range(from = 0, to = DYNAMIC_MENU_MAX_SIZE) int position, @NotNull DraggableItem item, @NotNull DraggableAction action) {
        item.setAction(action);
        item.setPosition(position);
        items.put(position, item);
    }


    @Override
    public final void setItem(@Range(from = 0, to = DYNAMIC_MENU_MAX_SIZE) int position, @NotNull ClickableItem item, @NotNull ClickableAction<ClickableItem> action) {
        item.setAction(action);
        item.setPosition(position);
        items.put(position, item);
    }

}

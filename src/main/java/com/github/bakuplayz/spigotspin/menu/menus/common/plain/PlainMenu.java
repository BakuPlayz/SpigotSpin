package com.github.bakuplayz.spigotspin.menu.menus.common.plain;

import com.github.bakuplayz.spigotspin.menu.items.ClickableItem;
import com.github.bakuplayz.spigotspin.menu.items.DraggableItem;
import com.github.bakuplayz.spigotspin.menu.items.Item;
import com.github.bakuplayz.spigotspin.menu.items.actions.ClickableAction;
import com.github.bakuplayz.spigotspin.menu.items.actions.DraggableAction;
import com.github.bakuplayz.spigotspin.menu.menus.DynamicMenu;
import org.jetbrains.annotations.NotNull;

public interface PlainMenu extends DynamicMenu {

    void setItem(int position, @NotNull Item item);


    void setItem(int position, @NotNull DraggableItem item, @NotNull DraggableAction action);


    void setItem(int position, @NotNull ClickableItem item, @NotNull ClickableAction<ClickableItem> action);

}

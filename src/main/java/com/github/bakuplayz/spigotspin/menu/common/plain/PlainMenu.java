package com.github.bakuplayz.spigotspin.menu.common.plain;

import com.github.bakuplayz.spigotspin.menu.items.ClickableItem;
import com.github.bakuplayz.spigotspin.menu.items.DraggableItem;
import com.github.bakuplayz.spigotspin.menu.items.Item;
import com.github.bakuplayz.spigotspin.menu.items.actions.ClickableAction;
import com.github.bakuplayz.spigotspin.menu.items.actions.DraggableAction;
import org.jetbrains.annotations.NotNull;

public interface PlainMenu {

    void setItem(int position, @NotNull Item item);


    void setItemIf(boolean setIfTrue, int position, @NotNull Item item);


    void setItem(int position, @NotNull DraggableItem item, @NotNull DraggableAction action);


    void setItemIf(boolean setIfTrue, int position, @NotNull DraggableItem item, @NotNull DraggableAction action);


    void setItem(int position, @NotNull ClickableItem item, @NotNull ClickableAction<ClickableItem> action);


    void setItemIf(boolean setIfTrue, int position, @NotNull ClickableItem item, @NotNull ClickableAction<ClickableItem> action);

}

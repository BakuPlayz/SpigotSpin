package dev.bakuplayz.spigotspin.abstraction.menus;

import dev.bakuplayz.spigotspin.abstraction.items.ClickableItem;
import dev.bakuplayz.spigotspin.abstraction.items.DraggableItem;
import dev.bakuplayz.spigotspin.abstraction.items.Item;
import dev.bakuplayz.spigotspin.abstraction.items.actions.ClickableAction;
import dev.bakuplayz.spigotspin.abstraction.items.actions.DraggableAction;
import dev.bakuplayz.spigotspin.abstraction.menus.plain.DynamicPlainMenu;
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
    public final void setItem(@Range(from = 0, to = DYNAMIC_MENU_MAX_SIZE) int position, @NotNull ClickableItem item, @NotNull ClickableAction action) {
        item.setAction(action);
        item.setPosition(position);
        items.put(position, item);
    }

}

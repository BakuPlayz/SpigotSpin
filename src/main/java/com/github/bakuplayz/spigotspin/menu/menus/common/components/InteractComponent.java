package com.github.bakuplayz.spigotspin.menu.menus.common.components;

import com.github.bakuplayz.spigotspin.menu.items.Clickable;
import com.github.bakuplayz.spigotspin.menu.items.Draggable;
import com.github.bakuplayz.spigotspin.menu.items.Item;
import com.github.bakuplayz.spigotspin.menu.listeners.events.ExtendedInventoryDragEvent;
import com.github.bakuplayz.spigotspin.menu.menus.DragState;
import com.github.bakuplayz.spigotspin.menu.menus.Menu;
import lombok.AllArgsConstructor;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.jetbrains.annotations.NotNull;

@AllArgsConstructor
public final class InteractComponent {

    private final Menu menu;


    public void handleClick(@NotNull InventoryClickEvent event) {
        Item item = menu.getItems().get(event.getSlot());

        switch (menu.getDragState()) {
            case BOTH_MENU_AND_INVENTORY:
                if (isInventoryDrag(event) || isMenuDrag(event) && !(item instanceof Clickable)) return;
                break;

            case MENU_ONLY:
                if (isMenuDrag(event) && !(item instanceof Clickable)) return;
                break;

            case INVENTORY_ONLY:
                if (isInventoryDrag(event)) return;
                break;

            case DISABLED:
                break;
        }

        event.setCancelled(true);

        if (!(item instanceof Clickable) || event.getCurrentItem() == null) {
            return;
        }

        ((Clickable) item).performAction((Player) event.getWhoClicked());
    }


    public void handleDrag(@NotNull ExtendedInventoryDragEvent event) {
        if (menu.getDragState() == DragState.DISABLED) {
            event.setCancelled(true);
            return;
        }

        Item item = menu.getItems().get(event.getOldSlot());

        if (!(item instanceof Draggable)) {
            return;
        }

        ((Draggable) item).performAction((Player) event.getWhoClicked());
    }


    public void handleClose(@NotNull InventoryCloseEvent event) {
        // Do nothing yet...
    }


    private boolean isMenuDrag(@NotNull InventoryClickEvent event) {
        return event.getClickedInventory() == menu.getInventory();
    }


    private boolean isInventoryDrag(@NotNull InventoryClickEvent event) {
        return event.getWhoClicked().getInventory() == event.getClickedInventory();
    }

}

package com.github.bakuplayz.spigotspin.menu.listeners;

import com.github.bakuplayz.spigotspin.menu.listeners.events.ExtendedInventoryDragEvent;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.event.inventory.InventoryOpenEvent;
import org.jetbrains.annotations.NotNull;

public interface MenuHandler {

    default boolean shouldCloseClickingOutside() {
        return true;
    }


    default long getBackStackClearingTime() {
        return 5000L;
    }


    void handleClick(@NotNull InventoryClickEvent event);


    void handleDrag(@NotNull ExtendedInventoryDragEvent event);


    void handleClose(@NotNull InventoryCloseEvent event);


    void handleOpen(@NotNull InventoryOpenEvent event);

}

package com.github.bakuplayz.spigotspin.abstraction.menu.menus;

import com.github.bakuplayz.spigotspin.abstraction.menu.listeners.events.ExtendedInventoryDragEvent;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.InventoryHolder;
import org.jetbrains.annotations.NotNull;

public interface DynamicMenu extends InventoryHolder {

    int DYNAMIC_MENU_MAX_SIZE = 54;


    default boolean shouldCloseClickingOutside() {
        return true;
    }


    void handleClick(@NotNull InventoryClickEvent event);


    void handleDrag(@NotNull ExtendedInventoryDragEvent event);


    void open(@NotNull Player player);


    void close(@NotNull Player player);


    void setItems();


    int getSize();

}

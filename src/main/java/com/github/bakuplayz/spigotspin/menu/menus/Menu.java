package com.github.bakuplayz.spigotspin.menu.menus;

import com.github.bakuplayz.spigotspin.menu.items.Item;
import com.github.bakuplayz.spigotspin.menu.listeners.MenuHandler;
import com.github.bakuplayz.spigotspin.menu.menus.common.handlers.OpenInventoryHandler;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.jetbrains.annotations.NotNull;

import java.util.Map;
import java.util.UUID;

public interface Menu extends MenuHandler {

    String IDENTIFIER = UUID.randomUUID().toString();


    void open(@NotNull Player player, @NotNull OpenInventoryHandler handler);


    void open(@NotNull Player player);


    void close(@NotNull Player player);


    void onPoppedTo();


    void setItems();


    Map<Integer, Item> getItems();


    int getSize();


    int getMaxSize();


    Inventory getInventory();


    default DragState getDragState() {
        return DragState.DISABLED;
    }


    default SizeType getSizeType() {
        return SizeType.INVALID;
    }

}

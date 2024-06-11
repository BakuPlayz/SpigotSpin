package com.github.bakuplayz.spigotspin.abstraction.menu.menus;

import com.github.bakuplayz.spigotspin.abstraction.menu.listeners.MenuHandler;
import com.github.bakuplayz.spigotspin.abstraction.menu.menus.handlers.OpenInventoryHandler;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.jetbrains.annotations.NotNull;

import java.util.UUID;

public interface DynamicMenu extends MenuHandler {

    int DYNAMIC_MENU_MAX_SIZE = 54;

    String IDENTIFIER = UUID.randomUUID().toString();


    void open(@NotNull Player player, @NotNull OpenInventoryHandler handler);


    void open(@NotNull Player player);


    void close(@NotNull Player player);


    void setItems();


    int getSize();


    Inventory getInventory();

}

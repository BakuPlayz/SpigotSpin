package com.github.bakuplayz.spigotspin.abstraction.menu.listeners;

import com.github.bakuplayz.spigotspin.abstraction.menu.listeners.events.ExtendedInventoryDragEvent;
import com.github.bakuplayz.spigotspin.abstraction.menu.menus.AbstractDynamicMenu;
import com.github.bakuplayz.spigotspin.abstraction.menu.menus.AbstractDynamicSharedMenu;
import com.github.bakuplayz.spigotspin.abstraction.menu.menus.DynamicMenu;
import com.github.bakuplayz.spigotspin.abstraction.menu.menus.shared.SharedInternal;
import org.bukkit.entity.HumanEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryAction;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.event.inventory.InventoryDragEvent;
import org.bukkit.event.server.PluginDisableEvent;
import org.bukkit.inventory.InventoryHolder;
import org.jetbrains.annotations.NotNull;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

public final class MenuListener implements Listener {

    private final static Set<InventoryAction> ALLOWED_ACTIONS = new HashSet<>(Arrays.asList(
            InventoryAction.PLACE_ALL,
            InventoryAction.PLACE_ONE,
            InventoryAction.PLACE_SOME,
            InventoryAction.PICKUP_ALL,
            InventoryAction.PICKUP_HALF,
            InventoryAction.PICKUP_ONE,
            InventoryAction.PICKUP_SOME,
            InventoryAction.NOTHING
    ));

    private final ConcurrentMap<String, Integer> lastClickedItemLocation = new ConcurrentHashMap<>();


    @EventHandler(priority = EventPriority.LOW)
    public void onMenuClick(@NotNull InventoryClickEvent event) {
        InventoryHolder holder = event.getInventory().getHolder();

        if (!(event.getWhoClicked() instanceof Player)) {
            return;
        }

        if (!(holder instanceof DynamicMenu)) {
            return;
        }
        
        if (!isAllowedInventoryAction(event.getAction())) {
            event.setCancelled(true);
            return;
        }

        lastClickedItemLocation.put(
                event.getWhoClicked().getUniqueId().toString(),
                event.getSlot()
        );

        DynamicMenu menu = ((DynamicMenu) holder);
        if (menu.shouldCloseClickingOutside() && event.getRawSlot() == -999) {
            event.getWhoClicked().closeInventory();
            event.setCancelled(true);
            return;
        }

        menu.handleClick(event);
    }


    @EventHandler(priority = EventPriority.LOW)
    public void onMenuDrag(@NotNull InventoryDragEvent event) {
        InventoryHolder holder = event.getInventory().getHolder();
        HumanEntity entity = event.getWhoClicked();

        if (!(entity instanceof Player)) {
            return;
        }

        if (!(holder instanceof DynamicMenu)) {
            return;
        }

        String playerId = entity.getUniqueId().toString();
        Integer slot = lastClickedItemLocation.get(playerId);

        if (slot == null || slot == -1) {
            lastClickedItemLocation.put(playerId, null);
            return;
        }

        ((DynamicMenu) holder).handleDrag(new ExtendedInventoryDragEvent(event, slot));
    }


    @EventHandler(priority = EventPriority.LOW)
    public void onMenuLeave(@NotNull InventoryCloseEvent event) {
        InventoryHolder holder = event.getInventory().getHolder();

        if (!(holder instanceof AbstractDynamicMenu)) {
            return;
        }

        HumanEntity entity = event.getPlayer();
        if (!(entity instanceof Player)) {
            return;
        }

        ((AbstractDynamicMenu) holder).handleClose(event);

        if (!(holder instanceof AbstractDynamicSharedMenu)) {
            return;
        }

        ((AbstractDynamicSharedMenu<?>) holder).leave((Player) event.getPlayer());
    }


    @EventHandler(priority = EventPriority.LOW)
    public void onDisable(@NotNull PluginDisableEvent event) {
        lastClickedItemLocation.clear();
        SharedInternal.STATE.ACTIVE_MENUS.clear();
        SharedInternal.STATE.PLAYER_OPENED_MENUS.clear();
    }


    private boolean isAllowedInventoryAction(@NotNull InventoryAction action) {
        return ALLOWED_ACTIONS.contains(action);
    }

}

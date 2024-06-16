package com.github.bakuplayz.spigotspin.abstraction.menu.listeners;

import com.github.bakuplayz.spigotspin.abstraction.menu.MenuManager;
import com.github.bakuplayz.spigotspin.abstraction.menu.listeners.events.ExtendedInventoryDragEvent;
import com.github.bakuplayz.spigotspin.abstraction.menu.menus.AbstractDynamicSharedMenu;
import org.bukkit.entity.HumanEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryAction;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.event.inventory.InventoryDragEvent;
import org.jetbrains.annotations.NotNull;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public final class MenuListener implements Listener {

    private final static List<InventoryAction> ALLOWED_ACTIONS = Arrays.asList(
            InventoryAction.PLACE_ALL,
            InventoryAction.PLACE_ONE,
            InventoryAction.PLACE_SOME,
            InventoryAction.PICKUP_ALL,
            InventoryAction.PICKUP_HALF,
            InventoryAction.PICKUP_ONE,
            InventoryAction.PICKUP_SOME,
            InventoryAction.NOTHING
    );

    private final Map<String, Integer> lastClickedItemLocation = new HashMap<>();

    private final MenuManager menuManager;


    public MenuListener(@NotNull MenuManager menuManager) {
        this.menuManager = menuManager;
    }


    @EventHandler(priority = EventPriority.LOW)
    public void onMenuClick(@NotNull InventoryClickEvent event) {
        if (!(event.getWhoClicked() instanceof Player)) {
            return;
        }

        if (!ALLOWED_ACTIONS.contains(event.getAction())) {
            event.setCancelled(true);
            return;
        }

        lastClickedItemLocation.put(
                event.getWhoClicked().getUniqueId().toString(),
                event.getSlot()
        );

        MenuHandler handler = menuManager.findHandlerByPlayer(event.getWhoClicked());
        if (handler == null) return;

        if (handler.shouldCloseClickingOutside() && event.getRawSlot() == -999) {
            event.getWhoClicked().closeInventory();
            event.setCancelled(true);
            return;
        }

        handler.handleClick(event);
    }


    @EventHandler(priority = EventPriority.LOW)
    public void onMenuDrag(@NotNull InventoryDragEvent event) {
        HumanEntity entity = event.getWhoClicked();

        if (!(entity instanceof Player)) {
            return;
        }

        String playerId = entity.getUniqueId().toString();
        Integer slot = lastClickedItemLocation.get(playerId);

        if (slot == null || slot == -1) {
            lastClickedItemLocation.put(playerId, null);
            return;
        }

        MenuHandler handler = menuManager.findHandlerByPlayer(event.getWhoClicked());
        if (handler == null) return;

        handler.handleDrag(new ExtendedInventoryDragEvent(event, slot));
    }


    @EventHandler(priority = EventPriority.LOW)
    public void onMenuLeave(@NotNull InventoryCloseEvent event) {
        HumanEntity human = event.getPlayer();
        if (!(human instanceof Player)) {
            return;
        }

        MenuHandler handler = menuManager.findHandlerByPlayer(human);
        if (handler == null) return;

        if (!(handler instanceof AbstractDynamicSharedMenu)) {
            return;
        }

        ((AbstractDynamicSharedMenu<?>) handler).leave((Player) human);
    }


    @EventHandler(priority = EventPriority.LOW)
    public void onMenuClose(@NotNull InventoryCloseEvent event) {
        HumanEntity human = event.getPlayer();
        if (!(human instanceof Player)) {
            return;
        }

        MenuHandler handler = menuManager.findHandlerByPlayer(human);
        if (handler == null) return;

        // It is not safe to navigate back to shared menus.
        if (handler instanceof AbstractDynamicSharedMenu<?>) {
            menuManager.dissociatePlayerFromHandler(human);
            return;
        }

        handler.handleClose(event);
        menuManager.dissociatePlayerFromHandler(human);
    }

}

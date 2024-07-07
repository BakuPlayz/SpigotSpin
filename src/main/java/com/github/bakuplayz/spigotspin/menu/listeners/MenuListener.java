package com.github.bakuplayz.spigotspin.menu.listeners;

import com.github.bakuplayz.spigotspin.SpigotSpin;
import com.github.bakuplayz.spigotspin.menu.MenuManager;
import com.github.bakuplayz.spigotspin.menu.abstracts.AbstractSharedMenu;
import com.github.bakuplayz.spigotspin.menu.listeners.events.ExtendedInventoryDragEvent;
import org.bukkit.entity.HumanEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.*;
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

    private final Map<String, Long> lastClosingTime;

    private final Map<String, Integer> lastClickedItemLocation;

    private final MenuManager menuManager;


    public MenuListener(@NotNull MenuManager menuManager) {
        this.lastClickedItemLocation = new HashMap<>();
        this.lastClosingTime = new HashMap<>();
        this.menuManager = menuManager;
    }


    @EventHandler(priority = EventPriority.LOW)
    public void onMenuClick(@NotNull InventoryClickEvent event) {
        if (!(event.getWhoClicked() instanceof Player)) {
            return;
        }

        MenuHandler handler = menuManager.findHandlerByPlayer(event.getWhoClicked());
        if (handler == null) return;

        if (!ALLOWED_ACTIONS.contains(event.getAction())) {
            event.setCancelled(true);
            return;
        }

        lastClickedItemLocation.put(
                event.getWhoClicked().getUniqueId().toString(),
                event.getSlot()
        );

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

        MenuHandler handler = menuManager.findHandlerByPlayer(event.getWhoClicked());
        if (handler == null) return;

        String playerId = entity.getUniqueId().toString();
        Integer slot = lastClickedItemLocation.get(playerId);

        if (slot == null || slot == -1) {
            lastClickedItemLocation.put(playerId, null);
            return;
        }

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

        if (!(handler instanceof AbstractSharedMenu)) {
            return;
        }

        ((AbstractSharedMenu<?, ?>) handler).leave((Player) human);
    }


    @EventHandler(priority = EventPriority.LOW)
    public void onMenuOpen(@NotNull InventoryOpenEvent event) {
        HumanEntity human = event.getPlayer();
        if (!(human instanceof Player)) {
            return;
        }

        MenuHandler handler = menuManager.findHandlerByPlayer(human);
        if (handler == null) return;

        long timeSinceClose = System.currentTimeMillis() - lastClosingTime.getOrDefault(human.getUniqueId().toString(), 0L);
        if (timeSinceClose >= handler.getBackStackClearingTime()) {
            SpigotSpin.Manager.REF.getHistory().clearBackStack(human);
            lastClosingTime.remove(human.getUniqueId().toString());
        }

        handler.handleOpen(event);
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
        if (handler instanceof AbstractSharedMenu<?, ?>) {
            menuManager.dissociatePlayerFromHandler(human);
            return;
        }

        handler.handleClose(event);
        menuManager.dissociatePlayerFromHandler(human);
        lastClosingTime.put(human.getUniqueId().toString(), System.currentTimeMillis());
    }

}

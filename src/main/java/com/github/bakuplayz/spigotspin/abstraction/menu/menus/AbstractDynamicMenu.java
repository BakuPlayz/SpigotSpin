package com.github.bakuplayz.spigotspin.abstraction.menu.menus;

import com.github.bakuplayz.spigotspin.abstraction.menu.dispatchers.DynamicMenuDispatcher;
import com.github.bakuplayz.spigotspin.abstraction.menu.items.Clickable;
import com.github.bakuplayz.spigotspin.abstraction.menu.items.Draggable;
import com.github.bakuplayz.spigotspin.abstraction.menu.items.Item;
import com.github.bakuplayz.spigotspin.abstraction.menu.listeners.events.ExtendedInventoryDragEvent;
import com.github.bakuplayz.spigotspin.abstraction.menu.menus.handlers.OpenInventoryHandler;
import lombok.AccessLevel;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.jetbrains.annotations.NotNull;

import java.util.HashMap;
import java.util.Map;
import java.util.stream.IntStream;

@EqualsAndHashCode
public abstract class AbstractDynamicMenu implements DynamicMenu {

    @NotNull
    protected final String title;

    @NotNull
    protected final DragState dragState;

    @NotNull
    @Getter
    @Setter(AccessLevel.MODULE)
    protected Map<Integer, Item> items;

    @NotNull
    @Getter
    @Setter(AccessLevel.MODULE)
    protected DynamicMenuDispatcher dispatcher;

    @Getter
    @Setter(AccessLevel.MODULE)
    protected Inventory inventory;


    protected AbstractDynamicMenu(@NotNull String title) {
        this.title = title;
        this.dragState = DragState.DISABLED;
        this.items = new HashMap<>(DYNAMIC_MENU_MAX_SIZE);
        this.dispatcher = new DynamicMenuDispatcher(() -> inventory);
    }


    @Override
    public final void handleClick(@NotNull InventoryClickEvent event) {
        Item item = items.get(event.getSlot());

        switch (dragState) {
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


    @Override
    public final void handleDrag(@NotNull ExtendedInventoryDragEvent event) {
        if (dragState == DragState.DISABLED) {
            event.setCancelled(true);
            return;
        }

        Item item = items.get(event.getOldSlot());

        if (!(item instanceof Draggable)) {
            return;
        }

        ((Draggable) item).performAction((Player) event.getWhoClicked());
    }


    public final void open(@NotNull Player player, @NotNull OpenInventoryHandler handler) {
        handler.beforeInventoryLoaded();
        handler.loadInventory();
        handler.afterInventoryLoaded();
        player.openInventory(inventory);
        handler.afterInventoryOpened();
    }


    @Override
    public void open(@NotNull Player player) {
        open(player, new OpenMenuHandler());
    }


    @Override
    public void close(@NotNull Player player) {
        player.closeInventory();
    }


    @Override
    public final int getSize() {
        int lastIndex = IntStream.range(0, DYNAMIC_MENU_MAX_SIZE)
                .filter(position -> items.get(position) != null)
                .reduce((first, second) -> second)
                .orElse(0);

        return (int) Math.ceil(lastIndex / 9.0d) * 9;
    }


    private boolean isMenuDrag(@NotNull InventoryClickEvent event) {
        return event.getClickedInventory() == getInventory();
    }


    private boolean isInventoryDrag(@NotNull InventoryClickEvent event) {
        return event.getWhoClicked().getInventory() == event.getClickedInventory();
    }


    private class OpenMenuHandler implements OpenInventoryHandler {

        @Override
        public void beforeInventoryLoaded() {
            setItems();
        }


        @Override
        public void loadInventory() {
            setInventory(Bukkit.createInventory(AbstractDynamicMenu.this, getSize(), title));
        }


        @Override
        public void afterInventoryLoaded() {
        }


        @Override
        public void afterInventoryOpened() {
            items.values().forEach(dispatcher::updateItem);
        }

    }

}

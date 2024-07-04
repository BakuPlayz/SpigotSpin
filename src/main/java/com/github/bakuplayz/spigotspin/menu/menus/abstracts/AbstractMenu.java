package com.github.bakuplayz.spigotspin.menu.menus.abstracts;

import com.github.bakuplayz.spigotspin.SpigotSpin;
import com.github.bakuplayz.spigotspin.menu.dispatchers.InventoryDispatcher;
import com.github.bakuplayz.spigotspin.menu.items.ItemsMap;
import com.github.bakuplayz.spigotspin.menu.listeners.events.ExtendedInventoryDragEvent;
import com.github.bakuplayz.spigotspin.menu.menus.Menu;
import com.github.bakuplayz.spigotspin.menu.menus.common.ViewerMap;
import com.github.bakuplayz.spigotspin.menu.menus.common.components.InteractComponent;
import com.github.bakuplayz.spigotspin.menu.menus.common.handlers.OpenInventoryHandler;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.inventory.Inventory;
import org.jetbrains.annotations.NotNull;


public abstract class AbstractMenu implements Menu {

    @Getter
    protected final ViewerMap viewers;

    private final InteractComponent interactComponent;

    @Getter
    @Setter(AccessLevel.MODULE)
    protected ItemsMap items;

    @Getter
    @Setter(AccessLevel.MODULE)
    private Inventory inventory;

    @Getter
    @Setter(AccessLevel.MODULE)
    private InventoryDispatcher dispatcher;


    protected AbstractMenu() {
        this.viewers = new ViewerMap();
        this.items = new ItemsMap(getMaxSize());
        this.dispatcher = new InventoryDispatcher(() -> inventory);
        this.interactComponent = new InteractComponent(this);
    }


    @NotNull
    protected abstract Inventory createInventory();


    @Override
    public final void handleClick(@NotNull InventoryClickEvent event) {
        interactComponent.handleClick(event);
    }


    @Override
    public final void handleDrag(@NotNull ExtendedInventoryDragEvent event) {
        interactComponent.handleDrag(event);
    }


    @Override
    public final void handleClose(@NotNull InventoryCloseEvent event) {
        interactComponent.handleClose(event);
    }


    @Override
    public final void open(@NotNull Player player, @NotNull OpenInventoryHandler handler) {
        handler.beforeInventoryLoaded();
        handler.loadInventory();
        handler.afterInventoryLoaded();
        player.openInventory(inventory);
        handler.afterInventoryOpened();

        SpigotSpin.Manager.REF.getHistory().addToBackStack(player, this);
        SpigotSpin.Manager.REF.getMenuManager().associatePlayerWithHandler(player, this);
    }


    @Override
    public void open(@NotNull Player player) {
        viewers.add(player);
        open(player, new OpenMenuHandler());
    }


    @Override
    public void close(@NotNull Player player) {
        player.closeInventory();
        viewers.remove(player);
    }


    private class OpenMenuHandler implements OpenInventoryHandler {

        @Override
        public void beforeInventoryLoaded() {
            setItems();
        }


        @Override
        public void loadInventory() {
            setInventory(createInventory());
        }


        @Override
        public void afterInventoryLoaded() {
            items.values().forEach(dispatcher::updateItem);
        }


        @Override
        public void afterInventoryOpened() {
            // Do nothing yet...
        }

    }

}

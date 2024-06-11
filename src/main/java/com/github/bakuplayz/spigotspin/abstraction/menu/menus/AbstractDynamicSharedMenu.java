package com.github.bakuplayz.spigotspin.abstraction.menu.menus;

import com.github.bakuplayz.spigotspin.abstraction.menu.menus.handlers.OpenInventoryHandler;
import com.github.bakuplayz.spigotspin.abstraction.menu.menus.shared.DynamicSharedMenu;
import com.github.bakuplayz.spigotspin.abstraction.menu.menus.shared.SharedInternal;
import com.github.bakuplayz.spigotspin.abstraction.menu.menus.state.DynamicStateMenu;
import com.github.bakuplayz.spigotspin.abstraction.menu.menus.state.MenuState;
import com.github.bakuplayz.spigotspin.abstraction.menu.menus.state.MenuStateObserver;
import lombok.Getter;
import lombok.Setter;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

@Getter
@Setter
public abstract class AbstractDynamicSharedMenu<S extends MenuState>
        extends AbstractDynamicStateMenu<S> implements DynamicSharedMenu, DynamicStateMenu<S>, MenuStateObserver<S> {


    protected AbstractDynamicSharedMenu(@NotNull String title) {
        super(title);
    }


    @Override
    public final void join(@NotNull Player player, @NotNull String identifier) {
        SharedInternal.STATE.ACTIVE_MENUS.getOrDefault(identifier, this).open(player, new OpenMenuHandler(identifier));
        SharedInternal.STATE.ACTIVE_MENUS.putIfAbsent(identifier, this);
        SharedInternal.STATE.PLAYER_OPENED_MENUS.put(player.getUniqueId().toString(), identifier);
    }


    /**
     * This method is not meant to be called directly, by an extending class.
     * But instead is called by {@link #join(Player, String)} instead.
     */
    @Override
    public final void open(@NotNull Player player) {
        throw new UnsupportedOperationException("This method is not allowed to be called in a shared menu, see comment of this function.");
    }


    @Override
    public final void leave(@NotNull Player player) {
        String identifier = SharedInternal.STATE.PLAYER_OPENED_MENUS.remove(player.getUniqueId().toString());

        if (!SharedInternal.STATE.PLAYER_OPENED_MENUS.containsValue(identifier)) {
            SharedInternal.STATE.ACTIVE_MENUS.remove(identifier);
        }
    }


    private final class OpenMenuHandler implements OpenInventoryHandler {

        private final String identifier;


        public OpenMenuHandler(@NotNull String identifier) {
            this.identifier = identifier;
        }


        @Override
        public void beforeInventoryLoaded() {
            if (SharedInternal.STATE.ACTIVE_MENUS.get(identifier) != null) return;
            setItems();
        }


        @Override
        public void loadInventory() {
            if (SharedInternal.STATE.ACTIVE_MENUS.get(identifier) != null) return;
            setInventory(Bukkit.createInventory(AbstractDynamicSharedMenu.this, getSize(), title));
        }


        @Override
        public void afterInventoryLoaded() {
            if (SharedInternal.STATE.ACTIVE_MENUS.get(identifier) == null) return;
            setItems(SharedInternal.STATE.ACTIVE_MENUS.get(identifier).getItems());
            setInventory(SharedInternal.STATE.ACTIVE_MENUS.get(identifier).getInventory());
            setDispatcher(SharedInternal.STATE.ACTIVE_MENUS.get(identifier).getDispatcher());
        }


        @Override
        public void afterInventoryOpened() {
            items.values().forEach(getDispatcher()::updateItem);
        }

    }

}

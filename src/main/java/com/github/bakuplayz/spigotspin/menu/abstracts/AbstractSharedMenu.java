package com.github.bakuplayz.spigotspin.menu.abstracts;

import com.github.bakuplayz.spigotspin.menu.common.handlers.OpenInventoryHandler;
import com.github.bakuplayz.spigotspin.menu.common.shared.SharedInternal;
import com.github.bakuplayz.spigotspin.menu.common.shared.SharedMenu;
import com.github.bakuplayz.spigotspin.menu.common.state.MenuState;
import com.github.bakuplayz.spigotspin.menu.common.state.MenuStateHandler;
import com.github.bakuplayz.spigotspin.menu.common.state.MenuStateObserver;
import com.github.bakuplayz.spigotspin.menu.items.Item;
import lombok.Getter;
import lombok.Setter;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

@Getter
@Setter
public abstract class AbstractSharedMenu<S extends MenuState, SH extends MenuStateHandler<S, ? extends MenuStateObserver<S>>>
        extends AbstractStateMenu<S, SH> implements SharedMenu {


    protected AbstractSharedMenu(@NotNull String title) {
        super(title);
    }


    @Override
    public final void join(@NotNull Player player, @NotNull String identifier) {
        viewers.add(player);
        setStateHandler(createStateHandler());
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
            setFrameItems();
            setItems();
        }


        @Override
        public void loadInventory() {
            if (SharedInternal.STATE.ACTIVE_MENUS.get(identifier) != null) return;
            setInventory(createInventory());
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
            items.values().forEach(Item::create);
            items.values().forEach(getDispatcher()::updateItem);
        }

    }

}

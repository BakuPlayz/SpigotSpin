package com.github.bakuplayz.spigotspin.menu;

import com.github.bakuplayz.spigotspin.SpigotSpin;
import com.github.bakuplayz.spigotspin.menu.listeners.MenuHandler;
import org.bukkit.entity.HumanEntity;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.HashMap;

/**
 * Inspired by <a href="https://github.com/Flo0/GUITutorial/blob/master/src/main/java/com/gestankbratwurst/guitutorial/gui/GUIManager.java#L29">7smile7</a>.
 */
public final class MenuManager {

    private final HashMap<String, MenuHandler> menus = new HashMap<>();


    @Nullable
    public MenuHandler findHandlerByPlayer(@NotNull HumanEntity player) {
        return menus.getOrDefault(player.getUniqueId().toString(), null);
    }


    public void associatePlayerWithHandler(@NotNull HumanEntity player, MenuHandler handler) {
        menus.put(player.getUniqueId().toString(), handler);

        long timeSinceClose = System.currentTimeMillis() - SpigotSpin.Manager.REF.getHistory().getLastClosingTime(player);
        if (timeSinceClose >= handler.getBackStackClearingTime()) {
            SpigotSpin.Manager.REF.getHistory().clearBackStack(player);
            SpigotSpin.Manager.REF.getHistory().clearLastClosingTime(player);
        }
    }


    public void dissociatePlayerFromHandler(@NotNull HumanEntity player) {
        menus.remove(player.getUniqueId().toString());
        SpigotSpin.Manager.REF.getHistory().setLastClosingTime(player);
    }

}

package com.github.bakuplayz.spigotspin.abstraction.menu.dispatchers;

import com.github.bakuplayz.spigotspin.abstraction.menu.menus.DynamicMenu;
import org.bukkit.entity.HumanEntity;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

import java.util.Stack;
import java.util.concurrent.ConcurrentHashMap;


public final class HistoryDispatcher {

    private static final ConcurrentHashMap<String, Stack<DynamicMenu>> backStack = new ConcurrentHashMap<>();

    private HistoryDispatcher() {}


    /**
     * Attempts to pop the backstack to get the last menu that was open
     * previous to this one, if it doesn't succeed then nothing happens.
     *
     * @param player The player that should open the previous menu.
     */
    public static void popBackStack(@NotNull HumanEntity player) {
        String uuid = player.getUniqueId().toString();

        if (!backStack.containsKey(uuid) || backStack.get(uuid).empty()) {
            return;
        }

        backStack.get(uuid).pop().open((Player) player);
    }


    /**
     * Pushes an new menu entry onto the player's backstack of menus previously
     * opened, also makes sure to create an new stack iff non-preexists.
     *
     * @param player The player that should be associated with the entry.
     * @param entry  The menu entry to add to the player's backstack.
     */
    public static void addToBackStack(@NotNull HumanEntity player, @NotNull DynamicMenu entry) {
        String uuid = player.getUniqueId().toString();
        backStack.putIfAbsent(uuid, new Stack<>());
        backStack.get(uuid).push(entry);
    }


    /**
     * Completely wipes the entire backstack of menus for the
     * provided player.
     *
     * @param player The player to wipe the backstack of.
     */
    public static void clearBackStack(@NotNull HumanEntity player) {
        backStack.put(player.getUniqueId().toString(), new Stack<>());
    }

}

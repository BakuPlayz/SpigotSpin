package com.github.bakuplayz.spigotspin.menu.dispatchers;

import com.github.bakuplayz.spigotspin.menu.Menu;
import lombok.Getter;
import org.bukkit.entity.HumanEntity;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

import java.util.HashMap;
import java.util.Map;
import java.util.Stack;


public final class HistoryDispatcher {

    @Getter
    private final Map<String, Stack<Menu>> backStack = new HashMap<>();


    private final Map<String, Long> lastClosingTime = new HashMap<>();


    /**
     * Attempts to pop the backstack to get the last menu that was open
     * previous to this one, if it doesn't succeed then nothing happens.
     *
     * @param player The player that should open the previous menu.
     */
    public void popBackStack(@NotNull HumanEntity player) {
        String uuid = player.getUniqueId().toString();

        if (!backStack.containsKey(uuid) || backStack.get(uuid).empty()) {
            return;
        }

        // Pops inclusive :)
        backStack.get(uuid).pop();
        backStack.get(uuid).peek().onPoppedTo();
        backStack.get(uuid).pop().open((Player) player);
    }


    /**
     * Pushes a new menu entry onto the player's backstack of menus previously
     * opened, also makes sure to create a new stack iff non-preexists.
     *
     * @param player The player that should be associated with the entry.
     * @param entry  The menu entry to add to the player's backstack.
     */
    public void addToBackStack(@NotNull HumanEntity player, @NotNull Menu entry) {
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
    public void clearBackStack(@NotNull HumanEntity player) {
        backStack.put(player.getUniqueId().toString(), new Stack<>());
    }


    public void setLastClosingTime(@NotNull HumanEntity player) {
        lastClosingTime.put(player.getUniqueId().toString(), System.currentTimeMillis());
    }


    public void clearLastClosingTime(@NotNull HumanEntity player) {
        lastClosingTime.remove(player.getUniqueId().toString());
    }


    public long getLastClosingTime(@NotNull HumanEntity player) {
        return lastClosingTime.getOrDefault(player.getUniqueId().toString(), 0L);
    }

}

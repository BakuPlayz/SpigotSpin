package com.github.bakuplayz.spigotspin.abstraction.menu.items.actions;

import com.github.bakuplayz.spigotspin.abstraction.menu.items.Clickable;
import com.github.bakuplayz.spigotspin.abstraction.menu.items.Item;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

@FunctionalInterface
public interface ClickableAction<C extends Item & Clickable> extends ItemAction {

    @Override
    default void performAction(@NotNull Item item, @NotNull Player player) {
        onClick((C) item, player);
    }


    void onClick(@NotNull C item, @NotNull Player player);

}
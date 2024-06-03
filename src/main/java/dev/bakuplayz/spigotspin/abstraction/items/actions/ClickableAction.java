package dev.bakuplayz.spigotspin.abstraction.items.actions;

import dev.bakuplayz.spigotspin.abstraction.items.Clickable;
import dev.bakuplayz.spigotspin.abstraction.items.Item;
import org.jetbrains.annotations.NotNull;

@FunctionalInterface
public interface ClickableAction<C extends Item & Clickable> {

    void onClick(@NotNull C item);

}
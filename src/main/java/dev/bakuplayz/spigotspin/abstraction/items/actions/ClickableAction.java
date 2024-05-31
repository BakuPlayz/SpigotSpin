package dev.bakuplayz.spigotspin.abstraction.items.actions;

import dev.bakuplayz.spigotspin.abstraction.items.Clickable;
import org.jetbrains.annotations.NotNull;

@FunctionalInterface
public interface ClickableAction {

    void onClick(@NotNull Clickable item);

}
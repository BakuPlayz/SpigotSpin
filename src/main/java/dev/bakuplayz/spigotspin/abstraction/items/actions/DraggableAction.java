package dev.bakuplayz.spigotspin.abstraction.items.actions;

import dev.bakuplayz.spigotspin.abstraction.items.DraggableItem;
import org.jetbrains.annotations.NotNull;

@FunctionalInterface
public interface DraggableAction {

    void onDrag(@NotNull DraggableItem item);

}

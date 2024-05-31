package dev.bakuplayz.spigotspin.abstraction.items;

import dev.bakuplayz.spigotspin.abstraction.items.actions.DraggableAction;
import lombok.Setter;
import org.jetbrains.annotations.NotNull;

@Setter
public abstract class DraggableItem extends Item implements Draggable {

    @NotNull
    private DraggableAction action;


    @Override
    public final void performAction() {
        action.onDrag(this);
    }

}

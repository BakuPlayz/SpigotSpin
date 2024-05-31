package dev.bakuplayz.spigotspin.abstraction.listeners.events;

import lombok.Getter;
import org.bukkit.event.inventory.DragType;
import org.bukkit.event.inventory.InventoryDragEvent;
import org.jetbrains.annotations.NotNull;

@Getter
public class ExtendedInventoryDragEvent extends InventoryDragEvent {

    private final int oldSlot;


    public ExtendedInventoryDragEvent(@NotNull InventoryDragEvent event, int position) {
        super(event.getView(), event.getCursor(), event.getOldCursor(), event.getType() == DragType.SINGLE, event.getNewItems());
        this.oldSlot = position;
    }

}

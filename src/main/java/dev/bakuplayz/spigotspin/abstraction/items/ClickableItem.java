package dev.bakuplayz.spigotspin.abstraction.items;

import dev.bakuplayz.spigotspin.abstraction.items.actions.ClickableAction;
import lombok.Setter;
import org.jetbrains.annotations.Nullable;

@Setter
public abstract class ClickableItem extends Item implements Clickable {

    @Nullable
    private ClickableAction action;


    @Override
    public void performAction() {
        if (action == null) {
            throw new RuntimeException("Action cannot be null.");
        }

        action.onClick(this);
    }

}


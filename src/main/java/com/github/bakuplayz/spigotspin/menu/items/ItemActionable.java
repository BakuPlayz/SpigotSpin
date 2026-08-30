package com.github.bakuplayz.spigotspin.menu.items;

import com.github.bakuplayz.spigotspin.menu.items.actions.ItemAction;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public interface ItemActionable {

    @Nullable
    ItemAction getAction();


    void setAction(@NotNull ItemAction action);

}

package com.github.bakuplayz.spigotspin.menu.items;

import com.github.bakuplayz.spigotspin.menu.items.actions.ItemAction;
import org.jetbrains.annotations.NotNull;

public interface ItemActionable {

    void setAction(@NotNull ItemAction action);

}

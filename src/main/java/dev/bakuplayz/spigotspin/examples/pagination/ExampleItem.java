package dev.bakuplayz.spigotspin.examples.pagination;

import com.cryptomorin.xseries.XMaterial;
import dev.bakuplayz.spigotspin.abstraction.menu.items.state.StateItem;
import org.jetbrains.annotations.NotNull;

public final class ExampleItem extends StateItem<ExampleState> {

    public ExampleItem(@NotNull String name) {
        setName(String.format("&e%s", name));
        setMaterial(XMaterial.ANVIL);
    }


    @Override
    public void update(@NotNull ExampleState state, int flag) {
        setLore(String.format("Count: %d", state.getCount()));
    }

}

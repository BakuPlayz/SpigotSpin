package dev.bakuplayz.spigotspin.examples.state;

import com.cryptomorin.xseries.XMaterial;
import dev.bakuplayz.spigotspin.abstraction.menu.items.state.ClickableStateItem;
import org.jetbrains.annotations.NotNull;

public final class ExampleItem extends ClickableStateItem<ExampleState> {

    public ExampleItem(@NotNull String name) {
        setName(String.format("&e%s", name));
        setMaterial(XMaterial.ANVIL);
    }

    @Override
    public void update(@NotNull ExampleState state, int flag) {
        setLore(String.format("Count: %d", state.getCount()));
    }
}

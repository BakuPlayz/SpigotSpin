package com.github.bakuplayz.spigotspin.examples.shared;

import com.cryptomorin.xseries.XMaterial;
import com.github.bakuplayz.spigotspin.menu.items.state.ClickableStateItem;
import lombok.AllArgsConstructor;
import org.jetbrains.annotations.NotNull;

@AllArgsConstructor
public final class ExampleItem extends ClickableStateItem<ExampleState> {


    private final String name;


    @Override
    public void create() {
        setName(String.format("&e%s", name));
        setMaterial(XMaterial.ANVIL);
    }


    @Override
    public void update(@NotNull ExampleState state, int flag) {
        setLore(String.format("Count: %d", state.getCount()));
    }


}

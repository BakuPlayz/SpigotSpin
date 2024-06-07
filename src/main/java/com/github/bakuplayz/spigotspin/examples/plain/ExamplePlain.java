package com.github.bakuplayz.spigotspin.examples.plain;

import com.github.bakuplayz.spigotspin.abstraction.menu.menus.AbstractDynamicPlainMenu;

import java.util.logging.Logger;

public final class ExamplePlain extends AbstractDynamicPlainMenu {

    public static final Logger LOGGER = Logger.getLogger("ExamplePlain");


    public ExamplePlain() {
        super("Plain example");
    }


    @Override
    public void setItems() {
        setItem(9, new ExampleItem(), (item, player) -> LOGGER.info(String.valueOf(item.getPosition())));
    }

}

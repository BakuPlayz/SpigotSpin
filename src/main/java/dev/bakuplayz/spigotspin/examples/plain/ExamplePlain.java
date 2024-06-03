package dev.bakuplayz.spigotspin.examples.plain;

import dev.bakuplayz.spigotspin.abstraction.menus.AbstractDynamicPlainMenu;

import java.util.logging.Logger;

public final class ExamplePlain extends AbstractDynamicPlainMenu {

    public static final Logger LOGGER = Logger.getLogger("ExamplePlain");


    public ExamplePlain() {
        super("Plain example");
    }


    @Override
    public void setItems() {
        setItem(9, new ExampleItem(), (item) -> LOGGER.info(String.valueOf(item.getPosition())));
    }

}

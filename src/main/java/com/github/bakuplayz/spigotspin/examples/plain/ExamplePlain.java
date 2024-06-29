package com.github.bakuplayz.spigotspin.examples.plain;

import com.github.bakuplayz.spigotspin.menu.menus.SizeType;
import com.github.bakuplayz.spigotspin.menu.menus.abstracts.AbstractPlainMenu;

import java.util.logging.Logger;

public final class ExamplePlain extends AbstractPlainMenu {

    public static final Logger LOGGER = Logger.getLogger("ExamplePlain");


    public ExamplePlain() {
        super("Plain example");
    }


    @Override
    public void setItems() {
        setItem(9, new ExampleItem(), (item, player) -> LOGGER.info(String.valueOf(item.getPosition())));
    }


    @Override
    public SizeType getSizeType() {
        return SizeType.DYNAMIC;
    }

}

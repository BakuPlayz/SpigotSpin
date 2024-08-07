package examples.com.github.bakuplayz.spigotspin.plain;

import com.github.bakuplayz.spigotspin.menu.abstracts.AbstractPlainMenu;
import com.github.bakuplayz.spigotspin.menu.common.SizeType;

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

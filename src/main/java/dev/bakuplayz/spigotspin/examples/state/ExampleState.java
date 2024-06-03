package dev.bakuplayz.spigotspin.examples.state;

import dev.bakuplayz.spigotspin.abstraction.menus.state.MenuState;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public final class ExampleState implements MenuState {

    private int count;

}

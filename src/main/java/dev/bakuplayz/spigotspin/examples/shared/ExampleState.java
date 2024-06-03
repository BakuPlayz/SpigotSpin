package dev.bakuplayz.spigotspin.examples.shared;

import dev.bakuplayz.spigotspin.abstraction.menus.state.MenuState;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public final class ExampleState implements MenuState {

    private int count;

}

package com.github.bakuplayz.spigotspin.examples.state;

import com.github.bakuplayz.spigotspin.abstraction.menu.menus.state.MenuState;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public final class ExampleState implements MenuState {

    private int count;

}

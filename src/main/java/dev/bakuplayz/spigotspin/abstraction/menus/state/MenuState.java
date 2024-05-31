package dev.bakuplayz.spigotspin.abstraction.menus.state;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

public interface MenuState {

    default int defaultInt() {
        return 0;
    }


    default long defaultLong() {
        return 0L;
    }


    default double defaultDouble() {
        return 0d;
    }


    default float defaultFloat() {
        return 0f;
    }


    @NotNull
    default <E> Set<E> defaultSet() {
        return new HashSet<>();
    }


    @NotNull
    default <E> ArrayList<E> defaultList() {
        return new ArrayList<>();
    }

}

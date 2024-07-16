package com.github.bakuplayz.spigotspin.menu.common;

import org.jetbrains.annotations.NotNull;

public final class Preconditions {

    public static void validateArgumentBounds(int argument, int lower, int higher, @NotNull String message) throws IllegalArgumentException {
        if (!(argument < lower || argument >= higher)) {
            return;
        }

        throw new IllegalArgumentException(String.format(message, argument));
    }

}

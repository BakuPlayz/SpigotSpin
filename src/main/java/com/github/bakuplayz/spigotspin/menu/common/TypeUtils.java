package com.github.bakuplayz.spigotspin.menu.common;

import org.jetbrains.annotations.NotNull;

public final class TypeUtils {

    @SuppressWarnings("unchecked")
    public static <T> T infer(@NotNull Object value) {
        return (T) value;
    }

}

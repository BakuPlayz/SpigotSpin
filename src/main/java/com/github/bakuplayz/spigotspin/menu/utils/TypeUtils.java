package com.github.bakuplayz.spigotspin.menu.utils;

import org.jetbrains.annotations.NotNull;

public final class TypeUtils {

    @SuppressWarnings("unchecked")
    public static <T> T infer(@NotNull Object value) {
        return (T) value;
    }

}

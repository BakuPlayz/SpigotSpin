package dev.bakuplayz.spigotspin.abstraction.utils;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.jetbrains.annotations.NotNull;

import java.util.function.Function;


/**
 * A collections utility class for creating indexed
 * list mappings, using e.g. Stream#map
 *
 * @param <T> The type to create an indexed collection from.
 * @apiNote Heavily inspired by the answer from <a href="https://stackoverflow.com/a/45976269">alex.b</a>.
 */
@Getter
@AllArgsConstructor
public final class Collection<T> {

    private T value;

    private int index;


    public static <T> @NotNull Function<T, Collection<T>> toIndexed() {
        return new Function<T, Collection<T>>() {

            int currentIndex = 0;


            @Override
            public Collection<T> apply(@NotNull T item) {
                return new Collection<>(item, currentIndex++);
            }

        };
    }

}



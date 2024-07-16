package com.github.bakuplayz.spigotspin.menu.common.state;

import com.github.bakuplayz.spigotspin.menu.common.TypeUtils;
import lombok.Getter;
import org.jetbrains.annotations.NotNull;

import java.util.Arrays;
import java.util.List;
import java.util.Set;
import java.util.function.UnaryOperator;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public abstract class MenuStateHandler<S extends MenuState, O extends MenuStateObserver<S>> {

    protected final O observer;

    @Getter
    protected S state;


    public MenuStateHandler(@NotNull O observer, @NotNull S initialState) {
        this.observer = observer;
        this.state = initialState;
    }


    @NotNull
    @SafeVarargs
    protected static <E> List<E> mutate(@NotNull List<E> initial, E @NotNull ... mutates) {
        return Stream.concat(initial.stream(), Arrays.stream(mutates)).collect(Collectors.toList());
    }


    @NotNull
    @SafeVarargs
    protected static <E> Set<E> mutate(@NotNull Set<E> initial, E @NotNull ... mutates) {
        return Stream.concat(initial.stream(), Arrays.stream(mutates)).collect(Collectors.toSet());
    }


    protected static <T> T infer(@NotNull Object value) {
        return TypeUtils.infer(value);
    }


    protected <P> void updateState(@NotNull P partialState, @NotNull UnaryOperator<P> mutator, int flag) {
        observer.onStateChanged(onUpdateStateMiddleware(mutator.apply(partialState), flag), flag);
    }


    protected <P> S onUpdateStateMiddleware(@NotNull P partialState, int flag) {
        return onUpdateState(partialState, flag);
    }


    protected abstract <P> S onUpdateState(@NotNull P partialState, int flag);

}
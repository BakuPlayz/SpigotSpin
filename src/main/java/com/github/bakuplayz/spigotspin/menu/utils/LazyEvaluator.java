package com.github.bakuplayz.spigotspin.menu.utils;

@FunctionalInterface
public interface LazyEvaluator<L> {

    L get();

}

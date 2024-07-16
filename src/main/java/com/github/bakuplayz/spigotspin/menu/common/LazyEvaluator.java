package com.github.bakuplayz.spigotspin.menu.common;

@FunctionalInterface
public interface LazyEvaluator<L> {

    L get();

}

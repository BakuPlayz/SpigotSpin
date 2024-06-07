package com.github.bakuplayz.spigotspin.abstraction.menu.utils;

@FunctionalInterface
public interface LazyEvaluator<L> {

    L get();
    
}

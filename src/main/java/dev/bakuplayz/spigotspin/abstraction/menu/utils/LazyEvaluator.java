package dev.bakuplayz.spigotspin.abstraction.menu.utils;

@FunctionalInterface
public interface LazyEvaluator<L> {

    L get();
    
}

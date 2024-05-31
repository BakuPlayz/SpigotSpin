package dev.bakuplayz.spigotspin.abstraction.utils;

@FunctionalInterface
public interface LazyEvaluator<L> {

    L get();
    
}

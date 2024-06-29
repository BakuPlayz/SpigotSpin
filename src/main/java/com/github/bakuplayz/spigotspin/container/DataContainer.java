package com.github.bakuplayz.spigotspin.container;

/**
 * To be implemented, should be used as a store:
 * 1. Older versions shall store it inside the block
 * 2. Newer versions shall additionally be able to store it inside PDC
 * 3. Maybe also into databases in the future?
 *
 * @param <D>
 */
public interface DataContainer<D> {

    D getData();


    void setData(D data);

}

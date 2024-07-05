package com.github.bakuplayz.spigotspin.menu.common.handlers;

public interface OpenInventoryHandler {


    void beforeInventoryLoaded();


    void loadInventory();


    void afterInventoryLoaded();


    void afterInventoryOpened();

}

package com.github.bakuplayz.spigotspin.menu.menus.common.handlers;

public interface OpenInventoryHandler {


    void beforeInventoryLoaded();


    void loadInventory();


    void afterInventoryLoaded();


    void afterInventoryOpened();

}

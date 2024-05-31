package dev.bakuplayz.spigotspin.abstraction.menus.handlers;

public interface OpenInventoryHandler {


    void beforeInventoryLoaded();


    void loadInventory();


    void afterInventoryLoaded();


    void afterInventoryOpened();

}

package dev.bakuplayz.spigotspin.abstraction.menu.menus.handlers;

public interface OpenInventoryHandler {


    void beforeInventoryLoaded();


    void loadInventory();


    void afterInventoryLoaded();


    void afterInventoryOpened();

}

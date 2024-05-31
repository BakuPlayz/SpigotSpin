package dev.bakuplayz.spigotspin.abstraction.menus.shared;

import dev.bakuplayz.spigotspin.abstraction.menus.AbstractDynamicStateMenu;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

public enum SharedInternal {

    STATE;

    public final ConcurrentMap<String, AbstractDynamicStateMenu<?>> ACTIVE_MENUS = new ConcurrentHashMap<>();

    public final ConcurrentMap<String, String> PLAYER_OPENED_MENUS = new ConcurrentHashMap<>();

}

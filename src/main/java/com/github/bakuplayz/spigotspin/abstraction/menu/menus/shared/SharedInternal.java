package com.github.bakuplayz.spigotspin.abstraction.menu.menus.shared;

import com.github.bakuplayz.spigotspin.abstraction.menu.menus.AbstractDynamicStateMenu;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

/**
 * A lazily loaded-enum with singleton-like abilities for holding the
 * active menus and what menu a certain player has opened.
 */
public enum SharedInternal {

    STATE;

    public final ConcurrentMap<String, AbstractDynamicStateMenu<?>> ACTIVE_MENUS = new ConcurrentHashMap<>();

    public final ConcurrentMap<String, String> PLAYER_OPENED_MENUS = new ConcurrentHashMap<>();

}

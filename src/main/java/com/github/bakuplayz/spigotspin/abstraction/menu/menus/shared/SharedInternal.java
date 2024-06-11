package com.github.bakuplayz.spigotspin.abstraction.menu.menus.shared;

import com.github.bakuplayz.spigotspin.abstraction.menu.menus.AbstractDynamicStateMenu;

import java.util.HashMap;
import java.util.Map;

/**
 * A lazily loaded-enum with singleton-like abilities for holding the
 * active menus and what menu a certain player has opened.
 */
public enum SharedInternal {

    STATE;

    public final Map<String, AbstractDynamicStateMenu<?>> ACTIVE_MENUS = new HashMap<>();

    public final Map<String, String> PLAYER_OPENED_MENUS = new HashMap<>();

}

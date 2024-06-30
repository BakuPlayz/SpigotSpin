package com.github.bakuplayz.spigotspin.menu.menus.common.shared;

import com.github.bakuplayz.spigotspin.menu.menus.abstracts.AbstractStateMenu;

import java.util.HashMap;
import java.util.Map;

/**
 * A lazily loaded-enum with singleton-like abilities for holding the
 * active menus and what menu a certain player has opened.
 */
public enum SharedInternal {

    STATE;

    public final Map<String, AbstractStateMenu<?, ?>> ACTIVE_MENUS = new HashMap<>();

    public final Map<String, String> PLAYER_OPENED_MENUS = new HashMap<>();

}

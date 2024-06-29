package com.github.bakuplayz.spigotspin.menu.menus.common.paginated;

import com.github.bakuplayz.spigotspin.menu.menus.common.state.MenuState;
import lombok.Data;

@Data
public abstract class PaginatedMenuState implements MenuState {

    protected int page = defaultInt();

    protected int displayPage = defaultInt() + 1;

    protected int maxItems = defaultInt();

}

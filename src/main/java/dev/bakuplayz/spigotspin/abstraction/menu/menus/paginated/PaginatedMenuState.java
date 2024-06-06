package dev.bakuplayz.spigotspin.abstraction.menu.menus.paginated;

import dev.bakuplayz.spigotspin.abstraction.menu.menus.state.MenuState;
import lombok.Data;

@Data
public abstract class PaginatedMenuState implements MenuState {

    protected int page = defaultInt();

    protected int displayPage = defaultInt() + 1;

    protected int maxItems = defaultInt();

}

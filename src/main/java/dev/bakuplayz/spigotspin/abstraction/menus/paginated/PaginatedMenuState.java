package dev.bakuplayz.spigotspin.abstraction.menus.paginated;

import dev.bakuplayz.spigotspin.abstraction.menus.state.MenuState;
import lombok.Data;

@Data
public abstract class PaginatedMenuState implements MenuState {

    protected int page = defaultInt();

    protected int displayPage = defaultInt() + 1;

    protected int maxItems = defaultInt();

}

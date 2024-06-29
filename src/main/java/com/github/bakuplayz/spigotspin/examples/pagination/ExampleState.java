package com.github.bakuplayz.spigotspin.examples.pagination;

import com.github.bakuplayz.spigotspin.menu.menus.common.paginated.PaginatedMenuState;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public final class ExampleState extends PaginatedMenuState {

    private int count;

}

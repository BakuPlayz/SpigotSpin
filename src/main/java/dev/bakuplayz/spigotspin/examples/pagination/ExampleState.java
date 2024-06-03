package dev.bakuplayz.spigotspin.examples.pagination;

import dev.bakuplayz.spigotspin.abstraction.menus.paginated.PaginatedMenuState;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public final class ExampleState extends PaginatedMenuState {

    private int count;

}

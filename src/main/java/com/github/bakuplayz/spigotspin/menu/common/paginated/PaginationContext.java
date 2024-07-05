package com.github.bakuplayz.spigotspin.menu.common.paginated;

public interface PaginationContext {


    boolean hasNextPage();


    boolean isItemOutOfBounds(int itemIndex, int page);

}

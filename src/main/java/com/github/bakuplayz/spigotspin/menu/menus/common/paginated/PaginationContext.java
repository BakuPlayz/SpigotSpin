package com.github.bakuplayz.spigotspin.menu.menus.common.paginated;

public interface PaginationContext {


    boolean hasNextPage();


    boolean isFramePosition(int position);


    boolean isItemOutOfBounds(int itemIndex, int page);

}

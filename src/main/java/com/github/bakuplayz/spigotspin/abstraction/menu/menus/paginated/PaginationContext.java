package com.github.bakuplayz.spigotspin.abstraction.menu.menus.paginated;

public interface PaginationContext {


    boolean hasNextPage();


    boolean isFramePosition(int position);


    boolean isItemOutOfBounds(int itemIndex, int page);

}

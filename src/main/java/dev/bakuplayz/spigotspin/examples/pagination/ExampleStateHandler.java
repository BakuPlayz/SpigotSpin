package dev.bakuplayz.spigotspin.examples.pagination;

import dev.bakuplayz.spigotspin.abstraction.menu.menus.paginated.PaginatedMenuStateHandler;
import dev.bakuplayz.spigotspin.abstraction.menu.menus.paginated.PaginatedMenuStateObserver;
import org.jetbrains.annotations.NotNull;

public final class ExampleStateHandler extends PaginatedMenuStateHandler<ExampleState> {

    public ExampleStateHandler(@NotNull PaginatedMenuStateObserver<ExampleState> observer) {
        super(observer, new ExampleState());
    }


    @Override
    protected <P> ExampleState onUpdateState(@NotNull P partialState, int flag) {
        if (flag == ExampleStateFlag.COUNT) {
            state.setCount(infer(partialState));
        }

        return state;
    }

}

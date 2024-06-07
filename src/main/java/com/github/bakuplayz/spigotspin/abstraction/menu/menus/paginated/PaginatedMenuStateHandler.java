package com.github.bakuplayz.spigotspin.abstraction.menu.menus.paginated;

import com.github.bakuplayz.spigotspin.abstraction.menu.menus.state.MenuStateHandler;
import org.jetbrains.annotations.NotNull;

public abstract class PaginatedMenuStateHandler<S extends PaginatedMenuState> extends MenuStateHandler<S, PaginatedMenuStateObserver<S>> {

    public PaginatedMenuStateHandler(@NotNull PaginatedMenuStateObserver<S> observer, @NotNull S initialState) {
        super(observer, initialState);
    }


    public final void increasePage() {
        updateState(state.page, (page) -> page + 1, PaginatedMenuStateFlag.PAGE);
    }


    public final void decreasePage() {
        updateState(state.page, (page) -> page - 1, PaginatedMenuStateFlag.PAGE);
    }


    protected final <P> S onUpdateStateMiddleware(@NotNull P partialState, int flag) {
        if (flag == PaginatedMenuStateFlag.PAGE) {
            state.setPage(infer(partialState));
            state.setDisplayPage((Integer) partialState + 1);
            observer.onChangePage(infer(partialState));
        }

        return onUpdateState(partialState, flag);
    }


    @Override
    protected <P> S onUpdateState(@NotNull P partialState, int flag) {
        return state;
    }

}

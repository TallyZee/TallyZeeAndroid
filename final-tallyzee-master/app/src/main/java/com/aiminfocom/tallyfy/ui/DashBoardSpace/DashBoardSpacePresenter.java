package com.aiminfocom.tallyfy.ui.DashBoardSpace;

import javax.inject.Inject;

import com.aiminfocom.tallyfy.data.DataManager;
import com.aiminfocom.tallyfy.ui.base.BasePresenter;
import com.aiminfocom.tallyfy.utils.rx.SchedulerProvider;
import io.reactivex.disposables.CompositeDisposable;

/**
 * Created by GulshanPC on 25/07/2018.
 */

public class DashBoardSpacePresenter<V extends DashBoardSpaceMvp> extends BasePresenter<V>
        implements DashBoardSpaceMvpPresenter<V> {

    @Inject
    public DashBoardSpacePresenter(DataManager dataManager, SchedulerProvider schedulerProvider, CompositeDisposable compositeDisposable) {
        super(dataManager, schedulerProvider, compositeDisposable);
    }
}

package com.aiminfocom.tallyfy.ui.main.ListDashBoardFragment;

import com.aiminfocom.tallyfy.data.DataManager;
import com.aiminfocom.tallyfy.ui.base.BasePresenter;
import com.aiminfocom.tallyfy.utils.rx.SchedulerProvider;

import javax.inject.Inject;

import io.reactivex.disposables.CompositeDisposable;

/**
 * Created by GulshanPC on 27/06/2018.
 */

public class DashBoardPresenter<V extends DashBoardMvpView> extends BasePresenter<V>
        implements DashBoardMvpPresenter<V> {
    @Inject
    public DashBoardPresenter(DataManager dataManager, SchedulerProvider schedulerProvider, CompositeDisposable compositeDisposable) {
        super(dataManager, schedulerProvider, compositeDisposable);
    }
}

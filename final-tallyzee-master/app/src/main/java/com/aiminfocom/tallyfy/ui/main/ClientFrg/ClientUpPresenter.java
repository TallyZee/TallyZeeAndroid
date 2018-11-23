package com.aiminfocom.tallyfy.ui.main.ClientFrg;

import com.aiminfocom.tallyfy.data.DataManager;
import com.aiminfocom.tallyfy.ui.base.BasePresenter;
import com.aiminfocom.tallyfy.utils.rx.SchedulerProvider;

import javax.inject.Inject;

import io.reactivex.disposables.CompositeDisposable;

public class ClientUpPresenter<V extends ClientUpMvp> extends BasePresenter<V>
        implements ClientUpMvpPresenter<V> {
    @Inject
    public ClientUpPresenter(DataManager dataManager, SchedulerProvider schedulerProvider, CompositeDisposable compositeDisposable) {
        super(dataManager, schedulerProvider, compositeDisposable);
    }
}

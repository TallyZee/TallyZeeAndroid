package com.aiminfocom.tallyfy.ui.ClientDetails.SoldFragment;

import com.aiminfocom.tallyfy.data.DataManager;
import com.aiminfocom.tallyfy.ui.base.BasePresenter;
import com.aiminfocom.tallyfy.utils.rx.SchedulerProvider;

import javax.inject.Inject;

import io.reactivex.disposables.CompositeDisposable;

public class SoldFragmentPresenter<V extends SoldFragmentMvp> extends BasePresenter<V>
        implements SoldFragmentMvpPresenter<V> {
    @Inject
    public SoldFragmentPresenter(DataManager dataManager, SchedulerProvider schedulerProvider, CompositeDisposable compositeDisposable) {
        super(dataManager, schedulerProvider, compositeDisposable);
    }
}

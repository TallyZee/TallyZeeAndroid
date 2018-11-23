package com.aiminfocom.tallyfy.ui.ClientDetails.PurchaseFragment;

import com.aiminfocom.tallyfy.data.DataManager;
import com.aiminfocom.tallyfy.ui.base.BasePresenter;
import com.aiminfocom.tallyfy.utils.rx.SchedulerProvider;

import javax.inject.Inject;

import io.reactivex.disposables.CompositeDisposable;

public class PurchaseFragmentPresenter<V extends PurchaseFragmentMvp> extends BasePresenter<V>
        implements PurchaseFragmentMvpPresenter<V> {
    @Inject
    public PurchaseFragmentPresenter(DataManager dataManager, SchedulerProvider schedulerProvider, CompositeDisposable compositeDisposable) {
        super(dataManager, schedulerProvider, compositeDisposable);
    }
}

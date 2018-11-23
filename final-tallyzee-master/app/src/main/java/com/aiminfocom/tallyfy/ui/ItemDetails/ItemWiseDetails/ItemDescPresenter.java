package com.aiminfocom.tallyfy.ui.ItemDetails.ItemWiseDetails;

import com.aiminfocom.tallyfy.data.DataManager;
import com.aiminfocom.tallyfy.ui.base.BasePresenter;
import com.aiminfocom.tallyfy.utils.rx.SchedulerProvider;

import javax.inject.Inject;

import io.reactivex.disposables.CompositeDisposable;

public class ItemDescPresenter<V extends ItemDescMvp> extends BasePresenter<V>
        implements ItemDescMvpPresenter<V> {
    @Inject
    public ItemDescPresenter(DataManager dataManager, SchedulerProvider schedulerProvider, CompositeDisposable compositeDisposable) {
        super(dataManager, schedulerProvider, compositeDisposable);
    }
}

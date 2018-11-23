package com.aiminfocom.tallyfy.ui.ItemDetails.ItemWiseDetails.ItemMain;

import com.aiminfocom.tallyfy.data.DataManager;
import com.aiminfocom.tallyfy.ui.base.BasePresenter;
import com.aiminfocom.tallyfy.utils.rx.SchedulerProvider;

import javax.inject.Inject;

import io.reactivex.disposables.CompositeDisposable;

public class ItemContanierPresenter<V extends ItemContanierMvp> extends BasePresenter<V>
        implements ItemContainerMvpPresenter<V> {
    @Inject
    public ItemContanierPresenter(DataManager dataManager, SchedulerProvider schedulerProvider, CompositeDisposable compositeDisposable) {
        super(dataManager, schedulerProvider, compositeDisposable);
    }
}

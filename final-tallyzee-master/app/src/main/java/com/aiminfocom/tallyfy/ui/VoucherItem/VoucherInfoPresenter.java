package com.aiminfocom.tallyfy.ui.VoucherItem;

import com.aiminfocom.tallyfy.data.DataManager;
import com.aiminfocom.tallyfy.ui.base.BasePresenter;
import com.aiminfocom.tallyfy.utils.rx.SchedulerProvider;

import javax.inject.Inject;

import io.reactivex.disposables.CompositeDisposable;

public class VoucherInfoPresenter<V extends VoucherItemMvp> extends BasePresenter<V>
        implements VoucherItemMvpPresenter<V> {

    @Inject
    public VoucherInfoPresenter(DataManager dataManager, SchedulerProvider schedulerProvider, CompositeDisposable compositeDisposable) {
        super(dataManager, schedulerProvider, compositeDisposable);
    }
}

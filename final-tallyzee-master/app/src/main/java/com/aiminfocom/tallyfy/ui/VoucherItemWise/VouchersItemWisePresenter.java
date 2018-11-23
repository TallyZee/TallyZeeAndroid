package com.aiminfocom.tallyfy.ui.VoucherItemWise;

import com.aiminfocom.tallyfy.data.DataManager;
import com.aiminfocom.tallyfy.ui.base.BasePresenter;
import com.aiminfocom.tallyfy.utils.rx.SchedulerProvider;

import javax.inject.Inject;

import io.reactivex.disposables.CompositeDisposable;

public class VouchersItemWisePresenter<V extends VouchersItemWiseMvp> extends BasePresenter<V>
        implements VouchersItemWisePresenterMVp<V> {
    @Inject
    public VouchersItemWisePresenter(DataManager dataManager, SchedulerProvider schedulerProvider, CompositeDisposable compositeDisposable) {
        super(dataManager, schedulerProvider, compositeDisposable);
    }
}

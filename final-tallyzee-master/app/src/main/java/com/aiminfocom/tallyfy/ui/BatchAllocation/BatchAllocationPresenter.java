package com.aiminfocom.tallyfy.ui.BatchAllocation;

import com.aiminfocom.tallyfy.data.DataManager;
import com.aiminfocom.tallyfy.ui.base.BasePresenter;
import com.aiminfocom.tallyfy.utils.rx.SchedulerProvider;

import javax.inject.Inject;

import io.reactivex.disposables.CompositeDisposable;

public class BatchAllocationPresenter<V extends BatchAllocationMvp> extends BasePresenter<V>
        implements BatchAllocationMvpPresenter<V>  {
    @Inject
    public BatchAllocationPresenter(DataManager dataManager, SchedulerProvider schedulerProvider, CompositeDisposable compositeDisposable) {
        super(dataManager, schedulerProvider, compositeDisposable);
    }
}

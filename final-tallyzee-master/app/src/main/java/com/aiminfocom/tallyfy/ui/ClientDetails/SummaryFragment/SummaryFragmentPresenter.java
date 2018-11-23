package com.aiminfocom.tallyfy.ui.ClientDetails.SummaryFragment;

import com.aiminfocom.tallyfy.data.DataManager;
import com.aiminfocom.tallyfy.ui.base.BasePresenter;
import com.aiminfocom.tallyfy.utils.rx.SchedulerProvider;

import javax.inject.Inject;

import io.reactivex.disposables.CompositeDisposable;

public class SummaryFragmentPresenter<V extends SummaryFragmentMvp> extends BasePresenter<V>
        implements SumaryFragmentMvpPresenter<V> {
    @Inject
    public SummaryFragmentPresenter(DataManager dataManager, SchedulerProvider schedulerProvider, CompositeDisposable compositeDisposable) {
        super(dataManager, schedulerProvider, compositeDisposable);
    }
}

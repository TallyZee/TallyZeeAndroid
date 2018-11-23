package com.aiminfocom.tallyfy.ui.ProfitandLoss;

import com.aiminfocom.tallyfy.data.DataManager;
import com.aiminfocom.tallyfy.ui.base.BasePresenter;
import com.aiminfocom.tallyfy.utils.rx.SchedulerProvider;

import io.reactivex.disposables.CompositeDisposable;

public class ProfitPresenter <V extends ProfitnLossView> extends BasePresenter<V>
    implements ProfitnLossPresenter<V>{
    public ProfitPresenter(DataManager dataManager, SchedulerProvider schedulerProvider, CompositeDisposable compositeDisposable) {
        super(dataManager, schedulerProvider, compositeDisposable);
    }
}

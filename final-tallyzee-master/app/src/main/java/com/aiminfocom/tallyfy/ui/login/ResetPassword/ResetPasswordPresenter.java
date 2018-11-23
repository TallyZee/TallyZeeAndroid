package com.aiminfocom.tallyfy.ui.login.ResetPassword;

import com.aiminfocom.tallyfy.data.DataManager;
import com.aiminfocom.tallyfy.ui.base.BasePresenter;
import com.aiminfocom.tallyfy.utils.rx.SchedulerProvider;

import javax.inject.Inject;

import io.reactivex.disposables.CompositeDisposable;
public class ResetPasswordPresenter <V extends ResetPasswordView> extends BasePresenter<V>
        implements ResetPasswordMvpPresenter<V> {
    @Inject
    public ResetPasswordPresenter(DataManager dataManager, SchedulerProvider schedulerProvider, CompositeDisposable compositeDisposable) {
        super(dataManager, schedulerProvider, compositeDisposable);
    }
}

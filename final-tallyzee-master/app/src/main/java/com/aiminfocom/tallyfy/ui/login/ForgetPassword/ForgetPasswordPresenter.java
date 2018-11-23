package com.aiminfocom.tallyfy.ui.login.ForgetPassword;

import android.content.Context;

import com.aiminfocom.tallyfy.data.DataManager;
import com.aiminfocom.tallyfy.data.network.FirebaseNetwork.FirebaseConnection;
import com.aiminfocom.tallyfy.ui.base.BasePresenter;
import com.aiminfocom.tallyfy.ui.login.SignUp.SignUPMvpPresenter;
import com.aiminfocom.tallyfy.ui.login.SignUp.SignUpMvp;
import com.aiminfocom.tallyfy.utils.CommonUtils;
import com.aiminfocom.tallyfy.utils.rx.SchedulerProvider;

import javax.inject.Inject;

import io.reactivex.disposables.CompositeDisposable;

public class ForgetPasswordPresenter <V extends ForgetPasswordMvp> extends  BasePresenter<V>
        implements ForgetPasswordMvpPresenter<V> {

    @Inject
    public ForgetPasswordPresenter(DataManager dataManager, SchedulerProvider schedulerProvider, CompositeDisposable compositeDisposable) {
        super(dataManager, schedulerProvider, compositeDisposable);

    }


    public boolean checkPhone(String email, Context ctx) {
        if (email == null || email.isEmpty()) {
            getMvpView().onError("Email Cannot be empty");
            return false;
        } else {
            FirebaseConnection connection = new FirebaseConnection();
            connection.onForgetPassword(email, ctx);
            return true;
        }
    }
}


package com.aiminfocom.tallyfy.ui.login.ForgetPassword;

import android.content.Context;

import com.aiminfocom.tallyfy.di.PerActivity;
import com.aiminfocom.tallyfy.ui.base.MvpPresenter;
@PerActivity
public interface ForgetPasswordMvpPresenter <V extends ForgetPasswordMvp> extends MvpPresenter<V> {

            boolean checkPhone(String phone, Context ctx);

}

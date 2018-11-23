package com.aiminfocom.tallyfy.ui.login.ForgetPassword;

import android.app.ActivityOptions;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.aiminfocom.tallyfy.R;
import com.aiminfocom.tallyfy.data.network.FirebaseNetwork.onCalledInterface;
import com.aiminfocom.tallyfy.ui.base.BaseActivity;
import com.aiminfocom.tallyfy.ui.base.MvpView;
import com.aiminfocom.tallyfy.ui.login.LoginActivity;
import com.aiminfocom.tallyfy.ui.login.ResetPassword.ResetPasswordActivity;
import com.aiminfocom.tallyfy.ui.login.SignUp.SignUp;
import com.google.firebase.FirebaseException;
import com.google.firebase.auth.PhoneAuthCredential;
import com.google.firebase.auth.PhoneAuthProvider;

import java.util.concurrent.TimeUnit;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class ForgetPasswordActivity extends BaseActivity implements ForgetPasswordMvp,onCalledInterface {


    @Inject
    ForgetPasswordPresenter<ForgetPasswordMvp> mPresenter;

    @BindView(R.id.closePage)
    ImageView close;

    @BindView(R.id.continueButton)
    Button countinue;

    @BindView(R.id.et_reset_email)
    EditText et_email;

    onCalledInterface onCalledInterface;


    private ProgressDialog progressDialog;



    protected void onCreate(Bundle savedInstance)
    {
        super.onCreate(savedInstance);

        setContentView(R.layout.activity_forget_password);
        getActivityComponent().inject(this);
        mPresenter.onAttach(ForgetPasswordActivity.this);
        ButterKnife.bind(this);
        progressDialog = new ProgressDialog(ForgetPasswordActivity.this);
    }

    @Override
    protected void setUp() {

    }

    @OnClick(R.id.closePage)
    void onClose(View view)
    {
        Animation animation = AnimationUtils.loadAnimation(ForgetPasswordActivity.this, R.anim.slide_down);
        Intent intent = new Intent(ForgetPasswordActivity.this, LoginActivity.class);
        startActivity(intent, ActivityOptions.makeCustomAnimation(this,R.anim.fade_in,R.anim.slide_down).toBundle());
        finish();
    }

    @OnClick(R.id.continueButton)
    void onContinue(View view)
    {
        if(mPresenter.checkPhone(et_email.getText().toString(),this)) {
            progressDialog.setTitle("Sending Otp");
            progressDialog.create();
            progressDialog.show();
        }
    }


    public static Intent getStartIntent(Context context) {
        Intent intent = new Intent(context, ForgetPasswordActivity.class);
        return intent;
    }


    @Override
    public void onReturn(boolean b) {

    }

    @Override
    public void onPhoneVerificationSend(boolean b) {
        if(b)
        {
            progressDialog.dismiss();
            Intent intent = ResetPasswordActivity.getStartIntent(getApplicationContext());
            startActivity(intent);
        }
        else
        {
            progressDialog.dismiss();
            Toast.makeText(getApplicationContext(),"Phone Number Verification Failed",Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onSignUpReturn(boolean b) {

    }

    @Override
    public void onSignInReturn(boolean b) {

    }

    @Override
    public void returnOtp(boolean b) {
        if(b)
        {
            progressDialog.dismiss();
            Toast.makeText(getApplicationContext(),"Reset Email Was Sent",Toast.LENGTH_LONG).show();
            Intent intent = new Intent(this,LoginActivity.class);
            startActivity(intent);
            finish();
        }
        else
        {
            progressDialog.dismiss();
            Toast.makeText(getApplicationContext(),"Error Sending Email",Toast.LENGTH_LONG).show();
        }
    }

    @Override
    public void sendOtp(String s) {

    }
}

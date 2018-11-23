package com.aiminfocom.tallyfy.ui.login.ResetPassword;

import android.app.ActivityOptions;
import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.aiminfocom.tallyfy.R;
import com.aiminfocom.tallyfy.ui.base.BaseActivity;
import com.aiminfocom.tallyfy.ui.login.ForgetPassword.ForgetPasswordActivity;
import com.aiminfocom.tallyfy.ui.login.LoginActivity;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import jp.wasabeef.blurry.Blurry;

public class ResetPasswordActivity extends BaseActivity implements ResetPasswordView {

    @Inject
    ResetPasswordMvpPresenter<ResetPasswordView> mPresenter;
    @BindView(R.id.closePage)
    ImageView doClose;

    @BindView(R.id.otp)
    EditText otp;

    @BindView(R.id.newPassword)
    EditText newPassword;

    @BindView(R.id.confirmPass)
    EditText confirmPass;




    @BindView(R.id.resetDone)
    Button reset;
    private AlertDialog.Builder alertDialog;
    public void onCreate(Bundle savedInstance)
    {
        super.onCreate(savedInstance);
        setContentView(R.layout.activity_reset_password);
        ButterKnife.bind(this);
        getActivityComponent().inject(this);
         alertDialog = new AlertDialog.Builder(ResetPasswordActivity.this);
    }
    public static Intent getStartIntent(Context context) {
        Intent intent = new Intent(context, ResetPasswordActivity.class);
        return intent;
    }




    @OnClick(R.id.closePage)
    void onClose(View view)
    {

        Intent intent = new Intent(ResetPasswordActivity.this, LoginActivity.class);
        startActivity(intent, ActivityOptions.makeCustomAnimation(this,R.anim.fade_in,R.anim.slide_down).toBundle());
        finish();
    }


    @OnClick(R.id.resetDone)
    void getDetail(View view)
    {

        if(otp.getText().toString().trim().isEmpty())
        {
            otp.setError("Please Enter Otp Sent To Your Phone No.");
        }
        else if(newPassword.getText().toString().trim().isEmpty())
        {
            newPassword.setError("Please Enter New Password");
        }
        else if(confirmPass.getText().toString().trim().isEmpty())
        {
            confirmPass.setError("Please Enter Confirm Password");
        }
        else if(!newPassword.getText().toString().equals(confirmPass.getText().toString()))
        {
                confirmPass.setError("Password Does Not Matches");
        }
        else {

//        linearLayout.setBackgroundColor(Color.parseColor("#60964178"));
            LayoutInflater inflater = getLayoutInflater();
            View view1 = inflater.inflate(R.layout.custom_alert_dialog, null);
            alertDialog.setTitle("");
            alertDialog.setView(view1);
            alertDialog.create();
            alertDialog.show();
        }
    }



    @Override
    protected void setUp() {

    }
}

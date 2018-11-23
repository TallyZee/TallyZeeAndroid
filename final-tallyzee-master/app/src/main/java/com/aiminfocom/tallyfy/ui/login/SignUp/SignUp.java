package com.aiminfocom.tallyfy.ui.login.SignUp;

import android.app.Activity;
import android.app.ActivityOptions;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import java.util.List;

import javax.inject.Inject;

import com.aiminfocom.tallyfy.R;
import com.aiminfocom.tallyfy.data.BeanModels.BillsPaybale;
import com.aiminfocom.tallyfy.data.BeanModels.BillsReceivable;
import com.aiminfocom.tallyfy.data.BeanModels.SalesOrders;
import com.aiminfocom.tallyfy.data.BeanModels.User;
import com.aiminfocom.tallyfy.data.BeanModels.UserProfile;
import com.aiminfocom.tallyfy.data.BeanModels.profitnloss;
import com.aiminfocom.tallyfy.data.db.RoomPersistance.DatabaseCallback;
import com.aiminfocom.tallyfy.data.db.RoomPersistance.LocalCacheManager;
import com.aiminfocom.tallyfy.data.network.FirebaseNetwork.onCalledInterface;
import com.aiminfocom.tallyfy.data.network.InternetStatus.InternetConnection;
import com.aiminfocom.tallyfy.ui.base.BaseActivity;
import com.aiminfocom.tallyfy.ui.base.BaseFragment;
import com.aiminfocom.tallyfy.ui.login.LoginActivity;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class SignUp extends BaseActivity implements SignUpMvp,DatabaseCallback,onCalledInterface {

    @Inject
    SignUpPresenter<SignUpMvp> mPresenter;
    @BindView(R.id.et_email)
    EditText email;

    @BindView(R.id.et_last_name)
    EditText lastName;
    @BindView(R.id.et_cnf_password)
    EditText cnfPassword;
    @BindView(R.id.et_mobile)
    EditText mobile;
    @BindView(R.id.et_name)
    EditText name;
    @BindView(R.id.et_password)
    EditText password;
    @BindView(R.id.back)
    ImageView back;
    @BindView(R.id.singup_button)
    Button signupButton;
    private ProgressDialog progressDialog;





    public static Intent getStartIntent(Context context) {
        Intent intent = new Intent(context, SignUp.class);
        return intent;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up2);
        getActivityComponent().inject(this);

        setUnBinder(ButterKnife.bind(this));

        mPresenter.onAttach(SignUp.this);
        progressDialog = new ProgressDialog(SignUp.this);

    }
@OnClick(R.id.singup_button)
void save(View view)
{
    boolean isEmailVerified = false;
    String getEmail = email.getText().toString();
    String getName = name.getText().toString() +"\t" + lastName.getText().toString();
    String getPass = String.valueOf(password.getText());
    String getCnPass = cnfPassword.getText().toString();
    String getPhone = mobile.getText().toString();
//    LocalCacheManager.getInstance(this).addUser(this,new User(email.getText().toString(),name.getText().toString(),mobile.getText().toString(),password.getText().toString()));
    if(InternetConnection.isNetworkAvailable(getApplicationContext())) {
        if (mPresenter.onCreateAccount(new UserProfile(getName, getEmail, getPass, getCnPass, getPhone, isEmailVerified), this)) {
            progressDialog.setTitle("Creating Account");
            progressDialog.setMessage("Please Wait for a while");
            progressDialog.show();
        }
    }
    else
    {
        Toast.makeText(getApplicationContext(),"Seems Like There is No Active Internet Connection",Toast.LENGTH_LONG).show();
    }



}
@OnClick(R.id.back)
void back(View view)
{
    Intent intent = new Intent(SignUp.this,LoginActivity.class);
    startActivity(intent,ActivityOptions.makeCustomAnimation(getApplicationContext(),R.anim.fade_in,R.anim.slide_down).toBundle());
}
    @Override
    protected void setUp() {

    }

    @Override
    public void onUsersLoaded(User users) {

    }

    @Override
    public void onUserDeleted() {

    }

    @Override
    public void onUserAdded() {
        System.out.println("ok user added");
        Toast.makeText(this,"User Added Sucesscfully",Toast.LENGTH_LONG).show();
        finish();
    }

    @Override
    public void onDataNotAvailable() {

    }

    @Override
    public void onUserUpdated() {

    }

    @Override
    public void onListBillRecable(List<BillsPaybale> list) {

    }

    @Override
    public void onListBillRecableSave() {

    }

    @Override
    public void onProfitandLoss(List<profitnloss> list) {

    }

    @Override
    public void onSalesOrdersCallBack(List<SalesOrders> list) {

    }

    @Override
    public void onReturn(boolean b) {

    }

    @Override
    public void onPhoneVerificationSend(boolean b) {

    }

    @Override
    public void onSignUpReturn(boolean b) {
        if(b)
        {
          progressDialog.dismiss();
          Intent intent = new Intent(SignUp.this,LoginActivity.class);
          startActivity(intent);
           Toast.makeText(getApplicationContext(),"Account Created Successfully,Please Verify your email",Toast.LENGTH_LONG).show();
        }
        else
        {
           progressDialog.dismiss();
           Toast.makeText(getApplicationContext(),"Error Creating Account !! Please Try again later",Toast.LENGTH_LONG).show();

        }
    }

    @Override
    public void onSignInReturn(boolean b) {

    }

    @Override
    public void returnOtp(boolean b) {

    }

    @Override
    public void sendOtp(String s) {

    }
}

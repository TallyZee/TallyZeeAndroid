package com.aiminfocom.tallyfy.ui.ContactUs;


import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.aiminfocom.tallyfy.R;
import com.aiminfocom.tallyfy.data.BeanModels.UserFeedback;
import com.aiminfocom.tallyfy.data.network.DatabaseCurrentUser.CurrentUserInfo;
import com.aiminfocom.tallyfy.ui.base.BaseFragment;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

import static android.Manifest.permission.CALL_PHONE;
import static android.Manifest.permission.CALL_PHONE;
/**
 * A simple {@link Fragment} subclass.
 */
public class ContactUsFragment extends BaseFragment implements ContactMvp {

    public static final String TAG = "ConactUsFragment";
    private static final int MY_PERMISSIONS_REQUEST_CALL_PHONE = 1 ;


    public static ContactUsFragment newInstance()
    {
        ContactUsFragment cs = new ContactUsFragment();
        return cs;
    }
    public ContactUsFragment() {
        // Required empty public constructor
    }

    @BindView(R.id.goBack)
    ImageView goBack;

    @BindView(R.id.whatsapp)
    TextView whatsapp;

    @BindView(R.id.facebook)
    TextView facebook;

    @BindView(R.id.phonee)
    TextView phone;

    @BindView(R.id.sms)
    TextView sms;

    @BindView(R.id.topic)
    EditText topic;

    @BindView(R.id.help)
    EditText help;

    @BindView(R.id.onSubmit)
    Button onSubmit;

    @BindView(R.id.email)
    EditText email;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_contact_us, container, false);
        setUnBinder(ButterKnife.bind(this,view));
        return view;
    }

    @OnClick(R.id.goBack)
    void onClick(View view)
    {
        getBaseActivity().onFragmentDetached(TAG);
    }


    @OnClick(R.id.whatsapp)
    void onClickwhatsapp(View view)
    {
        String number = "02261738383";
        Intent intent = new Intent();
        intent.setAction(Intent.ACTION_SEND);
        intent.setType("text/plain");
        intent.putExtra(Intent.EXTRA_TEXT, "This is my text to send.");
        intent.putExtra("jid", number + "@s.whatsapp.net");
        intent.setPackage("com.whatsapp");
        if (intent.resolveActivity(getActivity().getPackageManager()) == null) {
            Toast.makeText(getContext(), "Error", Toast.LENGTH_SHORT).show();
            return;
        }
        startActivity(intent);

    }

    @OnClick(R.id.phonee)
    void onPhoneCLick(View view) {
        Intent intent = new Intent(Intent.ACTION_CALL);
        intent.setData(Uri.parse("tel:" + "9730345171"));
        if (ContextCompat.checkSelfPermission(getBaseActivity(),
                Manifest.permission.CALL_PHONE)
                != PackageManager.PERMISSION_GRANTED) {

            ActivityCompat.requestPermissions(getBaseActivity(),
                    new String[]{Manifest.permission.CALL_PHONE},
                    MY_PERMISSIONS_REQUEST_CALL_PHONE);

            // MY_PERMISSIONS_REQUEST_CALL_PHONE is an
            // app-defined int constant. The callback method gets the
            // result of the request.
        } else {
            //You already have permission
            try {
                startActivity(intent);
            } catch (SecurityException e) {
                e.printStackTrace();
            }
        }
    }

    @OnClick(R.id.sms)
    void onSms()
    {
        Intent emailIntent = new Intent(Intent.ACTION_SENDTO, Uri.parse("mailto:" + "info@aiminfocom.com"));
        emailIntent.putExtra(Intent.EXTRA_SUBJECT, "PROBLEM");
        emailIntent.putExtra(Intent.EXTRA_TEXT, "BODY");
//emailIntent.putExtra(Intent.EXTRA_HTML_TEXT, body); //If you are using HTML in your body text

        startActivity(Intent.createChooser(emailIntent, "TITLE"));
    }
    @Override
    protected void setUp(View view) {

    }


    @OnClick(R.id.onSubmit)
    void getClick(View view)
    {
        if(!topic.getText().toString().isEmpty() && !help.getText().toString().isEmpty() && !email.getText().toString().isEmpty() )
        {
            UserFeedback userFeedback = new UserFeedback(topic.getText().toString(),help.getText().toString());
            CurrentUserInfo.setConactDetails(userFeedback);
            Toast.makeText(getContext(),"Admin will Contact you ,Feel Relaxed",Toast.LENGTH_LONG).show();
            topic.setText("");
            email.setText("");
            help.setText("");
        }
        else if(topic.getText().toString().isEmpty()) {
            topic.setError("Field Cannot Be Empty");
        }
        else if(email.getText().toString().isEmpty()) {
            email.setError("Field Cannot Be Empty");
        }
        else if(help.getText().toString().isEmpty()) {
            help.setError("Field Cannot Be Empty");
        }

    }
}

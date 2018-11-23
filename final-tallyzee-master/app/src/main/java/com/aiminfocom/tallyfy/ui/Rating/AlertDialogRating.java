package com.aiminfocom.tallyfy.ui.Rating;


import android.app.Dialog;
import android.app.Notification;
import android.app.NotificationManager;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;


import android.support.v4.app.NotificationCompat;
import android.support.v4.app.NotificationManagerCompat;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import com.aiminfocom.tallyfy.R;
import com.aiminfocom.tallyfy.data.BeanModels.UserFeedback;
import com.aiminfocom.tallyfy.data.network.DatabaseCurrentUser.CurrentUserInfo;
import com.aiminfocom.tallyfy.ui.login.LoginActivity;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

public class AlertDialogRating extends Dialog {

    private static final String CHANNEL = "Personal_notifications";
    private static final int channel_id = 001;
    private Context context;
    private TextView yesButton,noButton;
    private EditText editFeedback;
    private RatingBar ratingBar;
    private float stars;
    public AlertDialogRating(Context context)
    {
        super(context);
        this.context=context;
    }


    protected void onCreate(Bundle savedInstance)
    {
        super.onCreate(savedInstance);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.custom_rate_layout);
        yesButton = findViewById(R.id.yesButton);
        editFeedback = findViewById(R.id.editFeedback);
        ratingBar = findViewById(R.id.ratingBar);
        onClickMethod();


    }

    public void onClickMethod()
    {
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        int email = user.getEmail().hashCode();


        ratingBar.setOnRatingBarChangeListener(new RatingBar.OnRatingBarChangeListener() {
            @Override
            public void onRatingChanged(RatingBar ratingBar, float rating, boolean fromUser) {
                stars = ratingBar.getRating();
            }
        });


        yesButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!editFeedback.getText().toString().isEmpty() && ratingBar.getRating()!=0)
                {
                    UserFeedback userFeedback = new UserFeedback(editFeedback.getText().toString(),String.valueOf(stars));
                    CurrentUserInfo.setFeedbackDetails(userFeedback);
                    Toast.makeText(getContext(),"Thank You For Your Valuable Feedback",Toast.LENGTH_LONG).show();
                    dismiss();
                }
                else if(editFeedback.getText().toString().isEmpty())
                {
                    editFeedback.setError("Field Cannot be Empty");
                }
                else
                {
                    Toast.makeText(getContext(),"Please Select Rating",Toast.LENGTH_LONG).show();
                }
            }
        });
    }

}




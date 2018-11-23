package com.aiminfocom.tallyfy.ui.Users;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.aiminfocom.tallyfy.R;
import com.aiminfocom.tallyfy.data.BeanModels.SettingPojo;
import com.aiminfocom.tallyfy.data.BeanModels.UserProfile;
import com.aiminfocom.tallyfy.data.BeanModels.UserProfiles;
import com.aiminfocom.tallyfy.ui.base.BaseViewHolder;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.zip.Inflater;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.reactivex.subjects.PublishSubject;
import io.reactivex.subjects.Subject;

public class UserAdapter extends RecyclerView.Adapter<UserAdapter.UserViewHolder> {

    private HashSet<UserProfile> arrayList;
    private Context context;
    private Subject<UserProfile> subject;
    @Inject
    public UserAdapter()
    {
            subject = PublishSubject.create();
    }

    @NonNull
    @Override
    public UserViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.user_profile_list,viewGroup,false);
        UserViewHolder userViewHolder = new UserViewHolder(view);
        return userViewHolder;
    }
    public io.reactivex.Observable<UserProfile> getClick() {
        return subject;
    }

    @Override
    public void onBindViewHolder(@NonNull UserViewHolder userViewHolder, int i) {
        userViewHolder.onBind(i);

    }

    public void OnClick(HashSet<UserProfile> userProfiles, Context context)
    {
        this.arrayList=userProfiles;
        this.context=context;
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }

    class UserViewHolder extends BaseViewHolder
    {

        @BindView(R.id.relativeLayoutUser)
        RelativeLayout relativeLayout;
        @BindView(R.id.userProfilename)
        TextView userProfilename;
        @BindView(R.id.userProfileemail)
        TextView userProfileemail;
        public UserViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this,itemView);
        }

        @Override
        protected void clear() {

        }

        @Override
        public void onBind(int position) {
            super.onBind(position);
            ArrayList<UserProfile> list = new ArrayList<>(arrayList);
            userProfilename.setText(list.get(0).getName());
            userProfileemail.setText(list.get(0).getEmail());
            relativeLayout.setOnClickListener(v -> subject.onNext(list.get(0)));


        }
    }
}

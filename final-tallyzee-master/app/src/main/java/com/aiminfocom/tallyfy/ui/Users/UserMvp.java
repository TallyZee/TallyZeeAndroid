package com.aiminfocom.tallyfy.ui.Users;

import com.aiminfocom.tallyfy.data.BeanModels.UserProfile;
import com.aiminfocom.tallyfy.ui.base.MvpView;

import java.util.ArrayList;

/**
 * Created by GulshanPC on 25/06/2018.
 */

public interface UserMvp extends MvpView {

    void getUserProfileResponse(ArrayList<UserProfile> arrayList);
}

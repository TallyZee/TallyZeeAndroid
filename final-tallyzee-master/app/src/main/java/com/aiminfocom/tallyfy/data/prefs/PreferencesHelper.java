

package com.aiminfocom.tallyfy.data.prefs;

import com.aiminfocom.tallyfy.data.BeanModels.UserProfile;
import com.aiminfocom.tallyfy.data.DataManager;

/**
 * Created by janisharali on 27/01/17.
 */

public interface PreferencesHelper {



    int getCurrentUserLoggedInMode();

    void setCurrentUserLoggedInMode(DataManager.LoggedInMode mode);

    Long getCurrentUserId();

    void setCurrentUserId(Long userId);

    String getCurrentUserName();

    void setCurrentUserName(String userName);

    String getCurrentUserEmail();

    void setCurrentUserEmail(String email);

    String getCurrentUserProfilePicUrl();

    void setCurrentUserProfilePicUrl(String profilePicUrl);

    String getAccessToken();

    void setAccessToken(String accessToken);

    String getCompanyName();

    void setCompanyName(String companyName);


}

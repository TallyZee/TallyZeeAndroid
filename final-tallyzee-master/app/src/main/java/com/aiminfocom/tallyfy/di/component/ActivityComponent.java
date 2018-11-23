

package com.aiminfocom.tallyfy.di.component;


import com.aiminfocom.tallyfy.di.PerActivity;
import com.aiminfocom.tallyfy.di.module.ActivityModule;
import com.aiminfocom.tallyfy.ui.BatchAllocation.BatchAllocation;
import com.aiminfocom.tallyfy.ui.ClientDetails.ClientDetails;
import com.aiminfocom.tallyfy.ui.ClientDetails.PurchaseFragment.PurchaseFragment;
import com.aiminfocom.tallyfy.ui.ClientDetails.SoldFragment.SoldFragment;
import com.aiminfocom.tallyfy.ui.ClientDetails.SummaryFragment.SummaryFragment;
import com.aiminfocom.tallyfy.ui.Companies.CompaniesFragment;
import com.aiminfocom.tallyfy.ui.DashBoardSpace.DashBoardSpaceActivity;
import com.aiminfocom.tallyfy.ui.DashBoardSpace.DashBoardSpaceMvp;
import com.aiminfocom.tallyfy.ui.ItemDetails.ItemWiseDetails.Category.CategoryFragment;
import com.aiminfocom.tallyfy.ui.ItemDetails.ItemWiseDetails.Group.GroupFragment;
import com.aiminfocom.tallyfy.ui.ItemDetails.ItemWiseDetails.ItemDesc;
import com.aiminfocom.tallyfy.ui.ItemDetails.ItemWiseDetails.ItemMain.ItemContainer;
import com.aiminfocom.tallyfy.ui.ProfitandLoss.ProfitnLossActivity;
import com.aiminfocom.tallyfy.ui.Settings.SettingFragment;
import com.aiminfocom.tallyfy.ui.Users.UserFragment;
import com.aiminfocom.tallyfy.ui.Users.UsersFragment;
import com.aiminfocom.tallyfy.ui.VoucherItem.VoucherInfo;

import com.aiminfocom.tallyfy.ui.VoucherItemWise.VouchersItemWise;
import com.aiminfocom.tallyfy.ui.about.AboutFragment;
import com.aiminfocom.tallyfy.ui.feed.FeedActivity;
import com.aiminfocom.tallyfy.ui.feed.blogs.BlogFragment;
import com.aiminfocom.tallyfy.ui.feed.opensource.OpenSourceFragment;
import com.aiminfocom.tallyfy.ui.login.ForgetPassword.ForgetPasswordActivity;
import com.aiminfocom.tallyfy.ui.login.LoginActivity;


import com.aiminfocom.tallyfy.ui.login.ResetPassword.ResetPasswordActivity;
import com.aiminfocom.tallyfy.ui.login.SignUp.SignUp;
import com.aiminfocom.tallyfy.ui.main.ClientFrg.ClientUpFragment;
import com.aiminfocom.tallyfy.ui.main.ListDashBoardFragment.DashBoardFragment;
import com.aiminfocom.tallyfy.ui.main.MainActivity;
import com.aiminfocom.tallyfy.ui.main.rating.RateUsDialog;
import com.aiminfocom.tallyfy.ui.splash.SplashActivity;

import dagger.Component;


@PerActivity
@Component(dependencies = ApplicationComponent.class, modules = ActivityModule.class)
public interface ActivityComponent {
    void inject(ClientUpFragment clientUpFragment);
    void inject(ResetPasswordActivity resetPasswordActivity);

    void inject(BatchAllocation batchAllocation);

    void inject(ForgetPasswordActivity forgetPasswordActivity);

    void inject(ItemContainer itemContainer);

    void inject(GroupFragment groupFragment);

    void inject(UsersFragment usersFragment);


//    void inject(CategoryFragment categoryFragment);


    void inject(ItemDesc itemDesc);

    void inject(PurchaseFragment purchaseFragment);

    void inject(SoldFragment soldFragment);

    void inject(SummaryFragment summaryFragment);

    void inject(ClientDetails clientDetails);

    void inject(VoucherInfo voucherInfo);

    void inject(SignUp signUp);

    void inject(VouchersItemWise vouchersItemWise);

    void inject(DashBoardSpaceActivity dashBoardSpaceActivity);

    void inject(SettingFragment settingFragment);

    void inject(CompaniesFragment companiesFragment);

    void inject(UserFragment userFragment);

    void inject(DashBoardFragment dashBoardFragment);

    void inject(MainActivity activity);

    void inject(LoginActivity activity);

    void inject(SplashActivity activity);

    void inject(FeedActivity activity);

    void inject(AboutFragment fragment);

    void inject(OpenSourceFragment fragment);

    void inject(BlogFragment fragment);

    void inject(RateUsDialog dialog);

    void inject(ProfitnLossActivity profitnLossActivity);


}

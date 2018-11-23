/*
 * Copyright (C) 2017 MINDORKS NEXTGEN PRIVATE LIMITED
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     https://mindorks.com/license/apache-v2
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License
 */

package com.aiminfocom.tallyfy.di.module;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;

import com.aiminfocom.tallyfy.data.network.model.BlogResponse;
import com.aiminfocom.tallyfy.data.network.model.OpenSourceResponse;
import com.aiminfocom.tallyfy.di.ActivityContext;
import com.aiminfocom.tallyfy.di.PerActivity;
import com.aiminfocom.tallyfy.ui.BatchAllocation.BatchAllocationMvp;
import com.aiminfocom.tallyfy.ui.BatchAllocation.BatchAllocationMvpPresenter;
import com.aiminfocom.tallyfy.ui.BatchAllocation.BatchAllocationPresenter;
import com.aiminfocom.tallyfy.ui.ClientDetails.ClientDetailsMvp;
import com.aiminfocom.tallyfy.ui.ClientDetails.ClientDetailsMvpPresenter;
import com.aiminfocom.tallyfy.ui.ClientDetails.ClientDetailsPresenter;
import com.aiminfocom.tallyfy.ui.DashBoardSpace.DashBoardSpaceActivity;
import com.aiminfocom.tallyfy.ui.DashBoardSpace.DashBoardSpaceMvp;
import com.aiminfocom.tallyfy.ui.DashBoardSpace.DashBoardSpaceMvpPresenter;
import com.aiminfocom.tallyfy.ui.DashBoardSpace.DashBoardSpacePresenter;
import com.aiminfocom.tallyfy.ui.ItemDetails.ItemWiseDetails.Category.CategoryMvpPresenter;
import com.aiminfocom.tallyfy.ui.ItemDetails.ItemWiseDetails.Category.CategoryMvpView;
import com.aiminfocom.tallyfy.ui.ItemDetails.ItemWiseDetails.Category.CategoryPresenter;
import com.aiminfocom.tallyfy.ui.Users.UserMvp;
import com.aiminfocom.tallyfy.ui.Users.UserMvpPresenter;
import com.aiminfocom.tallyfy.ui.Users.UserPresenter;
import com.aiminfocom.tallyfy.ui.VoucherItem.VoucherInfoPresenter;
import com.aiminfocom.tallyfy.ui.VoucherItem.VoucherItemMvp;
import com.aiminfocom.tallyfy.ui.VoucherItem.VoucherItemMvpPresenter;
import com.aiminfocom.tallyfy.ui.VoucherItemWise.VouchersItemWise;
import com.aiminfocom.tallyfy.ui.VoucherItemWise.VouchersItemWiseMvp;
import com.aiminfocom.tallyfy.ui.VoucherItemWise.VouchersItemWisePresenter;
import com.aiminfocom.tallyfy.ui.VoucherItemWise.VouchersItemWisePresenterMVp;
import com.aiminfocom.tallyfy.ui.about.AboutMvpPresenter;
import com.aiminfocom.tallyfy.ui.about.AboutMvpView;
import com.aiminfocom.tallyfy.ui.about.AboutPresenter;
import com.aiminfocom.tallyfy.ui.feed.FeedMvpPresenter;
import com.aiminfocom.tallyfy.ui.feed.FeedMvpView;
import com.aiminfocom.tallyfy.ui.feed.FeedPagerAdapter;
import com.aiminfocom.tallyfy.ui.feed.FeedPresenter;
import com.aiminfocom.tallyfy.ui.feed.blogs.BlogAdapter;
import com.aiminfocom.tallyfy.ui.feed.blogs.BlogMvpPresenter;
import com.aiminfocom.tallyfy.ui.feed.blogs.BlogMvpView;
import com.aiminfocom.tallyfy.ui.feed.blogs.BlogPresenter;
import com.aiminfocom.tallyfy.ui.feed.opensource.OpenSourceAdapter;
import com.aiminfocom.tallyfy.ui.feed.opensource.OpenSourceMvpPresenter;
import com.aiminfocom.tallyfy.ui.feed.opensource.OpenSourceMvpView;
import com.aiminfocom.tallyfy.ui.feed.opensource.OpenSourcePresenter;
import com.aiminfocom.tallyfy.ui.login.ForgetPassword.ForgetPasswordMvp;
import com.aiminfocom.tallyfy.ui.login.ForgetPassword.ForgetPasswordMvpPresenter;
import com.aiminfocom.tallyfy.ui.login.ForgetPassword.ForgetPasswordPresenter;
import com.aiminfocom.tallyfy.ui.login.LoginMvpPresenter;
import com.aiminfocom.tallyfy.ui.login.LoginMvpView;
import com.aiminfocom.tallyfy.ui.login.LoginPresenter;

import com.aiminfocom.tallyfy.ui.login.ResetPassword.ResetPasswordMvpPresenter;
import com.aiminfocom.tallyfy.ui.login.ResetPassword.ResetPasswordPresenter;
import com.aiminfocom.tallyfy.ui.login.ResetPassword.ResetPasswordView;
import com.aiminfocom.tallyfy.ui.main.MainMvpPresenter;
import com.aiminfocom.tallyfy.ui.main.MainMvpView;
import com.aiminfocom.tallyfy.ui.main.MainPresenter;
import com.aiminfocom.tallyfy.ui.main.rating.RatingDialogMvpPresenter;
import com.aiminfocom.tallyfy.ui.main.rating.RatingDialogMvpView;
import com.aiminfocom.tallyfy.ui.main.rating.RatingDialogPresenter;
import com.aiminfocom.tallyfy.ui.splash.SplashMvpPresenter;
import com.aiminfocom.tallyfy.ui.splash.SplashMvpView;
import com.aiminfocom.tallyfy.ui.splash.SplashPresenter;
import com.aiminfocom.tallyfy.utils.rx.AppSchedulerProvider;
import com.aiminfocom.tallyfy.utils.rx.SchedulerProvider;

import java.util.ArrayList;

import dagger.Module;
import dagger.Provides;
import io.reactivex.disposables.CompositeDisposable;

/**
 * Created by janisharali on 27/01/17.
 */

@Module
public class ActivityModule {

    private AppCompatActivity mActivity;

    public ActivityModule(AppCompatActivity activity) {
        this.mActivity = activity;
    }

    @Provides
    @ActivityContext
    Context provideContext() {
        return mActivity;
    }

    @Provides
    AppCompatActivity provideActivity() {
        return mActivity;
    }

    @Provides
    CompositeDisposable provideCompositeDisposable() {
        return new CompositeDisposable();
    }

    @Provides
    SchedulerProvider provideSchedulerProvider() {
        return new AppSchedulerProvider();
    }

    @Provides
    @PerActivity
    SplashMvpPresenter<SplashMvpView> provideSplashPresenter(
            SplashPresenter<SplashMvpView> presenter) {
        return presenter;
    }
    @Provides
    @PerActivity

    ForgetPasswordMvpPresenter<ForgetPasswordMvp> provideForgrtPresenter(
            ForgetPasswordPresenter<ForgetPasswordMvp> presenter) {
        return presenter;
    }


    @Provides
    @PerActivity
    UserMvpPresenter<UserMvp> provideUsersFragmentPresenter(
            UserPresenter<UserMvp> presenter) {
        return presenter;
    }


    @Provides
    @PerActivity
    CategoryMvpPresenter<CategoryMvpView> provideCategoryFragmentPresenter(
            CategoryPresenter<CategoryMvpView> presenter) {
        return presenter;
    }

    @Provides
    @PerActivity
    BatchAllocationMvpPresenter<BatchAllocationMvp> provideBatchAllocationPresenter(
            BatchAllocationPresenter<BatchAllocationMvp> presenter) {
        return presenter;
    }
    @Provides
    @PerActivity
    ResetPasswordMvpPresenter<ResetPasswordView> provideResetPasswordPresenter(
            ResetPasswordPresenter<ResetPasswordView> presenter)
    {
        return presenter;
    }
    @Provides
    AboutMvpPresenter<AboutMvpView> provideAboutPresenter(
            AboutPresenter<AboutMvpView> presenter) {
        return presenter;
    }

    @Provides
    @PerActivity
    LoginMvpPresenter<LoginMvpView> provideLoginPresenter(
            LoginPresenter<LoginMvpView> presenter) {
        return presenter;
    }

    @Provides
    @PerActivity
    DashBoardSpaceMvpPresenter<DashBoardSpaceMvp> provideDashBoardSpacePresenter(
                    DashBoardSpacePresenter<DashBoardSpaceMvp> presenter) {
        return presenter;
    }



    @Provides
    @PerActivity
    VouchersItemWisePresenterMVp<VouchersItemWiseMvp> provideVouchersItemWisePresenter(
            VouchersItemWisePresenter<VouchersItemWiseMvp> presenter) {
        return presenter;
    }
    @Provides
    @PerActivity
    VoucherItemMvpPresenter<VoucherItemMvp> provideVoucherInfoPresenter(
            VoucherInfoPresenter<VoucherItemMvp> presenter) {
        return presenter;
    }

    @Provides
    @PerActivity
    MainMvpPresenter<MainMvpView> provideMainPresenter(
            MainPresenter<MainMvpView> presenter) {
        return presenter;
    }

    @Provides
    RatingDialogMvpPresenter<RatingDialogMvpView> provideRateUsPresenter(
            RatingDialogPresenter<RatingDialogMvpView> presenter) {
        return presenter;
    }

    @Provides
    ClientDetailsMvpPresenter<ClientDetailsMvp> provideClientDetailsPresenter(
            ClientDetailsPresenter<ClientDetailsMvp> presenter) {
        return presenter;
    }


    @Provides
    FeedMvpPresenter<FeedMvpView> provideFeedPresenter(
            FeedPresenter<FeedMvpView> presenter) {
        return presenter;
    }

    @Provides
    OpenSourceMvpPresenter<OpenSourceMvpView> provideOpenSourcePresenter(
            OpenSourcePresenter<OpenSourceMvpView> presenter) {
        return presenter;
    }

    @Provides
    BlogMvpPresenter<BlogMvpView> provideBlogMvpPresenter(
            BlogPresenter<BlogMvpView> presenter) {
        return presenter;
    }



    @Provides
    FeedPagerAdapter provideFeedPagerAdapter(AppCompatActivity activity) {
        return new FeedPagerAdapter(activity.getSupportFragmentManager());
    }

    @Provides
    OpenSourceAdapter provideOpenSourceAdapter() {
        return new OpenSourceAdapter(new ArrayList<OpenSourceResponse.Repo>());
    }

    @Provides
    BlogAdapter provideBlogAdapter() {
        return new BlogAdapter(new ArrayList<BlogResponse.Blog>());
    }

    @Provides
    LinearLayoutManager provideLinearLayoutManager(AppCompatActivity activity) {
        return new LinearLayoutManager(activity);
    }
}

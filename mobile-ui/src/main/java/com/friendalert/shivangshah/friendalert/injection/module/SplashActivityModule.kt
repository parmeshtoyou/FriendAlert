package com.friendalert.shivangshah.friendalert.injection.module

import com.friendalert.shivangshah.domain.user.CreateUser
import com.friendalert.shivangshah.domain.user.GetUser
import com.friendalert.shivangshah.friendalert.login.LoginActivity
import com.friendalert.shivangshah.friendalert.login.SplashActivity
import com.friendalert.shivangshah.presentation.login.LoginContract
import com.friendalert.shivangshah.presentation.login.LoginPresenter
import com.friendalert.shivangshah.presentation.splash.SplashContract
import com.friendalert.shivangshah.presentation.splash.SplashPresenter
import dagger.Module
import dagger.Provides

/**
 * Created by shivangshah on 11/20/17.
 */
@Module
open class SplashActivityModule {

    @Provides
    internal fun provideSplashView(splashActivity: SplashActivity): SplashContract.View {
        return splashActivity
    }

    @Provides
    internal fun provideSplashPresenter(mainView: SplashContract.View,
                                       getUser: GetUser):
            SplashContract.Presenter {
        return SplashPresenter(mainView, getUser)
    }

}
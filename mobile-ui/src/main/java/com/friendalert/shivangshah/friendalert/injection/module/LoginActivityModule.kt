package com.friendalert.shivangshah.friendalert.injection.module

import com.friendalert.shivangshah.domain.user.CreateUser
import com.friendalert.shivangshah.friendalert.injection.scopes.PerActivity
import com.friendalert.shivangshah.friendalert.injection.scopes.PerFragment
import com.friendalert.shivangshah.friendalert.login.LoginActivity
import com.friendalert.shivangshah.presentation.login.LoginContract
import com.friendalert.shivangshah.presentation.login.LoginPresenter
import dagger.Module
import dagger.Provides

/**
 * Created by shivangshah on 11/13/17.
 */
@Module
open class LoginActivityModule {

    @Provides
    internal fun provideLoginView(loginActivity: LoginActivity): LoginContract.View {
        return loginActivity
    }

    @Provides
    internal fun provideLoginPresenter(mainView: LoginContract.View,
                                          createUser: CreateUser):
            LoginContract.Presenter {
        return LoginPresenter(mainView, createUser)
    }


}
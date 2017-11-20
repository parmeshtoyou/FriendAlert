package com.friendalert.shivangshah.friendalert.injection.module

import android.app.Activity
import com.friendalert.shivangshah.friendalert.HomeActivity
import com.friendalert.shivangshah.friendalert.login.LoginActivity
import com.friendalert.shivangshah.friendalert.injection.component.HomeActivitySubComponent
import com.friendalert.shivangshah.friendalert.injection.component.LoginActivitySubComponent
import com.friendalert.shivangshah.friendalert.injection.component.SplashActivitySubComponent
import com.friendalert.shivangshah.friendalert.login.SplashActivity
import dagger.Binds
import dagger.Module
import dagger.android.ActivityKey
import dagger.android.AndroidInjector
import dagger.multibindings.IntoMap


// Add all activities in this module
@Module
abstract class ActivityBuilderModule {

    @Binds
    @IntoMap
    @ActivityKey(HomeActivity::class)
    internal abstract fun bindHomeActivity(builder: HomeActivitySubComponent.Builder): AndroidInjector.Factory<out Activity>

    @Binds
    @IntoMap
    @ActivityKey(LoginActivity::class)
    internal abstract fun bindLoginActivity(builder: LoginActivitySubComponent.Builder): AndroidInjector.Factory<out Activity>

    @Binds
    @IntoMap
    @ActivityKey(SplashActivity::class)
    internal abstract fun bindSplashActivity(builder: SplashActivitySubComponent.Builder): AndroidInjector.Factory<out Activity>

}
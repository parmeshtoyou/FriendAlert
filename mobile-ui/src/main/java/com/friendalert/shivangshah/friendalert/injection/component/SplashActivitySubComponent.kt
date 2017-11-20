package com.friendalert.shivangshah.friendalert.injection.component

import com.friendalert.shivangshah.friendalert.injection.module.SplashActivityModule
import com.friendalert.shivangshah.friendalert.login.SplashActivity
import dagger.Subcomponent
import dagger.android.AndroidInjector

/**
 * Created by shivangshah on 11/20/17.
 */
@Subcomponent(modules = arrayOf(SplashActivityModule::class))
interface SplashActivitySubComponent : AndroidInjector<SplashActivity> {

    @Subcomponent.Builder
    abstract class Builder : AndroidInjector.Builder<SplashActivity>()

}
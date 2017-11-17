package com.friendalert.shivangshah.friendalert.injection.component

import com.friendalert.shivangshah.friendalert.login.LoginActivity
import com.friendalert.shivangshah.friendalert.injection.module.HomeActivityModule
import dagger.Subcomponent
import dagger.android.AndroidInjector

/**
 * Created by shivangshah on 11/13/17.
 */
@Subcomponent(modules = arrayOf(HomeActivityModule::class))
interface LoginActivitySubComponent : AndroidInjector<LoginActivity> {

    @Subcomponent.Builder
    abstract class Builder : AndroidInjector.Builder<LoginActivity>()

}
package com.friendalert.shivangshah.friendalert.injection.component

import com.friendalert.shivangshah.friendalert.injection.module.SettingsActivityModule
import com.friendalert.shivangshah.friendalert.injection.module.SplashActivityModule
import com.friendalert.shivangshah.friendalert.login.SplashActivity
import com.friendalert.shivangshah.friendalert.settings.SettingsActivity
import dagger.Subcomponent
import dagger.android.AndroidInjector

/**
 * Created by shivangshah on 12/21/17.
 */
@Subcomponent(modules = arrayOf(SettingsActivityModule::class))
interface SettingsActivitySubComponent : AndroidInjector<SettingsActivity> {

    @Subcomponent.Builder
    abstract class Builder : AndroidInjector.Builder<SettingsActivity>()

}
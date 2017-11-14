package com.friendalert.shivangshah.friendalert.injection.module

import android.app.Activity
import com.friendalert.shivangshah.friendalert.HomeActivity
import com.friendalert.shivangshah.friendalert.TestActivity
import com.friendalert.shivangshah.friendalert.injection.component.HomeActivitySubComponent
import com.friendalert.shivangshah.friendalert.injection.component.TestActivitySubComponent
import com.friendalert.shivangshah.friendalert.injection.scopes.PerActivity
import com.friendalert.shivangshah.friendalert.injection.scopes.PerFragment
import com.friendalert.shivangshah.friendalert.notifications.NotificationsFragment
import dagger.Binds
import dagger.Module
import dagger.android.ActivityKey
import dagger.android.AndroidInjector
import dagger.android.ContributesAndroidInjector
import dagger.multibindings.IntoMap


// Add all activities in this module
@Module
abstract class ActivityBuilderModule {

    @PerActivity
    @ContributesAndroidInjector(modules = arrayOf(HomeActivityModule::class))
    abstract fun bindHomeActivity(): HomeActivity

    @PerActivity
    @ContributesAndroidInjector(modules = arrayOf(TestActivityModule::class))
    abstract fun bindTestActivity(): TestActivity


}
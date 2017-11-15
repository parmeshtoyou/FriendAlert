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

    @Binds
    @IntoMap
    @ActivityKey(HomeActivity::class)
    internal abstract fun bindHomeActivity(builder: HomeActivitySubComponent.Builder): AndroidInjector.Factory<out Activity>

    @Binds
    @IntoMap
    @ActivityKey(TestActivity::class)
    internal abstract fun bindTestActivity(builder: TestActivitySubComponent.Builder): AndroidInjector.Factory<out Activity>


}
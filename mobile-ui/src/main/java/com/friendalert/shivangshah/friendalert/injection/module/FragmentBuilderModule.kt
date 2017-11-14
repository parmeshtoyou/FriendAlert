package com.friendalert.shivangshah.friendalert.injection.module

import com.friendalert.shivangshah.friendalert.injection.scopes.PerActivity
import com.friendalert.shivangshah.friendalert.injection.scopes.PerFragment
import com.friendalert.shivangshah.friendalert.notifications.NotificationsFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

/**
 * Contains All the Fragments
 */
@Module
abstract class FragmentBuilderModule {

    @PerFragment
    @ContributesAndroidInjector(modules = arrayOf(NotificationsFragmentModule::class))
    abstract fun bindNotificationsFragment(): NotificationsFragment

}
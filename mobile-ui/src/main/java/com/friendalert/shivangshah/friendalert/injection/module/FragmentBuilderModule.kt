package com.friendalert.shivangshah.friendalert.injection.module

import com.friendalert.shivangshah.friendalert.broadcast.BroadcastFragment
import com.friendalert.shivangshah.friendalert.injection.scopes.PerActivity
import com.friendalert.shivangshah.friendalert.injection.scopes.PerFragment
import com.friendalert.shivangshah.friendalert.myplaces.MyPlacesFragment
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

    @PerFragment
    @ContributesAndroidInjector(modules = arrayOf(MyPlacesFragmentModule::class))
    abstract fun bindMyPlacesFragment(): MyPlacesFragment

    @PerFragment
    @ContributesAndroidInjector(modules = arrayOf(BroadcastFragmentModule::class))
    abstract fun bindBroadcastFragment(): BroadcastFragment

}
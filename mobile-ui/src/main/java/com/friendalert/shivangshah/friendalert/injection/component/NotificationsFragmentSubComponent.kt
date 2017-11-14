package com.friendalert.shivangshah.friendalert.injection.component

import com.friendalert.shivangshah.friendalert.injection.module.NotificationsFragmentModule
import com.friendalert.shivangshah.friendalert.notifications.NotificationsFragment
import dagger.Subcomponent
import dagger.android.AndroidInjector

@Subcomponent(modules = arrayOf(NotificationsFragmentModule::class))
interface NotificationsFragmentSubComponent : AndroidInjector<NotificationsFragment> {

    @Subcomponent.Builder
    abstract class Builder : AndroidInjector.Builder<NotificationsFragment>()

}
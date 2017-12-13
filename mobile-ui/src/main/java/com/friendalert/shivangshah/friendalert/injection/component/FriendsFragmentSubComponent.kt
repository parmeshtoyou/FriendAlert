package com.friendalert.shivangshah.friendalert.injection.component

import com.friendalert.shivangshah.friendalert.broadcast.BroadcastFragment
import com.friendalert.shivangshah.friendalert.friends.FriendsFragment
import com.friendalert.shivangshah.friendalert.injection.module.BroadcastFragmentModule
import com.friendalert.shivangshah.friendalert.injection.module.FriendsFragmentModule
import dagger.Subcomponent
import dagger.android.AndroidInjector

/**
 * Created by shivangshah on 12/12/17.
 */
@Subcomponent(modules = arrayOf(FriendsFragmentModule::class))
interface FriendsFragmentSubComponent : AndroidInjector<FriendsFragment> {

    @Subcomponent.Builder
    abstract class Builder : AndroidInjector.Builder<FriendsFragment>()

}
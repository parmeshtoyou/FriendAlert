package com.friendalert.shivangshah.friendalert.injection.component

import com.friendalert.shivangshah.friendalert.broadcast.BroadcastFragment
import com.friendalert.shivangshah.friendalert.injection.module.BroadcastFragmentModule
import dagger.Subcomponent
import dagger.android.AndroidInjector

/**
 * Created by shivangshah on 11/24/17.
 */
@Subcomponent(modules = arrayOf(BroadcastFragmentModule::class))
interface BroadcastFragmentSubComponent : AndroidInjector<BroadcastFragment> {

    @Subcomponent.Builder
    abstract class Builder : AndroidInjector.Builder<BroadcastFragment>()

}
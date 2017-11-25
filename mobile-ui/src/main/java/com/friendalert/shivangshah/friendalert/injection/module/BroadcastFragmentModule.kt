package com.friendalert.shivangshah.friendalert.injection.module

import com.friendalert.shivangshah.domain.broadcast.CreateBroadcast
import com.friendalert.shivangshah.friendalert.broadcast.BroadcastFragment
import com.friendalert.shivangshah.friendalert.injection.scopes.PerFragment
import com.friendalert.shivangshah.presentation.broadcast.BroadcastContract
import com.friendalert.shivangshah.presentation.broadcast.BroadcastMapper
import com.friendalert.shivangshah.presentation.broadcast.BroadcastPresenter
import dagger.Module
import dagger.Provides

/**
 * Created by shivangshah on 11/24/17.
 */
@Module
class BroadcastFragmentModule {

    @PerFragment
    @Provides
    internal fun provideBroadcastView(broadcastFragment: BroadcastFragment): BroadcastContract.View {
        return broadcastFragment
    }

    @PerFragment
    @Provides
    internal fun provideBroadcastPresenter(mainView: BroadcastContract.View, createBroadcast: CreateBroadcast, mapper: BroadcastMapper):
            BroadcastContract.Presenter {
        return BroadcastPresenter(mainView, createBroadcast, mapper)
    }

}
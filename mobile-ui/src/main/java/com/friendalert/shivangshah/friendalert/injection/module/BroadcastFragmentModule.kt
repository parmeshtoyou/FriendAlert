package com.friendalert.shivangshah.friendalert.injection.module

import com.friendalert.shivangshah.domain.notifications.GetNotifications
import com.friendalert.shivangshah.friendalert.broadcast.BroadcastFragment
import com.friendalert.shivangshah.friendalert.injection.scopes.PerFragment
import com.friendalert.shivangshah.friendalert.notifications.NotificationsFragment
import com.friendalert.shivangshah.presentation.notifications.NotificationMapper
import com.friendalert.shivangshah.presentation.notifications.NotificationsContract
import com.friendalert.shivangshah.presentation.notifications.NotificationsPresenter
import dagger.Module
import dagger.Provides

/**
 * Created by shivangshah on 11/24/17.
 */
@Module
class BroadcastFragmentModule {

//    @PerFragment
//    @Provides
//    internal fun provideBroadcastView(broadcastFragment: BroadcastFragment): BroadcastContract.View {
//        return broadcastFragment
//    }
//
//    @PerFragment
//    @Provides
//    internal fun provideBroadcastPresenter(mainView: BroadcastContract.View):
//            BroadcastContract.Presenter {
//        return BroadcastPresenter(mainView)
//    }

}
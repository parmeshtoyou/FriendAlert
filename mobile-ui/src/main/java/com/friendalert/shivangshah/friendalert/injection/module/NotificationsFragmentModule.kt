package com.friendalert.shivangshah.friendalert.injection.module

import android.app.Activity
import android.support.v4.app.Fragment
import com.friendalert.shivangshah.domain.interactor.notifications.GetNotifications
import com.friendalert.shivangshah.friendalert.HomeActivity
import com.friendalert.shivangshah.friendalert.injection.component.HomeActivitySubComponent
import com.friendalert.shivangshah.friendalert.injection.component.NotificationsFragmentSubComponent
import com.friendalert.shivangshah.friendalert.injection.scopes.PerActivity
import com.friendalert.shivangshah.friendalert.injection.scopes.PerFragment
import com.friendalert.shivangshah.friendalert.notifications.NotificationsFragment
import com.friendalert.shivangshah.presentation.mapper.NotificationMapper
import com.friendalert.shivangshah.presentation.notifications.NotificationsContract
import com.friendalert.shivangshah.presentation.notifications.NotificationsPresenter
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.android.ActivityKey
import dagger.android.AndroidInjector
import dagger.android.support.FragmentKey
import dagger.multibindings.IntoMap

/**
 * Created by shivangshah on 11/11/17.
 */
@Module
class NotificationsFragmentModule {

    @PerFragment
    @Provides
    internal fun provideNotificationView(notificationsFragment: NotificationsFragment): NotificationsContract.View {
        return notificationsFragment
    }

    @PerFragment
    @Provides
    internal fun provideNotificationsPresenter(mainView: NotificationsContract.View,
                                               getNotifications: GetNotifications, mapper: NotificationMapper):
            NotificationsContract.Presenter {
        return NotificationsPresenter(mainView, getNotifications, mapper)
    }

}
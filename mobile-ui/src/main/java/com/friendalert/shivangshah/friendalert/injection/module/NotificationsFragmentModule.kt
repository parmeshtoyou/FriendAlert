package com.friendalert.shivangshah.friendalert.injection.module

import com.friendalert.shivangshah.domain.notifications.GetNotifications
import com.friendalert.shivangshah.domain.notifications.MarkAsRead
import com.friendalert.shivangshah.friendalert.injection.scopes.PerFragment
import com.friendalert.shivangshah.friendalert.notifications.NotificationsFragment
import com.friendalert.shivangshah.presentation.notifications.NotificationPresentationModel
import com.friendalert.shivangshah.presentation.notifications.NotificationsContract
import com.friendalert.shivangshah.presentation.notifications.NotificationsPresenter
import dagger.Module
import dagger.Provides

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
                                               getNotifications: GetNotifications,
                                               markAsRead: MarkAsRead,
                                               presentationModel: NotificationPresentationModel):
            NotificationsContract.Presenter {
        return NotificationsPresenter(mainView, getNotifications, markAsRead, presentationModel)
    }

}
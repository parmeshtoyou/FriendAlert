package com.friendalert.shivangshah.friendalert.notifications

import com.friendalert.shivangshah.friendalert.Mapper
import com.friendalert.shivangshah.presentation.notifications.NotificationView
import javax.inject.Inject

open class NotificationMapper @Inject constructor(): Mapper<NotificationsViewModel, NotificationView> {

    /**
     * Map a [NotificationView] instance to a [NotificationsViewModel] instance
     */
    override fun mapToViewModel(type: NotificationView): NotificationsViewModel {
        return NotificationsViewModel(type.name, type.title, type.avatar)
    }

}

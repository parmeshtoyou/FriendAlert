package com.friendalert.shivangshah.friendalert.mapper

import com.friendalert.shivangshah.friendalert.model.NotificationsViewModel
import com.friendalert.shivangshah.presentation.model.NotificationView
import javax.inject.Inject

open class NotificationMapper @Inject constructor(): Mapper<NotificationsViewModel, NotificationView> {

    /**
     * Map a [NotificationView] instance to a [NotificationsViewModel] instance
     */
    override fun mapToViewModel(type: NotificationView): NotificationsViewModel {
        return NotificationsViewModel(type.name, type.title, type.avatar)
    }

}

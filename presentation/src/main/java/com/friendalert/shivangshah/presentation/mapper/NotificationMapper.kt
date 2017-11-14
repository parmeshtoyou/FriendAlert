package com.friendalert.shivangshah.presentation.mapper

import com.friendalert.shivangshah.domain.model.Notification
import com.friendalert.shivangshah.presentation.model.NotificationView
import javax.inject.Inject

/**
 * Map a [NotificationView] to and from a [Notification] instance when data is moving between
 * this layer and the Domain layer
 */
open class NotificationMapper @Inject constructor(): Mapper<NotificationView, Notification> {

    /**
     * Map a [Bufferoo] instance to a [BufferooView] instance
     */
    override fun mapToView(type: Notification): NotificationView {
        return NotificationView(type.name, type.title, type.avatar)
    }


}
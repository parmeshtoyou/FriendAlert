package com.friendalert.shivangshah.data.mapper

import com.friendalert.shivangshah.data.model.NotificationEntity
import com.friendalert.shivangshah.domain.model.Notification
import javax.inject.Inject

/**
 * Map a [NotificationEntity] to and from a [Notification] instance when data is moving between
 * this later and the Domain layer
 */
open class NotificationMapper @Inject constructor(): Mapper<NotificationEntity, Notification> {

    /**
     * Map a [NotificationEntity] instance to a [Notification] instance
     */
    override fun mapFromEntity(type: NotificationEntity): Notification {
        return Notification(type.name, type.title, type.avatar)
    }

    /**
     * Map a [Bufferoo] instance to a [BufferooEntity] instance
     */
    override fun mapToEntity(type: Notification): NotificationEntity {
        return NotificationEntity(type.name, type.title, type.avatar)
    }


}
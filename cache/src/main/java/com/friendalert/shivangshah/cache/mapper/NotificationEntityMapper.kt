package com.friendalert.shivangshah.cache.mapper

import com.friendalert.shivangshah.cache.model.CachedNotification
import com.friendalert.shivangshah.data.notifications.NotificationEntity
import javax.inject.Inject

/**
 * Map a [CachedBufferoo] instance to and from a [BufferooEntity] instance when data is moving between
 * this later and the Data layer
 */
class NotificationEntityMapper @Inject constructor(): EntityMapper<CachedNotification, NotificationEntity> {

    /**
     * Map a [BufferooEntity] instance to a [CachedBufferoo] instance
     */
    override fun mapToCached(type: NotificationEntity): CachedNotification {
        return CachedNotification(type.name, type.title, type.avatar)
    }

    /**
     * Map a [CachedBufferoo] instance to a [BufferooEntity] instance
     */
    override fun mapFromCached(type: CachedNotification): NotificationEntity {
        return NotificationEntity(type.name, type.title, type.avatar)
    }

}
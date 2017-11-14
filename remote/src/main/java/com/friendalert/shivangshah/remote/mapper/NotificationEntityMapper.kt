package com.friendalert.shivangshah.remote.mapper

import com.friendalert.shivangshah.data.model.NotificationEntity
import com.friendalert.shivangshah.remote.model.NotificationModel
import javax.inject.Inject

/**
 * Map a [BufferooModel] to and from a [BufferooEntity] instance when data is moving between
 * this later and the Data layer
 */
open class NotificationEntityMapper @Inject constructor(): EntityMapper<NotificationModel, NotificationEntity> {

    /**
     * Map an instance of a [BufferooModel] to a [BufferooEntity] model
     */
    override fun mapFromRemote(type: NotificationModel): NotificationEntity {
        return NotificationEntity(type.name, type.title, type.avatar)
    }

}
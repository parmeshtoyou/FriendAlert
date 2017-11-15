package com.friendalert.shivangshah.remote.notifications

import com.friendalert.shivangshah.data.notifications.NotificationEntity
import com.friendalert.shivangshah.data.notifications.repository.NotificationRemote
import io.reactivex.Single
import javax.inject.Inject

/**
 * Remote implementation for retrieving Bufferoo instances. This class implements the
 * [BufferooRemote] from the Data layer as it is that layers responsibility for defining the
 * operations in which data store implementation layers can carry out.
 */
class NotificationRemoteImpl @Inject constructor(private val notificationService: NotificationService,
                                                 private val entityMapper: NotificationEntityMapper) :
        NotificationRemote {

    override fun getNotifications(): Single<List<NotificationEntity>> {
        return notificationService.getNotifications()
                .map {
                    it.team.map { listItem ->
                        entityMapper.mapFromRemote(listItem)
                    }
                }
    }


}
package com.friendalert.shivangshah.remote.notifications

import com.friendalert.shivangshah.remote.DataFactory.Factory.randomUuid

/**
 * Factory class for Bufferoo related instances
 */
class NotificationFactory {

    companion object Factory {

        fun makeNotificationResponse(): NotificationService.NotificationResponse {
            val notificationResponse = NotificationService.NotificationResponse()
            notificationResponse.team = makeNotificationModelList(5)
            return notificationResponse
        }

        fun makeNotificationModelList(count: Int): List<NotificationModel> {
            val notificationEntities = mutableListOf<NotificationModel>()
            repeat(count) {
                notificationEntities.add(makeNotificationModel())
            }
            return notificationEntities
        }

        fun makeNotificationModel(): NotificationModel {
            return NotificationModel(randomUuid(), randomUuid(), randomUuid())
        }

    }

}
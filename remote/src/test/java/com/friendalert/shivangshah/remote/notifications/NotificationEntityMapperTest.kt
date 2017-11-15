package com.friendalert.shivangshah.remote.notifications

import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import kotlin.test.assertEquals

@RunWith(JUnit4::class)
class NotificationEntityMapperTest {

    private lateinit var notificationEntityMapper: NotificationEntityMapper

    @Before
    fun setUp() {
        notificationEntityMapper = NotificationEntityMapper()
    }

    @Test
    fun mapFromRemoteMapsData() {
        val notifcationModel = NotificationFactory.makeNotificationModel()
        val notificationEntity = notificationEntityMapper.mapFromRemote(notifcationModel)

        assertEquals(notifcationModel.name, notificationEntity.name)
        assertEquals(notifcationModel.title, notificationEntity.title)
        assertEquals(notifcationModel.avatar, notificationEntity.avatar)
    }

}
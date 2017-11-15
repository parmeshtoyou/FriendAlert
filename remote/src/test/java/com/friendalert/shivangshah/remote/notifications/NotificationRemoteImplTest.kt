package com.friendalert.shivangshah.remote.notifications

import com.friendalert.shivangshah.data.notifications.NotificationEntity
import com.nhaarman.mockito_kotlin.mock
import com.nhaarman.mockito_kotlin.whenever
import io.reactivex.Single
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4

@RunWith(JUnit4::class)
class NotificationRemoteImplTest {

    private lateinit var entityMapper: NotificationEntityMapper
    private lateinit var notificationService: NotificationService

    private lateinit var notificationRemoteImpl: NotificationRemoteImpl

    @Before
    fun setup() {
        entityMapper = mock()
        notificationService = mock()
        notificationRemoteImpl = NotificationRemoteImpl(notificationService, entityMapper)
    }

    //<editor-fold desc="Get Bufferoos">
    @Test
    fun getNotificationsCompletes() {
        stubNotificationServiceGetNotifications(Single.just(NotificationFactory.makeNotificationResponse()))
        val testObserver = notificationRemoteImpl.getNotifications().test()
        testObserver.assertComplete()
    }

    @Test
    fun getNotificationsReturnsData() {
        val notificationResponse = NotificationFactory.makeNotificationResponse()
        stubNotificationServiceGetNotifications(Single.just(notificationResponse))
        val notificationEntities = mutableListOf<NotificationEntity>()
        notificationResponse.team.forEach {
            notificationEntities.add(entityMapper.mapFromRemote(it))
        }

        val testObserver = notificationRemoteImpl.getNotifications().test()
        testObserver.assertValue(notificationEntities)
    }
    //</editor-fold>

    private fun stubNotificationServiceGetNotifications(single: Single<NotificationService.NotificationResponse>) {
        whenever(notificationService.getNotifications())
                .thenReturn(single)
    }
}
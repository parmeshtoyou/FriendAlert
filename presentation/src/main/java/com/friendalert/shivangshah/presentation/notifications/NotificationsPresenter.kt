package com.friendalert.shivangshah.presentation.notifications

import com.friendalert.shivangshah.domain.SingleUseCase
import com.friendalert.shivangshah.domain.notifications.Notification
import io.reactivex.observers.DisposableSingleObserver
import javax.inject.Inject

/**
 * Created by shivangshah on 11/11/17.
 */
class NotificationsPresenter @Inject constructor(val notificationsView: NotificationsContract.View,
                                                 val getNotificationsUseCase: SingleUseCase<List<Notification>, Void>,
                                                 val notificationMapper: NotificationMapper):
        NotificationsContract.Presenter {

    init {
        notificationsView.setPresenter(this)
    }

    override fun start() {
        retrieveNotifications()
    }

    override fun stop() {
        getNotificationsUseCase.dispose()
    }

    override fun retrieveNotifications() {
        getNotificationsUseCase.execute(NotificationSubscriber())
    }

    internal fun handleGetNotificationsSuccess(notifications: List<Notification>) {
        notificationsView.hideErrorState()
        if (notifications.isNotEmpty()) {
            notificationsView.hideEmptyState()
            notificationsView.showNotifications(notifications.map { notificationMapper.mapToView(it) })
        } else {
            notificationsView.hideNotifications()
            notificationsView.showEmptyState()
        }
    }

    inner class NotificationSubscriber: DisposableSingleObserver<List<Notification>>() {

        override fun onSuccess(t: List<Notification>) {
            handleGetNotificationsSuccess(t)
        }

        override fun onError(exception: Throwable) {
            notificationsView.hideNotifications()
            notificationsView.hideEmptyState()
            notificationsView.showErrorState()
        }

    }

}
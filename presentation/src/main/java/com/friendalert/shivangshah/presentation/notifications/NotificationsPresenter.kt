package com.friendalert.shivangshah.presentation.notifications

import com.friendalert.shivangshah.domain.SingleUseCase
import com.friendalert.shivangshah.model.notifications.response.NotificationModel
import com.friendalert.shivangshah.model.notifications.response.NotificationResponseModel
import io.reactivex.observers.DisposableSingleObserver
import javax.inject.Inject

/**
 * Created by shivangshah on 11/11/17.
 */
class NotificationsPresenter @Inject constructor(val notificationsView: NotificationsContract.View,
                                                 val getNotificationsUseCase: SingleUseCase<NotificationResponseModel, Void>):
        NotificationsContract.Presenter {

    init {
        notificationsView.setPresenter(this)
    }

    override fun start() {
        retrieveNotifications()
    }

    override fun retrieveNotifications() {
        getNotificationsUseCase.execute(NotificationSubscriber())
    }

    inner class NotificationSubscriber: DisposableSingleObserver<NotificationResponseModel>() {

        override fun onSuccess(t: NotificationResponseModel) {

        }

        override fun onError(exception: Throwable) {
            notificationsView.hideNotifications()
            notificationsView.hideEmptyState()
            notificationsView.showErrorState()
        }

    }

    override fun stop() {
        getNotificationsUseCase.dispose()
    }

}
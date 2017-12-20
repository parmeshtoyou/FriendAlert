package com.friendalert.shivangshah.presentation.notifications

import com.friendalert.shivangshah.domain.SingleUseCase
import com.friendalert.shivangshah.domain.notifications.MarkAsRead
import com.friendalert.shivangshah.model.notifications.response.NotificationModel
import com.friendalert.shivangshah.model.notifications.response.NotificationResponseModel
import com.friendalert.shivangshah.presentation.CustomResponseCodes
import com.friendalert.shivangshah.presentation.myplaces.MyPlacesPresentationModel
import io.reactivex.observers.DisposableSingleObserver
import javax.inject.Inject

/**
 * Created by shivangshah on 11/11/17.
 */
class NotificationsPresenter @Inject constructor(val notificationsView: NotificationsContract.View,
                                                 val getNotificationsUseCase: SingleUseCase<NotificationResponseModel, Void>,
                                                 val markAsRead: SingleUseCase<Boolean, String>,
                                                 val presentationModel: NotificationPresentationModel):
        NotificationsContract.Presenter {

    init {
        notificationsView.setPresenter(this)
    }

    override fun start() {
        retrieveNotifications()
    }

    override fun markAsRead(notification: NotificationModel) {

        presentationModel.setToBeMarkedAsRead(notification)

        markAsRead.execute(MarkAsReadSubscriber(), notification.broadcast_id)

    }

    inner class MarkAsReadSubscriber: DisposableSingleObserver<Boolean>() {

        override fun onSuccess(t: Boolean) {

            presentationModel.markNotificationAsRead()
            notificationsView.showNotifications(presentationModel.getNotifications()!!)

        }

        override fun onError(exception: Throwable) {

        }

    }

    override fun retrieveNotifications() {

        notificationsView.showProgress()
        getNotificationsUseCase.execute(GetNotificationsSubscriber())

    }

    inner class GetNotificationsSubscriber: DisposableSingleObserver<NotificationResponseModel>() {

        override fun onSuccess(t: NotificationResponseModel) {

            if(t.customCode == CustomResponseCodes.getSuccess){

                presentationModel.setNotifications(t.data)
                notificationsView.showNotifications(presentationModel.getNotifications()!!)
                notificationsView.hideProgress()
                notificationsView.showSuccess()

            }else{

                notificationsView.hideProgress()
                //notificationsView.showFailure()

            }
        }

        override fun onError(exception: Throwable) {

            notificationsView.hideProgress()
            notificationsView.showFailure(presentationModel.getNotifications() == null)

        }

    }

    override fun stop() {
        getNotificationsUseCase.dispose()
    }

}
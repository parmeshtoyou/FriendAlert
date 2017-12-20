package com.friendalert.shivangshah.presentation.notifications

import com.friendalert.shivangshah.model.notifications.response.NotificationModel
import com.friendalert.shivangshah.presentation.BasePresenter
import com.friendalert.shivangshah.presentation.BaseView

/**
 * Created by shivangshah on 11/11/17.
 */
interface NotificationsContract {

    interface View : BaseView<Presenter> {

        fun showNotifications(notifications: List<NotificationModel>)

        fun showSuccess()

        fun showFailure(firstTime: Boolean)

        fun showProgress()

        fun hideProgress()

    }

    interface Presenter : BasePresenter {

        fun retrieveNotifications()

        fun markAsRead(notification: NotificationModel)

    }

}
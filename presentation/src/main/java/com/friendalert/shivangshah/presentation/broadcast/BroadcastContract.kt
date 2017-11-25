package com.friendalert.shivangshah.presentation.broadcast

import com.friendalert.shivangshah.presentation.BasePresenter
import com.friendalert.shivangshah.presentation.BaseView
import com.friendalert.shivangshah.presentation.notifications.NotificationView
import com.friendalert.shivangshah.presentation.notifications.NotificationsContract

/**
 * Created by shivangshah on 11/25/17.
 */
interface BroadcastContract {

    interface View : BaseView<BroadcastContract.Presenter> {

        fun showSuccess()

        fun showFailure()

    }

    interface Presenter : BasePresenter {

        fun createBroadcast(userId: String, message: String, latitude: String, longitude: String)

    }

}
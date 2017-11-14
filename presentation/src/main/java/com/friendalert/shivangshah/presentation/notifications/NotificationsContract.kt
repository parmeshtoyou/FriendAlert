package com.friendalert.shivangshah.presentation.notifications

import com.friendalert.shivangshah.presentation.BasePresenter
import com.friendalert.shivangshah.presentation.BaseView
import com.friendalert.shivangshah.presentation.model.NotificationView

/**
 * Created by shivangshah on 11/11/17.
 */
interface NotificationsContract {

    interface View : BaseView<Presenter> {

        fun showProgress()

        fun hideProgress()

        fun showNotifications(notifications: List<NotificationView>)

        fun hideNotifications()

        fun showErrorState()

        fun hideErrorState()

        fun showEmptyState()

        fun hideEmptyState()

    }

    interface Presenter : BasePresenter {

        fun retrieveNotifications()

    }

}
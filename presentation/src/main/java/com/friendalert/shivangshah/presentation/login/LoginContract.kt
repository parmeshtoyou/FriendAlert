package com.friendalert.shivangshah.presentation.login

import com.friendalert.shivangshah.presentation.BasePresenter
import com.friendalert.shivangshah.presentation.BaseView

/**
 * Created by shivangshah on 11/16/17.
 */
interface LoginContract {

    interface View : BaseView<LoginContract.Presenter> {

        fun showProgress()

        fun hideProgress()

        fun loginUser()

        fun showErrorState()

        fun hideErrorState()

    }

    interface Presenter : BasePresenter {

        fun loginUser(id: String,
                      firstName: String,
                      lastName: String,
                      phoneNumber: String,
                      newPushNotification: String,
                      oldPushNotification: String,
                      active: Int)

    }

}

package com.friendalert.shivangshah.presentation.splash

import com.friendalert.shivangshah.presentation.BasePresenter
import com.friendalert.shivangshah.presentation.BaseView

/**
 * Created by shivangshah on 11/16/17.
 */
interface SplashContract {

    interface View : BaseView<Presenter> {

        fun goToHomeScreen()

        fun goToLoginScreen()

    }

    interface Presenter : BasePresenter {

        fun getUser()

    }

}
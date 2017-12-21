package com.friendalert.shivangshah.presentation.settings

import com.friendalert.shivangshah.presentation.BasePresenter
import com.friendalert.shivangshah.presentation.BaseView

/**
 * Created by shivangshah on 12/21/17.
 */
interface SettingsContract {

    interface View : BaseView<Presenter> {

        fun logout()

        fun goToLoginScreen()

    }

    interface Presenter : BasePresenter {

        fun logout()

    }

}
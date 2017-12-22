package com.friendalert.shivangshah.presentation.broadcast

import com.friendalert.shivangshah.presentation.BasePresenter
import com.friendalert.shivangshah.presentation.BaseView

/**
 * Created by shivangshah on 11/25/17.
 */
interface BroadcastContract {

    interface View : BaseView<BroadcastContract.Presenter> {



    }

    interface Presenter : BasePresenter {

        fun createBroadcast(userId: String, message: String, latitude: String, longitude: String, location: String)

    }

}
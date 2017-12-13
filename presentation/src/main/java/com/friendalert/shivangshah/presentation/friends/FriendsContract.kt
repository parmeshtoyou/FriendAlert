package com.friendalert.shivangshah.presentation.friends

import com.friendalert.shivangshah.presentation.BasePresenter
import com.friendalert.shivangshah.presentation.BaseView
import com.friendalert.shivangshah.presentation.myplaces.MyPlaceViewData
import com.friendalert.shivangshah.presentation.myplaces.MyPlacesContract

/**
 * Created by shivangshah on 12/12/17.
 */
interface FriendsContract {

    interface View : BaseView<FriendsContract.Presenter> {



    }

    interface Presenter : BasePresenter {

        fun getFriends()

    }

}
package com.friendalert.shivangshah.domain.myplaces

import com.friendalert.shivangshah.domain.notifications.Notification
import io.reactivex.Single

/**
 * Created by shivangshah on 11/15/17.
 */
interface MyPlaceRepository {

    fun getMyPlaces(): Single<List<MyPlace>>

}
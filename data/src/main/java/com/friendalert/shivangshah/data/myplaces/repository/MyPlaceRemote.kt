package com.friendalert.shivangshah.data.myplaces.repository

import com.friendalert.shivangshah.data.myplaces.MyPlacesResponseEntity
import io.reactivex.Single

/**
 * Created by shivangshah on 11/15/17.
 */
interface MyPlaceRemote {

    /**
     * Retrieve a list of MyPlaces, from the service
     */
    fun getMyPlaces(userId : String): Single<MyPlacesResponseEntity>

}
package com.friendalert.shivangshah.domain.myplaces

import io.reactivex.Single

/**
 * Created by shivangshah on 11/15/17.
 */
interface MyPlaceRepository {

    fun getMyPlaces(userId: String): Single<MyPlaces>

    fun createMyPlace(myPlace: MyPlace) : Single<MyPlaceResponse>

    fun deleteMyPlace(myPlaceId: Int) : Single<MyPlaceResponse>

}
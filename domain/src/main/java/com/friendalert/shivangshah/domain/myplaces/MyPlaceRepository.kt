package com.friendalert.shivangshah.domain.myplaces

import com.friendalert.shivangshah.model.myplaces.request.MyPlaceRequestModel
import com.friendalert.shivangshah.model.myplaces.response.MyPlaceResponseModel
import com.friendalert.shivangshah.model.myplaces.response.MyPlacesResponseModel
import io.reactivex.Single

/**
 * Created by shivangshah on 11/15/17.
 */
interface MyPlaceRepository {

    fun getMyPlaces(userId: String): Single<MyPlacesResponseModel>

    fun createMyPlace(myPlace: MyPlaceRequestModel) : Single<MyPlaceResponseModel>

    fun deleteMyPlace(myPlaceId: Int) : Single<MyPlaceResponseModel>

    fun editMyPlace(myPlace: MyPlaceRequestModel) : Single<MyPlaceResponseModel>

}
package com.friendalert.shivangshah.remote.myplaces

import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Path

/**
 * service interface used by retrofit to call api
 */
interface MyPlaceService {

    @GET("{id}")
    fun getMyPlaces(@Path("id") userId : String): Single<MyPlacesResponseModel>

}
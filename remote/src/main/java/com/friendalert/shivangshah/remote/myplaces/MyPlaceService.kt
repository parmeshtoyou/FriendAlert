package com.friendalert.shivangshah.remote.myplaces

import io.reactivex.Single
import retrofit2.http.GET

/**
 * service interface used by retrofit to call api
 */
interface MyPlaceService {

    @GET("team.json")
    fun getMyPlaces(): Single<MyPlaceResponse>

    class MyPlaceResponse {
        lateinit var team: List<MyPlaceModel>
    }

}
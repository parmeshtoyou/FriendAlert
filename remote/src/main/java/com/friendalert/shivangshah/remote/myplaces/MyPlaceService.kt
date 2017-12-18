package com.friendalert.shivangshah.remote.myplaces

import com.friendalert.shivangshah.model.myplaces.request.MyPlaceRequestModel
import com.friendalert.shivangshah.model.myplaces.response.MyPlaceResponseModel
import com.friendalert.shivangshah.model.myplaces.response.MyPlacesResponseModel
import io.reactivex.Single
import retrofit2.http.*

/**
 * service interface used by retrofit to call api
 */
interface MyPlaceService {

    @GET("basecamps/{id}")
    fun getMyPlaces(@Path("id") userId : String): Single<MyPlacesResponseModel>

    @POST("basecamps")
    fun createMyPlace(@Body myPlaceRequestModel: MyPlaceRequestModel) : Single<MyPlaceResponseModel>

    @DELETE("basecamps/{id}")
    fun deleteMyPlace(@Path("id") myPlaceId: Int) : Single<MyPlaceResponseModel>

    @PUT("basecamps")
    fun editMyPlace(@Body myPlaceRequestModel: MyPlaceRequestModel) : Single<MyPlaceResponseModel>
}
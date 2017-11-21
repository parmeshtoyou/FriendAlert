package com.friendalert.shivangshah.remote.myplaces

import com.friendalert.shivangshah.data.myplaces.MyPlacesResponseEntity
import com.friendalert.shivangshah.data.myplaces.repository.MyPlaceRemote
import io.reactivex.Single
import javax.inject.Inject

/**
 * Created by shivangshah on 11/15/17.
 */
class MyPlaceRemoteImpl @Inject constructor(private val myPlaceService: MyPlaceService,
                                            private val entityMapper: MyPlaceEntityMapper) :
        MyPlaceRemote {
    // this method gets my places from service, converts it to MyPlaceEntity (data layer) and returns it to data layer
    override fun getMyPlaces(userId:String): Single<MyPlacesResponseEntity> {
        return myPlaceService.getMyPlaces(userId)
                .map { response: MyPlacesResponseModel ->  entityMapper.mapFromRemote(response)
                }
    }
}
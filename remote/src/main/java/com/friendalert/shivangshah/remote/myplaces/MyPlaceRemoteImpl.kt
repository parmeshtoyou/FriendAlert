package com.friendalert.shivangshah.remote.myplaces

import com.friendalert.shivangshah.data.myplaces.MyPlaceEntity
import com.friendalert.shivangshah.data.myplaces.MyPlaceResponseEntity
import com.friendalert.shivangshah.data.myplaces.MyPlaceResponseEntityMapper
import com.friendalert.shivangshah.data.myplaces.MyPlacesResponseEntity
import com.friendalert.shivangshah.data.myplaces.repository.MyPlaceRemote
import io.reactivex.Single
import javax.inject.Inject

/**
 * Created by shivangshah on 11/15/17.
 */
class MyPlaceRemoteImpl @Inject constructor(private val myPlaceService: MyPlaceService,
                                            private val entityMapper: MyPlacesEntityMapper,
                                            private val myPlaceEntityMapper: MyPlaceEntityMapper,
                                            private val responseModelEntityMapper: MyPlaceResponseModelMapper) :
        MyPlaceRemote {

    override fun createMyPlace(myPlace: MyPlaceEntity): Single<MyPlaceResponseEntity> {
        return myPlaceService.createMyPlace(myPlaceEntityMapper.mapFromEntity(myPlace)).map {
            responseModel: MyPlaceResponseModel ->  responseModelEntityMapper.mapFromRemote(responseModel)
        }
    }

    override fun deleteMyPlace(myPlaceId: Int): Single<MyPlaceResponseEntity> {
        return myPlaceService.deleteMyPlace(myPlaceId).map {
            responseModel: MyPlaceResponseModel ->  responseModelEntityMapper.mapFromRemote(responseModel)
        }
    }

    // this method gets my places from service, converts it to MyPlaceEntity (data layer) and returns it to data layer
    override fun getMyPlaces(userId:String): Single<MyPlacesResponseEntity> {
        return myPlaceService.getMyPlaces(userId).map {
            response: MyPlacesResponseModel ->  entityMapper.mapFromRemote(response)
        }
    }
}
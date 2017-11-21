package com.friendalert.shivangshah.remote.myplaces

import com.friendalert.shivangshah.data.myplaces.MyPlacesResponseEntity
import com.friendalert.shivangshah.data.myplaces.MyPlacesResponseEntityData
import com.friendalert.shivangshah.remote.EntityMapper
import javax.inject.Inject

/**
 * Created by shivangshah on 11/15/17.
 */
open class MyPlaceEntityMapper @Inject constructor(): EntityMapper<MyPlacesResponseModel, MyPlacesResponseEntity> {

    /**
     * Map an instance of a [MyPlaceRequestModel] to a [MyPlacesResponseEntity] model
     * remote -> data layer
     */
    override fun mapFromRemote(type: MyPlacesResponseModel): MyPlacesResponseEntity {

        var myPlaces : ArrayList<MyPlacesResponseEntityData> = ArrayList()

        for (myPlace in type.myPlaces){
            var myPlaceEntity = MyPlacesResponseEntityData(
                    myPlace.base_camp_id,myPlace.fk_user_id, myPlace.nickname, myPlace.address, myPlace.city, myPlace.state,
                    myPlace.latitude, myPlace.longitude, myPlace.active
            )

            myPlaces.add(myPlaceEntity)
        }

        return MyPlacesResponseEntity(type.customCode, myPlaces)
    }

}
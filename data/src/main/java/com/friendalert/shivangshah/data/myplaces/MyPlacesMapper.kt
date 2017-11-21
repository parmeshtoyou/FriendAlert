package com.friendalert.shivangshah.data.myplaces

import com.friendalert.shivangshah.data.Mapper
import com.friendalert.shivangshah.domain.myplaces.MyPlaces
import com.friendalert.shivangshah.domain.myplaces.MyPlacesData
import javax.inject.Inject

/**
 * Mapper
 * MyPlaceEntity (data) -> MyPlace (domain)
 * MyPlace (domain) -> MyPlaceEntity (data)
 */
open class MyPlacesMapper @Inject constructor(): Mapper<MyPlacesResponseEntity, MyPlaces> {

    override fun mapFromEntity(type: MyPlacesResponseEntity): MyPlaces {
        var myPlaces : ArrayList<MyPlacesData> = ArrayList()

        for (myPlace in type.myPlaces){
            var myPlaceObj = MyPlacesData(
                    myPlace.base_camp_id,myPlace.fk_user_id, myPlace.nickname, myPlace.address, myPlace.city, myPlace.state,
                    myPlace.latitude, myPlace.longitude, myPlace.active
            )

            myPlaces.add(myPlaceObj)
        }

        return MyPlaces(type.customCode, myPlaces)
    }

    override fun mapToEntity(type: MyPlaces): MyPlacesResponseEntity {
        return MyPlacesResponseEntity(0, ArrayList<MyPlacesResponseEntityData>())    }

}
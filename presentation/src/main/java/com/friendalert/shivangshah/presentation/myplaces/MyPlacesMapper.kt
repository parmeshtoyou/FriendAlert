package com.friendalert.shivangshah.presentation.myplaces

import com.friendalert.shivangshah.domain.myplaces.MyPlaces
import com.friendalert.shivangshah.presentation.Mapper
import javax.inject.Inject

/**
 * Created by shivangshah on 11/15/17.
 */
class MyPlacesMapper @Inject constructor(): Mapper<MyPlaceView, MyPlaces> {

    /**
     * Map a [Bufferoo] instance to a [BufferooView] instance
     */
    override fun mapToView(type: MyPlaces): MyPlaceView {
        var myPlaces : ArrayList<MyPlaceViewData> = ArrayList()

        for (myPlace in type.myPlaces){
            var myPlaceViewData = MyPlaceViewData(
                    myPlace.base_camp_id,myPlace.fk_user_id, myPlace.nickname, myPlace.address, myPlace.city, myPlace.state,
                    myPlace.latitude, myPlace.longitude, myPlace.active
            )

            myPlaces.add(myPlaceViewData)
        }

        return MyPlaceView(myPlaces)
    }

}
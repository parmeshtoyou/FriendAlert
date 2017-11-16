package com.friendalert.shivangshah.presentation.myplaces

import com.friendalert.shivangshah.domain.myplaces.MyPlace
import com.friendalert.shivangshah.presentation.Mapper
import javax.inject.Inject

/**
 * Created by shivangshah on 11/15/17.
 */
class MyPlaceMapper @Inject constructor(): Mapper<MyPlaceView, MyPlace> {

    /**
     * Map a [Bufferoo] instance to a [BufferooView] instance
     */
    override fun mapToView(type: MyPlace): MyPlaceView {
        return MyPlaceView(type.baseCampId, type.userId, type.nickname,
                type.address, type.city, type.state,
                type.latitude, type.longitude, type.active)
    }

}
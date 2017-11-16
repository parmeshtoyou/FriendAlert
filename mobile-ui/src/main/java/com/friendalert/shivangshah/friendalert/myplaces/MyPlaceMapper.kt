package com.friendalert.shivangshah.friendalert.myplaces

import com.friendalert.shivangshah.friendalert.Mapper
import com.friendalert.shivangshah.presentation.myplaces.MyPlaceView
import javax.inject.Inject

/**
 * Created by shivangshah on 11/15/17.
 */
open class MyPlaceMapper @Inject constructor(): Mapper<MyPlaceViewModel, MyPlaceView> {

    /**
     * Map a [MyPlaceView] instance to a [MyPlaceViewModel] instance
     */
    override fun mapToViewModel(type: MyPlaceView): MyPlaceViewModel {
        return MyPlaceViewModel(type.baseCampId, type.userId, type.nickname,
                type.address, type.city, type.state,
                type.latitude, type.longitude, type.active)
    }

}

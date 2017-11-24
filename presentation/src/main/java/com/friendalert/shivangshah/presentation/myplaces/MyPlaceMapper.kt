package com.friendalert.shivangshah.presentation.myplaces

import com.friendalert.shivangshah.domain.myplaces.MyPlace
import com.friendalert.shivangshah.presentation.Mapper
import javax.inject.Inject

/**
 * Created by shivangshah on 11/22/17.
 */
class MyPlaceMapper @Inject constructor(): Mapper<MyPlaceViewData, MyPlace> {

    fun mapFromView(type: MyPlaceViewData): MyPlace {
        return MyPlace(type.base_camp_id,type.fk_user_id,type.nickname,type.address,type.city,type.state,type.latitude,type.longitude,type.active)
    }

    override fun mapToView(type: MyPlace): MyPlaceViewData {
        return MyPlaceViewData(type.base_camp_id,type.fk_user_id,type.nickname,type.address,type.city,type.state,type.latitude,type.longitude,type.active)
    }

}
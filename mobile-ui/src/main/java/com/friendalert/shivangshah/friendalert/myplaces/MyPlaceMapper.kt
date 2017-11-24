package com.friendalert.shivangshah.friendalert.myplaces

import com.friendalert.shivangshah.friendalert.Mapper
import com.friendalert.shivangshah.presentation.myplaces.MyPlaceViewData
import javax.inject.Inject

/**
 * Created by shivangshah on 11/24/17.
 */
class MyPlaceMapper @Inject constructor(): Mapper<MyPlaceViewModelData, MyPlaceViewData> {

    override fun mapToViewModel(type: MyPlaceViewData): MyPlaceViewModelData {
        return MyPlaceViewModelData(type.base_camp_id,type.fk_user_id,type.nickname,type.address,type.city,type.state,type.latitude,type.longitude,type.active)
    }

    fun mapFromViewModel(type: MyPlaceViewModelData): MyPlaceViewData {
        return MyPlaceViewData(type.base_camp_id,type.fk_user_id,type.nickname,type.address,type.city,type.state,type.latitude,type.longitude,type.active)
    }

}
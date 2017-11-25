package com.friendalert.shivangshah.remote.myplaces

import com.friendalert.shivangshah.data.Mapper
import com.friendalert.shivangshah.data.myplaces.MyPlaceEntity
import javax.inject.Inject

/**
 * Created by shivangshah on 11/21/17.
 */
class MyPlaceEntityMapper @Inject constructor(): Mapper<MyPlaceEntity, MyPlaceRequestModel> {

    override fun mapFromEntity(type: MyPlaceEntity): MyPlaceRequestModel {
        return MyPlaceRequestModel(type.base_camp_id,type.fk_user_id,type.nickname,type.address,type.city,type.state,type.latitude,type.longitude,type.active)
    }

    override fun mapToEntity(type: MyPlaceRequestModel): MyPlaceEntity {
        return MyPlaceEntity(type.base_camp_id,type.fk_user_id,type.nickname,type.address,type.city,type.state,type.latitude,type.longitude,type.active)
    }

}
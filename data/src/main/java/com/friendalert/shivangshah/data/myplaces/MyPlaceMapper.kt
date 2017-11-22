package com.friendalert.shivangshah.data.myplaces

import com.friendalert.shivangshah.data.Mapper
import com.friendalert.shivangshah.data.user.UserResponseModelEntity
import com.friendalert.shivangshah.domain.myplaces.MyPlace
import com.friendalert.shivangshah.domain.user.UserResponse
import javax.inject.Inject

/**
 * Created by shivangshah on 11/21/17.
 */
class MyPlaceMapper @Inject constructor(): Mapper<MyPlaceEntity, MyPlace>{

    override fun mapFromEntity(type: MyPlaceEntity): MyPlace {
        return MyPlace(type.base_camp_id,type.fk_user_id,type.nickname,type.address,type.city,type.state,type.latitude,type.longitude,type.active)
    }

    override fun mapToEntity(type: MyPlace): MyPlaceEntity {
        return MyPlaceEntity(type.base_camp_id,type.fk_user_id,type.nickname,type.address,type.city,type.state,type.latitude,type.longitude,type.active)
    }

}
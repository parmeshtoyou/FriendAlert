package com.friendalert.shivangshah.data.myplaces

import com.friendalert.shivangshah.data.Mapper
import com.friendalert.shivangshah.domain.myplaces.MyPlace
import javax.inject.Inject

/**
 * Mapper
 * MyPlaceEntity (data) -> MyPlace (domain)
 * MyPlace (domain) -> MyPlaceEntity (data)
 */
open class MyPlaceMapper @Inject constructor(): Mapper<MyPlaceEntity, MyPlace> {

    override fun mapFromEntity(type: MyPlaceEntity): MyPlace {
        return MyPlace(type.baseCampId, type.userId, type.nickname,
                type.address, type.city, type.state,
                type.latitude, type.longitude, type.active)
    }

    override fun mapToEntity(type: MyPlace): MyPlaceEntity {
        return MyPlaceEntity(type.baseCampId, type.userId, type.nickname,
                type.address, type.city, type.state,
                type.latitude, type.longitude, type.active)    }

}
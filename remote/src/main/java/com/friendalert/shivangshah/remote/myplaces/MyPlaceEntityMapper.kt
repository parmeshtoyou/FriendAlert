package com.friendalert.shivangshah.remote.myplaces

import com.friendalert.shivangshah.data.myplaces.MyPlaceEntity
import com.friendalert.shivangshah.remote.EntityMapper
import javax.inject.Inject

/**
 * Created by shivangshah on 11/15/17.
 */
open class MyPlaceEntityMapper @Inject constructor(): EntityMapper<MyPlaceModel, MyPlaceEntity> {

    /**
     * Map an instance of a [MyPlaceModel] to a [MyPlaceEntity] model
     * remote -> data layer
     */
    override fun mapFromRemote(type: MyPlaceModel): MyPlaceEntity {
        return MyPlaceEntity(type.base_camp_id, type.fk_user_id, type.nickname,
                                type.address, type.city, type.state,
                                    type.latitude, type.longitude, type.active)
    }

}
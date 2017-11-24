package com.friendalert.shivangshah.remote.myplaces

import com.friendalert.shivangshah.data.myplaces.MyPlaceResponseEntity
import com.friendalert.shivangshah.data.myplaces.MyPlaceResponseEntityData
import com.friendalert.shivangshah.remote.EntityMapper
import javax.inject.Inject

/**
 * Created by shivangshah on 11/21/17.
 */
class MyPlaceResponseModelMapper @Inject constructor(): EntityMapper<MyPlaceResponseModel, MyPlaceResponseEntity> {

    override fun mapFromRemote(type: MyPlaceResponseModel): MyPlaceResponseEntity {
        if(type.data == null){
            return MyPlaceResponseEntity(type.customCode, MyPlaceResponseEntityData(0))
        }else{
            return MyPlaceResponseEntity(type.customCode, MyPlaceResponseEntityData(type.data?.insertId))
        }
    }


}
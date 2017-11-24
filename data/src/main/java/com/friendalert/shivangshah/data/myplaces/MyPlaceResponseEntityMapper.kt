package com.friendalert.shivangshah.data.myplaces

import com.friendalert.shivangshah.data.Mapper
import com.friendalert.shivangshah.domain.myplaces.MyPlaceResponse
import com.friendalert.shivangshah.domain.myplaces.MyPlaceResponseData
import javax.inject.Inject

/**
 * Created by shivangshah on 11/21/17.
 */
class MyPlaceResponseEntityMapper @Inject constructor(): Mapper<MyPlaceResponseEntity, MyPlaceResponse> {

    override fun mapFromEntity(type: MyPlaceResponseEntity): MyPlaceResponse {
        return MyPlaceResponse(type.customCode, MyPlaceResponseData(type.data?.insertId))
    }

    override fun mapToEntity(type: MyPlaceResponse): MyPlaceResponseEntity {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }


}
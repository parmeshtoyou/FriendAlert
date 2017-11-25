package com.friendalert.shivangshah.data.broadcast.source

import com.friendalert.shivangshah.data.broadcast.BroadcastEntity
import com.friendalert.shivangshah.data.broadcast.CreateBroadcastResponseEntity
import com.friendalert.shivangshah.data.broadcast.repository.BroadcastDataStore
import com.friendalert.shivangshah.data.broadcast.repository.BroadcastRemote
import com.friendalert.shivangshah.data.myplaces.repository.MyPlaceDataStore
import com.friendalert.shivangshah.data.myplaces.repository.MyPlaceRemote
import io.reactivex.Single
import javax.inject.Inject

/**
 * Created by shivangshah on 11/25/17.
 */
class BroadcastRemoteDataStore @Inject constructor(private val broadcastRemote: BroadcastRemote) :
        BroadcastDataStore {


    override fun createBroadcast(broadcast: BroadcastEntity): Single<CreateBroadcastResponseEntity> {
        return broadcastRemote.createBroadcast(broadcast)
    }

}
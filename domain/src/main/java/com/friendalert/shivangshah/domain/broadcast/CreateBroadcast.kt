package com.friendalert.shivangshah.domain.broadcast

import com.friendalert.shivangshah.domain.PostExecutionThread
import com.friendalert.shivangshah.domain.SingleUseCase
import com.friendalert.shivangshah.domain.ThreadExecutor
import com.friendalert.shivangshah.domain.myplaces.MyPlace
import com.friendalert.shivangshah.domain.myplaces.MyPlaceRepository
import com.friendalert.shivangshah.domain.myplaces.MyPlaceResponse
import com.friendalert.shivangshah.domain.user.User
import com.friendalert.shivangshah.domain.user.UserRepository
import io.reactivex.Single
import javax.inject.Inject

/**
 * Created by shivangshah on 11/25/17.
 */
class CreateBroadcast @Inject constructor(val broadcastRepository: BroadcastRepository,
                                        val userRepository: UserRepository,
                                        threadExecutor: ThreadExecutor,
                                        postExecutionThread: PostExecutionThread):
        SingleUseCase<CreateBroadcastResponse, Broadcast>(threadExecutor, postExecutionThread) {

    override fun buildUseCaseObservable(broadcast: Broadcast?): Single<CreateBroadcastResponse> {

        var localBroadcast : Broadcast;

        return userRepository.getUser().flatMap{
            user: User ->
            localBroadcast = Broadcast(user.userId, broadcast!!.message, broadcast!!.latitude, broadcast!!.longitude)
            broadcastRepository.createBroadcast(localBroadcast)
        }
    }

}
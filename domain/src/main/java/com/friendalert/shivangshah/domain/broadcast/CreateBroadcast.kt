package com.friendalert.shivangshah.domain.broadcast

import com.friendalert.shivangshah.domain.PostExecutionThread
import com.friendalert.shivangshah.domain.SingleUseCase
import com.friendalert.shivangshah.domain.ThreadExecutor
import com.friendalert.shivangshah.domain.user.User
import com.friendalert.shivangshah.domain.user.UserRepository
import com.friendalert.shivangshah.model.broadcast.request.BroadcastRequestModel
import com.friendalert.shivangshah.model.broadcast.response.BroadcastResponseModel
import io.reactivex.Single
import javax.inject.Inject

/**
 * Created by shivangshah on 11/25/17.
 */
class CreateBroadcast @Inject constructor(val broadcastRepository: BroadcastRepository,
                                        val userRepository: UserRepository,
                                        threadExecutor: ThreadExecutor,
                                        postExecutionThread: PostExecutionThread):
        SingleUseCase<BroadcastResponseModel, BroadcastRequestModel>(threadExecutor, postExecutionThread) {

    override fun buildUseCaseObservable(broadcast: BroadcastRequestModel?): Single<BroadcastResponseModel> {

        var localBroadcast : BroadcastRequestModel;

        return userRepository.getUser().flatMap{
            user: User ->
            localBroadcast = BroadcastRequestModel(user.userId, broadcast!!.message, broadcast!!.latitude, broadcast!!.longitude, broadcast!!.location)
            broadcastRepository.createBroadcast(localBroadcast)
        }
    }

}
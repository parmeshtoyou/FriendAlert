package com.friendalert.shivangshah.domain.friends

import com.friendalert.shivangshah.domain.PostExecutionThread
import com.friendalert.shivangshah.domain.SingleUseCase
import com.friendalert.shivangshah.domain.ThreadExecutor
import com.friendalert.shivangshah.model.friends.request.UpdateFriendRequestModel
import com.friendalert.shivangshah.model.friends.response.UpdateFriendResponseModel
import io.reactivex.Single
import javax.inject.Inject

/**
 * Created by shivangshah on 12/13/17.
 */
class UpdateFriend @Inject constructor(val friendsRepository: FriendsRepository,
                                       threadExecutor: ThreadExecutor,
                                       postExecutionThread: PostExecutionThread):
        SingleUseCase<UpdateFriendResponseModel, UpdateFriendRequestModel>(threadExecutor, postExecutionThread) {

    public override fun buildUseCaseObservable(requestModel: UpdateFriendRequestModel?): Single<UpdateFriendResponseModel> {

        return friendsRepository.updateFriend(requestModel!!.requestId, requestModel!!.status)

    }
}
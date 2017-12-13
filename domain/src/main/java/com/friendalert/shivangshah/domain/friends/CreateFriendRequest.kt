package com.friendalert.shivangshah.domain.friends

import com.friendalert.shivangshah.domain.PostExecutionThread
import com.friendalert.shivangshah.domain.SingleUseCase
import com.friendalert.shivangshah.domain.ThreadExecutor
import com.friendalert.shivangshah.domain.user.User
import com.friendalert.shivangshah.domain.user.UserRepository
import com.friendalert.shivangshah.model.friends.response.CreateFriendRequestResponseModel
import io.reactivex.Single
import javax.inject.Inject

/**
 * Created by shivangshah on 12/13/17.
 */
class CreateFriendRequest @Inject constructor(val friendsRepository: FriendsRepository,
                                              val userRepository: UserRepository,
                                              threadExecutor: ThreadExecutor,
                                              postExecutionThread: PostExecutionThread):
        SingleUseCase<CreateFriendRequestResponseModel, String>(threadExecutor, postExecutionThread) {

    public override fun buildUseCaseObservable(receiverId: String?): Single<CreateFriendRequestResponseModel> {

        return userRepository.getUser().flatMap {
            user:User -> friendsRepository.createFriendRequest(user.userId, receiverId!!)
        }
    }
}
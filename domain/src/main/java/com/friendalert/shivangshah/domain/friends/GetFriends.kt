package com.friendalert.shivangshah.domain.friends

import com.friendalert.shivangshah.domain.PostExecutionThread
import com.friendalert.shivangshah.domain.SingleUseCase
import com.friendalert.shivangshah.domain.ThreadExecutor
import com.friendalert.shivangshah.domain.user.User
import com.friendalert.shivangshah.domain.user.UserRepository
import com.friendalert.shivangshah.model.friends.request.ContactsRequestModel
import com.friendalert.shivangshah.model.friends.response.FriendsResponseModel
import io.reactivex.Single
import javax.inject.Inject

/**
 * Created by shivangshah on 12/12/17.
 */
open class GetFriends @Inject constructor(val friendsRepository: FriendsRepository,
                                           val userRepository: UserRepository,
                                           threadExecutor: ThreadExecutor,
                                           postExecutionThread: PostExecutionThread):
        SingleUseCase<FriendsResponseModel, Void?>(threadExecutor, postExecutionThread) {

    public override fun buildUseCaseObservable(params: Void?): Single<FriendsResponseModel> {

        return userRepository.getUser().flatMap{ // get userId from local
            user: User -> friendsRepository.getContacts().flatMap{ // get contacts from device
                contacts: ArrayList<String> -> friendsRepository.getFriends(user.userId, ContactsRequestModel(contacts)) // send userid and contacts to backend to get friends response
            }
        }

    }
}
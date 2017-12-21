package com.friendalert.shivangshah.domain

import com.friendalert.shivangshah.domain.user.User
import com.friendalert.shivangshah.domain.user.UserRepository
import com.friendalert.shivangshah.domain.user.UserResponse
import io.reactivex.Single
import javax.inject.Inject

/**
 * Created by shivangshah on 12/21/17.
 */
open class LogoutUser @Inject constructor(val userRepository: UserRepository,
                                          threadExecutor: ThreadExecutor,
                                          postExecutionThread: PostExecutionThread):
        SingleUseCase<UserResponse, User>(threadExecutor, postExecutionThread) {
    override fun buildUseCaseObservable(params: User?): Single<UserResponse> {
        return userRepository.getUser().flatMap {
            user: User ->  userRepository.logoutUser(user)
        }
    }

}
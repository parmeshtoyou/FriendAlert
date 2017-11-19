package com.friendalert.shivangshah.domain.user

import com.friendalert.shivangshah.domain.CompletableUseCase
import com.friendalert.shivangshah.domain.PostExecutionThread
import com.friendalert.shivangshah.domain.SingleUseCase
import com.friendalert.shivangshah.domain.ThreadExecutor
import io.reactivex.Completable
import io.reactivex.Single
import javax.inject.Inject

/**
 * Created by shivangshah on 11/17/17.
 */
open class CreateUser @Inject constructor(val userRepository: UserRepository,
                                                threadExecutor: ThreadExecutor,
                                                postExecutionThread: PostExecutionThread):
        SingleUseCase<UserResponse,User>(threadExecutor, postExecutionThread) {
    override fun buildUseCaseObservable(user: User?): Single<UserResponse> {
        return userRepository.createUser(user!!)
    }


}
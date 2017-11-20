package com.friendalert.shivangshah.domain.user

import com.friendalert.shivangshah.domain.PostExecutionThread
import com.friendalert.shivangshah.domain.SingleUseCase
import com.friendalert.shivangshah.domain.ThreadExecutor
import io.reactivex.Single
import javax.inject.Inject

/**
 * Created by shivangshah on 11/20/17.
 */
open class GetUser @Inject constructor(val userRepository: UserRepository,
                                          threadExecutor: ThreadExecutor,
                                          postExecutionThread: PostExecutionThread):
        SingleUseCase<User, Void>(threadExecutor, postExecutionThread) {

    override fun buildUseCaseObservable(params: Void?): Single<User> {
        return userRepository.getUser()
    }
}
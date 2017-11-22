package com.friendalert.shivangshah.domain.myplaces

import com.friendalert.shivangshah.domain.PostExecutionThread
import com.friendalert.shivangshah.domain.SingleUseCase
import com.friendalert.shivangshah.domain.ThreadExecutor
import com.friendalert.shivangshah.domain.user.User
import com.friendalert.shivangshah.domain.user.UserRepository
import io.reactivex.Single
import javax.inject.Inject

/**
 * Gets Called from presentation layer, calls MyPlaceRepository to get MyPlaces
 */
open class GetMyPlaces @Inject constructor(val myPlaceRepository: MyPlaceRepository,
                                           val userRepository: UserRepository,
                                      threadExecutor: ThreadExecutor,
                                      postExecutionThread: PostExecutionThread):
        SingleUseCase<MyPlaces, Void?>(threadExecutor, postExecutionThread) {

    public override fun buildUseCaseObservable(params: Void?): Single<MyPlaces> {

        return userRepository.getUser().flatMap{
            user: User -> myPlaceRepository.getMyPlaces(user.userId) }

    }

}
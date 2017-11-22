package com.friendalert.shivangshah.domain.myplaces

import com.friendalert.shivangshah.domain.PostExecutionThread
import com.friendalert.shivangshah.domain.SingleUseCase
import com.friendalert.shivangshah.domain.ThreadExecutor
import com.friendalert.shivangshah.domain.user.User
import com.friendalert.shivangshah.domain.user.UserRepository
import io.reactivex.Single
import javax.inject.Inject

/**
 * Created by shivangshah on 11/21/17.
 */
class DeleteMyPlace @Inject constructor(val myPlaceRepository: MyPlaceRepository,
                                        threadExecutor: ThreadExecutor,
                                        postExecutionThread: PostExecutionThread):
        SingleUseCase<MyPlaceResponse, Int>(threadExecutor, postExecutionThread) {

    override fun buildUseCaseObservable(myPlaceId: Int?): Single<MyPlaceResponse> {

        return myPlaceRepository.deleteMyPlace(myPlaceId!!)

    }

}
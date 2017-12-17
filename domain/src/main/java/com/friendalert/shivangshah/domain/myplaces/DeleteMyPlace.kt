package com.friendalert.shivangshah.domain.myplaces

import com.friendalert.shivangshah.domain.PostExecutionThread
import com.friendalert.shivangshah.domain.SingleUseCase
import com.friendalert.shivangshah.domain.ThreadExecutor
import com.friendalert.shivangshah.domain.user.User
import com.friendalert.shivangshah.domain.user.UserRepository
import com.friendalert.shivangshah.model.myplaces.response.MyPlaceResponseModel
import io.reactivex.Single
import javax.inject.Inject

/**
 * Created by shivangshah on 11/21/17.
 */
class DeleteMyPlace @Inject constructor(val myPlaceRepository: MyPlaceRepository,
                                        threadExecutor: ThreadExecutor,
                                        postExecutionThread: PostExecutionThread):
        SingleUseCase<MyPlaceResponseModel, Int>(threadExecutor, postExecutionThread) {

    override fun buildUseCaseObservable(myPlaceId: Int?): Single<MyPlaceResponseModel> {

        return myPlaceRepository.deleteMyPlace(myPlaceId!!)

    }

}
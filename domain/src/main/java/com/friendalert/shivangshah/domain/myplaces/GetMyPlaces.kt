package com.friendalert.shivangshah.domain.myplaces

import com.friendalert.shivangshah.domain.PostExecutionThread
import com.friendalert.shivangshah.domain.SingleUseCase
import com.friendalert.shivangshah.domain.ThreadExecutor
import io.reactivex.Single
import javax.inject.Inject

/**
 * Gets Called from presentation layer, calls MyPlaceRepository to get MyPlaces
 */
open class GetMyPlaces @Inject constructor(val myPlaceRepository: MyPlaceRepository,
                                      threadExecutor: ThreadExecutor,
                                      postExecutionThread: PostExecutionThread):
        SingleUseCase<List<MyPlace>, Void?>(threadExecutor, postExecutionThread) {

    public override fun buildUseCaseObservable(params: Void?): Single<List<MyPlace>> {
        return myPlaceRepository.getMyPlaces()
    }

}
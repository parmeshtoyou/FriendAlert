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
class CreateMyPlace @Inject constructor(val myPlaceRepository: MyPlaceRepository,
                                        val userRepository: UserRepository,
                                        threadExecutor: ThreadExecutor,
                                        postExecutionThread: PostExecutionThread):
        SingleUseCase<MyPlaceResponse, MyPlace>(threadExecutor, postExecutionThread) {

    override fun buildUseCaseObservable(myPlace: MyPlace?): Single<MyPlaceResponse> {

        var localMyPlace : MyPlace;

        return userRepository.getUser().flatMap{
            user: User ->
                localMyPlace = MyPlace(myPlace!!.base_camp_id, user.userId, myPlace!!.nickname, myPlace!!.address,
                        myPlace!!.city, myPlace!!.state, myPlace!!.latitude, myPlace!!.longitude, myPlace!!.active)
                myPlaceRepository.createMyPlace(localMyPlace)
        }
    }

}
package com.friendalert.shivangshah.domain.myplaces

import com.friendalert.shivangshah.domain.PostExecutionThread
import com.friendalert.shivangshah.domain.SingleUseCase
import com.friendalert.shivangshah.domain.ThreadExecutor
import com.friendalert.shivangshah.domain.user.User
import com.friendalert.shivangshah.domain.user.UserRepository
import com.friendalert.shivangshah.model.myplaces.request.MyPlaceRequestModel
import com.friendalert.shivangshah.model.myplaces.response.MyPlaceResponseModel
import io.reactivex.Single
import javax.inject.Inject

/**
 * Created by shivangshah on 12/18/17.
 */
class EditMyPlace @Inject constructor(val myPlaceRepository: MyPlaceRepository,
                                      val userRepository: UserRepository,
                                      threadExecutor: ThreadExecutor,
                                      postExecutionThread: PostExecutionThread):
        SingleUseCase<MyPlaceResponseModel, MyPlaceRequestModel>(threadExecutor, postExecutionThread) {

    override fun buildUseCaseObservable(myPlace: MyPlaceRequestModel?): Single<MyPlaceResponseModel> {

        var localMyPlace : MyPlaceRequestModel;

        return userRepository.getUser().flatMap{
            user: User ->
            localMyPlace = MyPlaceRequestModel(myPlace!!.base_camp_id, user.userId, myPlace!!.nickname, myPlace!!.address,
                    myPlace!!.city, myPlace!!.state, myPlace!!.latitude, myPlace!!.longitude, myPlace!!.active, myPlace!!.radius)
            myPlaceRepository.editMyPlace(localMyPlace)
        }
    }

}
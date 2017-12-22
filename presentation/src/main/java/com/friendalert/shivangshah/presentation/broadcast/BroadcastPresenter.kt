package com.friendalert.shivangshah.presentation.broadcast

import com.friendalert.shivangshah.domain.SingleUseCase
import com.friendalert.shivangshah.model.broadcast.request.BroadcastRequestModel
import com.friendalert.shivangshah.model.broadcast.response.BroadcastResponseModel
import com.friendalert.shivangshah.model.exceptions.NoNetworkException
import com.friendalert.shivangshah.presentation.CustomResponseCodes
import com.friendalert.shivangshah.presentation.ErrorMessages
import io.reactivex.observers.DisposableSingleObserver
import javax.inject.Inject

/**
 * Created by shivangshah on 11/25/17.
 */
class BroadcastPresenter @Inject constructor(val broadcastView: BroadcastContract.View,
                                             val createBroadcastUseCase: SingleUseCase<BroadcastResponseModel, BroadcastRequestModel>):
        BroadcastContract.Presenter {

    init {
        broadcastView.setPresenter(this)
    }

    override fun start() {

    }

    override fun stop() {
        createBroadcastUseCase.dispose()
    }

    override fun createBroadcast(userId: String, message: String, latitude: String, longitude: String, location: String) {

        broadcastView.showProgress()
        createBroadcastUseCase.execute(CreateBroadcastSubscriber(), BroadcastRequestModel(userId, message, latitude,longitude, location))

    }

    inner class CreateBroadcastSubscriber: DisposableSingleObserver<BroadcastResponseModel>(){

        override fun onSuccess(t: BroadcastResponseModel) {

            if(t.customCode == CustomResponseCodes.createSuccess){

                broadcastView.showSuccess()
                broadcastView.hideProgress()

            }else{

                broadcastView.hideProgress()

            }

        }

        override fun onError(e: Throwable) {

            broadcastView.hideProgress()

            if (e is NoNetworkException){

                broadcastView.showFailure(true, ErrorMessages.noNetworkError)

            }else{

                broadcastView.showFailure(true, ErrorMessages.serviceError)

            }

        }

    }
}
package com.friendalert.shivangshah.presentation.broadcast

import com.friendalert.shivangshah.domain.SingleUseCase
import com.friendalert.shivangshah.domain.broadcast.Broadcast
import com.friendalert.shivangshah.domain.broadcast.CreateBroadcastResponse
import com.friendalert.shivangshah.model.exceptions.NoNetworkException
import com.friendalert.shivangshah.presentation.CustomResponseCodes
import com.friendalert.shivangshah.presentation.ErrorMessages
import io.reactivex.observers.DisposableSingleObserver
import javax.inject.Inject

/**
 * Created by shivangshah on 11/25/17.
 */
class BroadcastPresenter @Inject constructor(val broadcastView: BroadcastContract.View,
                                             val createBroadcastUseCase: SingleUseCase<CreateBroadcastResponse, Broadcast>,
                                             val broadcastMapper: BroadcastMapper):
        BroadcastContract.Presenter {

    init {
        broadcastView.setPresenter(this)
    }

    override fun start() {

    }

    override fun stop() {
        createBroadcastUseCase.dispose()
    }

    override fun createBroadcast(userId: String, message: String, latitude: String, longitude: String) {

        broadcastView.showProgress()
        createBroadcastUseCase.execute(CreateBroadcastSubscriber(), broadcastMapper.mapFromView(BroadcastView(userId, message, latitude,longitude)))

    }

    inner class CreateBroadcastSubscriber: DisposableSingleObserver<CreateBroadcastResponse>(){

        override fun onSuccess(t: CreateBroadcastResponse) {

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
package com.friendalert.shivangshah.presentation.friends

import com.friendalert.shivangshah.domain.SingleUseCase
import com.friendalert.shivangshah.domain.friends.CreateFriendRequest
import com.friendalert.shivangshah.domain.friends.GetFriends
import com.friendalert.shivangshah.domain.friends.UpdateFriend
import com.friendalert.shivangshah.model.exceptions.NoNetworkException
import com.friendalert.shivangshah.model.friends.request.UpdateFriendRequestModel
import com.friendalert.shivangshah.model.friends.response.CreateFriendRequestResponseModel
import com.friendalert.shivangshah.model.friends.response.FriendModel
import com.friendalert.shivangshah.model.friends.response.FriendsResponseModel
import com.friendalert.shivangshah.model.friends.response.UpdateFriendResponseModel
import com.friendalert.shivangshah.presentation.CustomResponseCodes
import com.friendalert.shivangshah.presentation.ErrorMessages
import io.reactivex.observers.DisposableSingleObserver
import javax.inject.Inject

/**
 * Created by shivangshah on 12/12/17.
 */
class FriendsPresenter @Inject constructor(val friendsView: FriendsContract.View,
                                           val getFriends: SingleUseCase<FriendsResponseModel, Void?>,
                                           val createFriendRequest: SingleUseCase<CreateFriendRequestResponseModel, String>,
                                           val updateFriend: SingleUseCase<UpdateFriendResponseModel, UpdateFriendRequestModel>,
                                           val presentationModel: FriendsPresentationModel)
    : FriendsContract.Presenter{

    init {
        friendsView.setPresenter(this)
    }

    override fun start() {


    }

    override fun getFriends() {

        friendsView.showProgress()
        getFriends.execute(GetFriendsSubscriber())

    }

    inner class GetFriendsSubscriber: DisposableSingleObserver<FriendsResponseModel>() {

        override fun onSuccess(t: FriendsResponseModel) {

            if(t.customCode == CustomResponseCodes.getSuccess)
            {

                presentationModel.setFriendsDictionary(
                        t.friendsData,
                        t.requestsData,
                        t.suggestedData,
                        t.inviteData
                )

                friendsView.showFriends(presentationModel.getFriendsDictionary()!!)
                friendsView.hideProgress()

                if(presentationModel.getFriendsDictionary()!!["Friends"]!!.size == 0){
                    friendsView.showNoFriends()
                }

                friendsView.showSuccess()

            }else{

                friendsView.hideProgress()

            }

        }

        override fun onError(exception: Throwable) {

            friendsView.hideProgress()

            if (exception is NoNetworkException){
                friendsView.showFailure(presentationModel.getFriendsDictionary() == null, ErrorMessages.noNetworkError)
            }else{
                friendsView.showFailure(presentationModel.getFriendsDictionary() == null, ErrorMessages.serviceError)
            }

        }

    }

    override fun sendFriendRequest(position: Int, friendModel: FriendModel) {

        friendsView.showProgress()
        presentationModel.setFriendActionType(FriendActionType.CreateFriendRequest)
        presentationModel.setCreateFriendRequestModel(friendModel)
        createFriendRequest.execute(CreateFriendRequestSubscriber(), friendModel.user_id)

    }

    inner class CreateFriendRequestSubscriber: DisposableSingleObserver<CreateFriendRequestResponseModel>() {


        override fun onSuccess(t: CreateFriendRequestResponseModel) {

            if(t.customCode == CustomResponseCodes.createSuccess){

                 presentationModel.successCreateFriendRequest(t.data.insertId)
                friendsView.showFriends(presentationModel.getFriendsDictionary()!!)
                friendsView.hideProgress()

            }else{

                friendsView.hideProgress()

            }
        }

        override fun onError(e: Throwable) {

            friendsView.hideProgress()

            if (e is NoNetworkException){
                friendsView.showFailure(false, ErrorMessages.noNetworkError)
            }else{
                friendsView.showFailure(false, ErrorMessages.serviceError)
            }

        }

    }

    override fun deleteFriend(position: Int, friendModel: FriendModel) {

        friendsView.showProgress()
        presentationModel.setFriendActionType(FriendActionType.RemoveFriend)
        presentationModel.setDeleteFriendRequestModel(friendModel)
        updateFriend.execute(UpdateFriendSubscriber(), UpdateFriendRequestModel(friendModel.request_id.toString(), "0"))

    }

    override fun acceptFriendRequest(position: Int, friendModel: FriendModel) {

        friendsView.showProgress()
        presentationModel.setFriendActionType(FriendActionType.AcceptFriendRequest)
        presentationModel.setAcceptFriendRequestModel(friendModel)
        updateFriend.execute(UpdateFriendSubscriber(), UpdateFriendRequestModel(friendModel.request_id.toString(), "1"))

    }

    override fun declineFriendRequest(position: Int, friendModel: FriendModel) {

        friendsView.showProgress()
        presentationModel.setFriendActionType(FriendActionType.DeclineFriendRequest)
        presentationModel.setDeclineFriendRequestModel(friendModel)
        updateFriend.execute(UpdateFriendSubscriber(), UpdateFriendRequestModel(friendModel.request_id.toString(), "2"))

    }

    inner class UpdateFriendSubscriber: DisposableSingleObserver<UpdateFriendResponseModel>() {


        override fun onSuccess(t: UpdateFriendResponseModel) {

            when(presentationModel.getFriendActionType()){

                FriendActionType.RemoveFriend -> {

                    presentationModel.successDeleteFriend()
                    friendsView.showFriends(presentationModel.getFriendsDictionary()!!)
                    friendsView.hideProgress()

                }

                FriendActionType.AcceptFriendRequest -> {

                    presentationModel.successAcceptFriendRequest()
                    friendsView.showFriends(presentationModel.getFriendsDictionary()!!)
                    friendsView.hideProgress()

                }

                FriendActionType.DeclineFriendRequest -> {

                    presentationModel.successDeclineFriendRequest()
                    friendsView.showFriends(presentationModel.getFriendsDictionary()!!)
                    friendsView.hideProgress()

                }

            }


        }

        override fun onError(e: Throwable) {

            friendsView.hideProgress()

            if (e is NoNetworkException){
                friendsView.showFailure(false, ErrorMessages.noNetworkError)
            }else{
                friendsView.showFailure(false, ErrorMessages.serviceError)
            }

        }

    }

    override fun getFriendsByType() {
        friendsView.showFriends(presentationModel.getFriendsDictionary()!!)
    }


    override fun stop() {

        getFriends.dispose()
        createFriendRequest.dispose()
        updateFriend.dispose()

    }


}
package com.friendalert.shivangshah.presentation.friends

import com.friendalert.shivangshah.domain.friends.CreateFriendRequest
import com.friendalert.shivangshah.domain.friends.GetFriends
import com.friendalert.shivangshah.domain.friends.UpdateFriend
import com.friendalert.shivangshah.model.friends.request.UpdateFriendRequestModel
import com.friendalert.shivangshah.model.friends.response.CreateFriendRequestResponseModel
import com.friendalert.shivangshah.model.friends.response.FriendModel
import com.friendalert.shivangshah.model.friends.response.FriendsResponseModel
import com.friendalert.shivangshah.model.friends.response.UpdateFriendResponseModel
import com.friendalert.shivangshah.presentation.CustomResponseCodes
import io.reactivex.observers.DisposableSingleObserver
import javax.inject.Inject

/**
 * Created by shivangshah on 12/12/17.
 */
class FriendsPresenter @Inject constructor(val friendsView: FriendsContract.View,
                                           val getFriends: GetFriends,
                                           val createFriendRequest: CreateFriendRequest,
                                           val updateFriend: UpdateFriend,
                                           val presentationModel: FriendsPresentationModel)
    : FriendsContract.Presenter{

    init {
        friendsView.setPresenter(this)
    }

    override fun start() {


    }

    override fun getFriends() {
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

                friendsView.showFriends(presentationModel.getFriendsDictionary())

            }else{

            }

        }

        override fun onError(exception: Throwable) {


        }

    }

    override fun sendFriendRequest(position: Int, friendModel: FriendModel) {

        presentationModel.setFriendActionType(FriendActionType.CreateFriendRequest)
        presentationModel.setCreateFriendRequestModel(friendModel)
        createFriendRequest.execute(CreateFriendRequestSubscriber(), friendModel.user_id)

    }

    inner class CreateFriendRequestSubscriber: DisposableSingleObserver<CreateFriendRequestResponseModel>() {


        override fun onSuccess(t: CreateFriendRequestResponseModel) {

            if(t.customCode == CustomResponseCodes.createSuccess){

                presentationModel.successCreateFriendRequest(t.data.insertId)
                friendsView.showFriends(presentationModel.getFriendsDictionary())

            }else{

            }
        }

        override fun onError(e: Throwable) {

        }

    }

    override fun deleteFriend(position: Int, friendModel: FriendModel) {

        presentationModel.setFriendActionType(FriendActionType.RemoveFriend)
        presentationModel.setDeleteFriendRequestModel(friendModel)
        updateFriend.execute(UpdateFriendSubscriber(), UpdateFriendRequestModel(friendModel.request_id.toString(), "0"))

    }

    override fun acceptFriendRequest(position: Int, friendModel: FriendModel) {

        presentationModel.setFriendActionType(FriendActionType.AcceptFriendRequest)
        presentationModel.setAcceptFriendRequestModel(friendModel)
        updateFriend.execute(UpdateFriendSubscriber(), UpdateFriendRequestModel(friendModel.request_id.toString(), "1"))

    }

    override fun declineFriendRequest(position: Int, friendModel: FriendModel) {

//        presentationModel.setFriendActionType(FriendActionType.DeclineFriendRequest)
//        presentationModel.setDeclineFriendRequestModel(friendModel)
//        updateFriend.execute(UpdateFriendSubscriber(), UpdateFriendRequestModel(friendModel.request_id.toString(), 2))

    }

    inner class UpdateFriendSubscriber: DisposableSingleObserver<UpdateFriendResponseModel>() {


        override fun onSuccess(t: UpdateFriendResponseModel) {

            when(presentationModel.getFriendActionType()){

                FriendActionType.RemoveFriend -> {

                    presentationModel.successDeleteFriend()
                    friendsView.showFriends(presentationModel.getFriendsDictionary())

                }

                FriendActionType.AcceptFriendRequest -> {

                    presentationModel.successAcceptFriendRequest()
                    friendsView.showFriends(presentationModel.getFriendsDictionary())

                }

                FriendActionType.DeclineFriendRequest -> {

                    presentationModel.successDeclineFriendRequest()
                    friendsView.showFriends(presentationModel.getFriendsDictionary())

                }

            }


        }

        override fun onError(e: Throwable) {

        }

    }

    override fun getFriendsByType() {
        friendsView.showFriends(presentationModel.getFriendsDictionary())
    }


    override fun stop() {

        getFriends.dispose()
        createFriendRequest.dispose()
        updateFriend.dispose()

    }


}
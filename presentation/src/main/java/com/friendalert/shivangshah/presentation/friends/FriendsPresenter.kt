package com.friendalert.shivangshah.presentation.friends

import com.friendalert.shivangshah.domain.friends.CreateFriendRequest
import com.friendalert.shivangshah.domain.friends.GetFriends
import com.friendalert.shivangshah.domain.friends.UpdateFriend
import com.friendalert.shivangshah.model.friends.request.UpdateFriendRequestModel
import com.friendalert.shivangshah.model.friends.response.CreateFriendRequestResponseModel
import com.friendalert.shivangshah.model.friends.response.FriendsResponseModel
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

    inner class CreateFriendRequestSubscriber: DisposableSingleObserver<CreateFriendRequestResponseModel>() {


        override fun onSuccess(t: CreateFriendRequestResponseModel) {

        }

        override fun onError(e: Throwable) {

        }

    }

    inner class UpdateFriendSubscriber: DisposableSingleObserver<UpdateFriendRequestModel>() {


        override fun onSuccess(t: UpdateFriendRequestModel) {

        }

        override fun onError(e: Throwable) {

        }

    }

    override fun getMyFriends() {
        friendsView.showMyFriends(presentationModel.getFriendsDictionary()["Friends"]!!)
    }

    override fun getRequests() {
        friendsView.showRequests(presentationModel.getFriendsDictionary()["Requests"]!!)
    }

    override fun getSuggested() {
        friendsView.showSuggested(presentationModel.getFriendsDictionary()["Suggested"]!!)
    }

    override fun getInvites() {
        friendsView.showInvites(presentationModel.getFriendsDictionary()["Invite"]!!)
    }


    override fun stop() {

        getFriends.dispose()
        createFriendRequest.dispose()
        updateFriend.dispose()

    }


}
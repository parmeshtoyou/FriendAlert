package com.friendalert.shivangshah.friendalert.injection.module

import com.friendalert.shivangshah.domain.friends.CreateFriendRequest
import com.friendalert.shivangshah.domain.friends.GetFriends
import com.friendalert.shivangshah.domain.friends.UpdateFriend
import com.friendalert.shivangshah.domain.myplaces.CreateMyPlace
import com.friendalert.shivangshah.domain.myplaces.DeleteMyPlace
import com.friendalert.shivangshah.domain.myplaces.GetMyPlaces
import com.friendalert.shivangshah.friendalert.friends.FriendsFragment
import com.friendalert.shivangshah.friendalert.injection.scopes.PerFragment
import com.friendalert.shivangshah.friendalert.myplaces.MyPlacesFragment
import com.friendalert.shivangshah.presentation.friends.FriendsContract
import com.friendalert.shivangshah.presentation.friends.FriendsPresentationModel
import com.friendalert.shivangshah.presentation.friends.FriendsPresenter
import com.friendalert.shivangshah.presentation.myplaces.*
import dagger.Module
import dagger.Provides

/**
 * Created by shivangshah on 12/12/17.
 */
@Module
class FriendsFragmentModule {

    @PerFragment
    @Provides
    internal fun provideFriendsView(friendsFragment: FriendsFragment): FriendsContract.View {
        return friendsFragment
    }

    @PerFragment
    @Provides
    internal fun provideFriendsPresenter(mainView: FriendsContract.View,
                                         getFriends: GetFriends,
                                         createFriendRequest: CreateFriendRequest,
                                         updateFriend: UpdateFriend,
                                         presentationModel: FriendsPresentationModel):
            FriendsContract.Presenter {
        return FriendsPresenter(mainView, getFriends, createFriendRequest, updateFriend, presentationModel)
    }

}
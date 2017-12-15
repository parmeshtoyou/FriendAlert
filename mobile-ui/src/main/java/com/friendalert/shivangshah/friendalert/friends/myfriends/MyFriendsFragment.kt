package com.friendalert.shivangshah.friendalert.friends.myfriends


import android.os.Bundle
import android.support.annotation.Nullable
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import com.friendalert.shivangshah.friendalert.R
import com.friendalert.shivangshah.model.friends.response.FriendModel
import java.util.ArrayList


/**
 * A simple [Fragment] subclass.
 */
class MyFriendsFragment : Fragment() {

    var myFriends = ArrayList<FriendModel>()

    fun instantiate(@Nullable arguments: Bundle): MyFriendsFragment {
        val friendsFragment = MyFriendsFragment()
        friendsFragment.setArguments(arguments)
        return friendsFragment
    }

    fun setData(data: ArrayList<FriendModel>){
        myFriends = data
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_my_friends, container, false)
    }

}// Required empty public constructor

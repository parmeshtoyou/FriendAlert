package com.friendalert.shivangshah.friendalert.friends


import android.os.Bundle
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
class SuggestFragment : Fragment() {

    var suggested = ArrayList<FriendModel>()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_suggest, container, false)
    }

    fun setData(data: ArrayList<FriendModel>){
        suggested = data
    }

}// Required empty public constructor

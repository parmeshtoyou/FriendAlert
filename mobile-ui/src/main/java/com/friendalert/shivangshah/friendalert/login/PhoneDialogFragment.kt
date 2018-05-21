package com.friendalert.shivangshah.friendalert.login


import android.os.Bundle
import android.support.v4.app.DialogFragment
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import com.friendalert.shivangshah.friendalert.R
import com.friendalert.shivangshah.friendalert.myplaces.ActionMyPlaceDialogFragment
import com.friendalert.shivangshah.friendalert.myplaces.MyPlaceActionListener
import com.friendalert.shivangshah.model.myplaces.request.MyPlaceRequestModel
import com.google.gson.Gson


/**
 * A simple [Fragment] subclass.
 */
class PhoneDialogFragment : DialogFragment() {

    var phoneButtonListener: PhoneActionListener? = null

    fun newInstance(phoneButtonListener: PhoneActionListener): PhoneDialogFragment {
        val f = PhoneDialogFragment()

        f.phoneButtonListener = phoneButtonListener

        return f
    }


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_phone_dialog, container, false)
    }

}// Required empty public constructor

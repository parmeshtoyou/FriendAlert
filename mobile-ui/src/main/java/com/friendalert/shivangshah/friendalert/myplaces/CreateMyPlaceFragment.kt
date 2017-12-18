package com.friendalert.shivangshah.friendalert.myplaces


import android.os.Bundle
import android.support.v4.app.DialogFragment
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.friendalert.shivangshah.friendalert.R
import com.friendalert.shivangshah.model.myplaces.request.MyPlaceRequestModel
import com.friendalert.shivangshah.model.myplaces.response.MyPlaceModel
import com.google.gson.Gson


/**
 * A simple [Fragment] subclass.
 */
class CreateMyPlaceFragment : DialogFragment() {

    lateinit var myPlace : MyPlaceRequestModel

    fun newInstance(myPlace: MyPlaceRequestModel): CreateMyPlaceFragment {
        val f = CreateMyPlaceFragment()

        // Supply num input as an argument.
        val args = Bundle()

        val gson = Gson()
        val json = gson.toJson(myPlace)

        args.putString("myPlace", json)
        f.setArguments(args)

        return f
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val gson = Gson()
        val json = arguments!!.getString("myPlace");
        myPlace = gson.fromJson<MyPlaceRequestModel>(json, MyPlaceRequestModel::class.java)
    }


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_create_my_place, container, false)
    }

}// Required empty public constructor

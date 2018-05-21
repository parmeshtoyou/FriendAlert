package com.friendalert.shivangshah.friendalert.myplaces


import android.os.Bundle
import android.support.v4.app.DialogFragment
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.Spinner
import android.widget.TextView
import com.friendalert.shivangshah.friendalert.R
import com.friendalert.shivangshah.model.myplaces.request.MyPlaceRequestModel
import com.google.gson.Gson


/**
 * A simple [Fragment] subclass.
 */
class ActionMyPlaceDialogFragment : DialogFragment() {

    lateinit var myPlace : MyPlaceRequestModel
    var isNewMyPlace = false
    var actionButtonListener: MyPlaceActionListener? = null

    lateinit var titleTextView: TextView
    lateinit var nickNameEditText : EditText
    lateinit var locationTextView : TextView
    lateinit var radiusSpinner : Spinner

    lateinit var actionButton1 : Button
    lateinit var actionButton2 : Button


    fun newInstance(myPlace: MyPlaceRequestModel, isNewMyPlace: Boolean, actionButtonListener: MyPlaceActionListener): ActionMyPlaceDialogFragment {
        val f = ActionMyPlaceDialogFragment()

        // Supply num input as an argument.
        val args = Bundle()

        val gson = Gson()
        val json = gson.toJson(myPlace)

        args.putString("myPlace", json)
        args.putBoolean("isNewMyPlace", isNewMyPlace)
        f.arguments = args

        f.actionButtonListener = actionButtonListener

        return f
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val gson = Gson()
        val json = arguments!!.getString("myPlace");
        myPlace = gson.fromJson<MyPlaceRequestModel>(json, MyPlaceRequestModel::class.java)

        isNewMyPlace = arguments!!.getBoolean("isNewMyPlace")
    }


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_create_my_place, container, false)

        titleTextView = view.findViewById(R.id.titleTextView)

        locationTextView = view.findViewById(R.id.locationValueTextView)
        nickNameEditText = view.findViewById(R.id.nicknameEditText)
        radiusSpinner = view.findViewById(R.id.radiusSpinner)

        actionButton1 = view.findViewById(R.id.actionButton1)
        actionButton2 = view.findViewById(R.id.actionButton2)

        setupUI()

        return view
    }

    private fun setupUI(){

        locationTextView.text = myPlace.address

        if(isNewMyPlace){

            // title
            titleTextView.text = "Create New MyPlace"

            // set radius spinner default - 5 miles
            radiusSpinner.setSelection(4)

            // action button 1 - cancel
            actionButton1.text = "Cancel"
            actionButton1.setOnClickListener{
                this.dismiss()
            }

            // action button 2 - create
            actionButton2.text = "Create"
            actionButton2.setOnClickListener {

                if(nickNameEditText.text.toString() == ""){
                        myPlace.nickname = ""
                    }else{
                        myPlace.nickname = nickNameEditText.text.toString()
                    }

                    myPlace.radius = radiusSpinner.selectedItem as String
                    actionButtonListener!!.createMyPlacePressed(myPlace)
                    this.dismiss()
            }

        }else{

            // title
            titleTextView.text = "Edit MyPlace"

            // set nickname
            if(myPlace.nickname == null){
                nickNameEditText.setText("")
            }else{
                nickNameEditText.setText(myPlace.nickname)
            }

            // set radius spinner
            radiusSpinner.setSelection(getIndex(radiusSpinner, myPlace.radius))

            // action button 1 - delete
            actionButton1.text = "Delete"
            actionButton1.setOnClickListener{
                v ->
                    actionButtonListener!!.deleteMyPlacePressed(myPlace)
                    this.dismiss()
            }

            // action button 2 - save
            actionButton2.text = "Save"
            actionButton2.setOnClickListener {
                v ->
                    if(nickNameEditText.text.toString() == ""){
                        myPlace.nickname = ""
                    }else{
                        myPlace.nickname = nickNameEditText.text.toString()
                    }
                    myPlace.radius = radiusSpinner.selectedItem as String
                    actionButtonListener!!.editMyPlacePressed(myPlace)
                    this. dismiss()
            }

        }
    }

    private fun getIndex(spinner: Spinner, radius: String): Int {
        var index = 0

        for (i in 0 until spinner.count) {
            if (spinner.getItemAtPosition(i).toString().equals(radius, ignoreCase = true)) {
                index = i
                break
            }
        }
        return index
    }

}// Required empty public constructor

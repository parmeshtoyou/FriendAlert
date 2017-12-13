package com.friendalert.shivangshah.model.friends.request

import com.google.gson.annotations.SerializedName

/**
 * Created by shivangshah on 12/12/17.
 */
class ContactsRequestModel (@SerializedName("contacts") val contacts: ArrayList<String>)
package com.friendalert.shivangshah.friendalert.broadcast

import android.location.Location

/**
 * Created by shivangshah on 11/24/17.
 */
interface LocationCallback {
    fun onLocationUpdatedCallback(location: Location)
}
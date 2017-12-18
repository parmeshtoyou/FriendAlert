package com.friendalert.shivangshah.friendalert.myplaces

import android.graphics.Color
import com.google.android.gms.maps.model.BitmapDescriptorFactory
import com.google.android.gms.maps.model.BitmapDescriptor
import android.graphics.Color.colorToHSV
import android.graphics.Color.parseColor



/**
 * Created by shivangshah on 12/18/17.
 */
class CustomBitmapDescriptorFactory : Any() {
    companion object {

        /**
         * Using HEX color string, parses the color and return its HUE component.
         * <br></br>
         * Note: Used for marker icons and ground overlays.
         *
         * @param colorString hex color as string
         * @return hsv components for color
         */
        fun getHsvFromColor(colorString: String): FloatArray {
            val hsv = FloatArray(3)
            val _color = Color.parseColor(colorString)
            Color.colorToHSV(_color, hsv)
            return hsv
        }

        /**
         * Creates a bitmap descriptor that refers to a colorization of HEX color string.
         *
         * @param colorString hex color as string
         * @return the BitmapDescriptor that was loaded using colorString or null if failed to obtain.
         */
        fun fromColorString(colorString: String): BitmapDescriptor {
            return BitmapDescriptorFactory.defaultMarker(CustomBitmapDescriptorFactory.getHsvFromColor(colorString)[0])
        }
    }
}
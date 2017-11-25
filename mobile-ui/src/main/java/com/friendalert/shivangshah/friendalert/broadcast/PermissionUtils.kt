package com.friendalert.shivangshah.friendalert.broadcast

import android.app.Activity
import android.content.Context
import android.content.DialogInterface
import android.content.pm.PackageManager
import android.os.Build
import android.support.v4.app.ActivityCompat
import android.support.v4.content.ContextCompat
import android.support.v7.app.AlertDialog
import android.util.Log
import android.widget.Toast


/**
 * Created by shivangshah on 11/24/17.
 */
class PermissionUtils {

    internal var context: Context
    internal var current_activity: Activity

    internal var permissionResultCallback: PermissionResultCallback


    internal var permission_list: ArrayList<String> = ArrayList()
    internal var listPermissionsNeeded: ArrayList<String> = ArrayList()

    internal var dialog_content = ""
    internal var req_code: Int = 0

    constructor(context: Context) {
        this.context = context
        this.current_activity = context as Activity

        permissionResultCallback = context as PermissionResultCallback


    }

    constructor(context: Context, callback: PermissionResultCallback) {
        this.context = context
        this.current_activity = context as Activity

        permissionResultCallback = callback


    }


    /**
     * Check the API Level & Permission
     *
     * @param permissions
     * @param dialog_content
     * @param request_code
     */

    fun check_permission(permissions: ArrayList<String>, dialog_content: String, request_code: Int) {
        this.permission_list = permissions
        this.dialog_content = dialog_content
        this.req_code = request_code

        if (Build.VERSION.SDK_INT >= 23) {
            if (checkAndRequestPermissions(permissions, request_code)) {
                permissionResultCallback.PermissionGranted(request_code)
                Log.i("all permissions", "granted")
                Log.i("proceed", "to callback")
            }
        } else {
            permissionResultCallback.PermissionGranted(request_code)

            Log.i("all permissions", "granted")
            Log.i("proceed", "to callback")
        }

    }


    /**
     * Check and request the Permissions
     *
     * @param permissions
     * @param request_code
     * @return
     */

    private fun checkAndRequestPermissions(permissions: ArrayList<String>, request_code: Int): Boolean {

        if (permissions.size > 0) {
            listPermissionsNeeded = ArrayList()

            for (i in 0 until permissions.size) {
                val hasPermission = ContextCompat.checkSelfPermission(current_activity, permissions[i])

                if (hasPermission != PackageManager.PERMISSION_GRANTED) {
                    listPermissionsNeeded.add(permissions[i])
                }

            }

            if (!listPermissionsNeeded.isEmpty()) {
                ActivityCompat.requestPermissions(current_activity, listPermissionsNeeded.toArray(arrayOfNulls<String>(listPermissionsNeeded.size)), request_code)
                return false
            }
        }

        return true
    }

    /**
     *
     *
     * @param requestCode
     * @param permissions
     * @param grantResults
     */
    fun onRequestPermissionsResult(requestCode: Int, permissions: Array<String>, grantResults: IntArray) {
        when (requestCode) {
            1 -> if (grantResults.size > 0) {
                val perms = HashMap<String, Int>()

                for (i in permissions.indices) {
                    perms.put(permissions[i], grantResults[i])
                }

                val pending_permissions = ArrayList<String>()

                for (i in 0 until listPermissionsNeeded.size) {
                    if (perms.get(listPermissionsNeeded[i]) != PackageManager.PERMISSION_GRANTED) {
                        if (ActivityCompat.shouldShowRequestPermissionRationale(current_activity, listPermissionsNeeded[i]))
                            pending_permissions.add(listPermissionsNeeded[i])
                        else {
                            Log.i("Go to settings", "and enable permissions")
                            permissionResultCallback.NeverAskAgain(req_code)
                            Toast.makeText(current_activity, "Go to settings and enable permissions", Toast.LENGTH_LONG).show()
                            return
                        }
                    }

                }

                if (pending_permissions.size > 0) {
                    showMessageOKCancel(dialog_content,
                            DialogInterface.OnClickListener { dialog, which ->
                                when (which) {
                                    DialogInterface.BUTTON_POSITIVE -> check_permission(permission_list, dialog_content, req_code)
                                    DialogInterface.BUTTON_NEGATIVE -> {
                                        Log.i("permisson", "not fully given")
                                        if (permission_list.size === pending_permissions.size)
                                            permissionResultCallback.PermissionDenied(req_code)
                                        else
                                            permissionResultCallback.PartialPermissionGranted(req_code, pending_permissions)
                                    }
                                }
                            })

                } else {
                    Log.i("all", "permissions granted")
                    Log.i("proceed", "to next step")
                    permissionResultCallback.PermissionGranted(req_code)

                }


            }
        }
    }


    /**
     * Explain why the app needs permissions
     *
     * @param message
     * @param okListener
     */
    private fun showMessageOKCancel(message: String, okListener: DialogInterface.OnClickListener) {
        AlertDialog.Builder(current_activity)
                .setMessage(message)
                .setPositiveButton("Ok", okListener)
                .setNegativeButton("Cancel", okListener)
                .create()
                .show()
    }

    interface PermissionResultCallback {
        fun PermissionGranted(request_code: Int)
        fun PartialPermissionGranted(request_code: Int, granted_permissions: ArrayList<String>)
        fun PermissionDenied(request_code: Int)
        fun NeverAskAgain(request_code: Int)
    }
}
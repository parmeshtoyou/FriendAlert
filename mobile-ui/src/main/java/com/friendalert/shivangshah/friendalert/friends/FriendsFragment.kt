package com.friendalert.shivangshah.friendalert.friends


import android.Manifest
import android.content.Context
import android.os.Bundle
import android.support.annotation.Nullable
import android.support.v13.app.FragmentCompat
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.friendalert.shivangshah.friendalert.PermissionUtils

import com.friendalert.shivangshah.friendalert.R
import com.friendalert.shivangshah.model.friends.response.FriendModel
import com.friendalert.shivangshah.presentation.friends.FriendsContract
import dagger.android.support.AndroidSupportInjection
import javax.inject.Inject


/**
 * A simple [Fragment] subclass.
 */
class FriendsFragment : Fragment(), FriendsContract.View, FragmentCompat.OnRequestPermissionsResultCallback,
        PermissionUtils.PermissionResultCallback {

    @Inject lateinit var friendsPresenter : FriendsContract.Presenter

    private val READ_CONTACTS_REQUEST = 3000

    var permissions: ArrayList<String> = ArrayList()
    var permissionUtils: PermissionUtils? = null

    var isPermissionGranted: Boolean = false

    fun instantiate(@Nullable arguments: Bundle): FriendsFragment {
        val friendsFragment = FriendsFragment()
        friendsFragment.setArguments(arguments)
        return friendsFragment
    }

    override fun setPresenter(presenter: FriendsContract.Presenter) {
        friendsPresenter = presenter;
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        val view = inflater!!.inflate(R.layout.fragment_friends, container, false)

        permissionUtils = PermissionUtils(context!!, this)
        permissions.add(Manifest.permission.READ_CONTACTS)

        permissionUtils!!.check_permission(permissions,"Need access to contacts",READ_CONTACTS_REQUEST)

        return view
    }

    override fun showFriends(friendsDictionary: HashMap<String, ArrayList<FriendModel>>) {

    }

    override fun PermissionGranted(request_code: Int) {

        isPermissionGranted=true;
        friendsPresenter.getFriends()

    }

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<String>, grantResults: IntArray) {
        permissionUtils!!.onRequestPermissionsResult(requestCode,permissions,grantResults);
    }

    override fun PartialPermissionGranted(request_code: Int, granted_permissions: ArrayList<String>) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun PermissionDenied(request_code: Int) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun NeverAskAgain(request_code: Int) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun onAttach(context: Context?) {
        AndroidSupportInjection.inject(this)
        super.onAttach(context)
    }

    override fun onStart() {
        super.onStart()

        friendsPresenter.start()
    }

    override fun onDestroy() {
        friendsPresenter.stop()

        super.onDestroy()
    }

}// Required empty public constructor

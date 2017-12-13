package com.friendalert.shivangshah.friendalert.friends


import android.Manifest
import android.content.Context
import android.os.Bundle
import android.support.annotation.Nullable
import android.support.design.widget.TabLayout
import android.support.v13.app.FragmentCompat
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.friendalert.shivangshah.friendalert.PermissionUtils

import com.friendalert.shivangshah.friendalert.R
import com.friendalert.shivangshah.model.friends.response.FriendModel
import com.friendalert.shivangshah.presentation.friends.FriendsContract
import dagger.android.support.AndroidSupportInjection
import javax.inject.Inject
import io.github.luizgrp.sectionedrecyclerviewadapter.SectionedRecyclerViewAdapter
/**
 * A simple [Fragment] subclass.
 */
class FriendsFragment : Fragment(), FriendsContract.View, FragmentCompat.OnRequestPermissionsResultCallback,
        PermissionUtils.PermissionResultCallback, TabLayout.OnTabSelectedListener {

    @Inject lateinit var friendsPresenter : FriendsContract.Presenter

    private val READ_CONTACTS_REQUEST = 3000

    var permissions: ArrayList<String> = ArrayList()
    var permissionUtils: PermissionUtils? = null

    var isPermissionGranted: Boolean = false

    var sectionAdapter = SectionedRecyclerViewAdapter()
    private var friendsRecyclerView: RecyclerView? = null
    private var tabLayout: TabLayout? = null

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

        friendsRecyclerView = view.findViewById<RecyclerView>(R.id.friendsRecyclerView)

        tabLayout = view.findViewById<TabLayout>(R.id.tabLayout)

        setupTabs()

        return view
    }

    override fun showFriends(friendsDictionary: HashMap<String, ArrayList<FriendModel>>) {

//        sectionAdapter.addSection(FriendsSection("Friends", friendsDictionary["Friends"]!!, FriendType.MyFriend))
//        sectionAdapter.addSection(FriendsSection("RequestsFragment", friendsDictionary["Requests"]!!, FriendType.Request))
//        sectionAdapter.addSection(FriendsSection("Suggested", friendsDictionary["Suggested"]!!, FriendType.Suggested))
//
//        friendsRecyclerView!!.setLayoutManager(LinearLayoutManager(getContext()));
//        friendsRecyclerView!!.setAdapter(sectionAdapter);

        var tab = tabLayout!!.getTabAt(0)
        tab!!.select()
    }

    override fun onTabReselected(tab: TabLayout.Tab?) {

    }

    override fun onTabUnselected(tab: TabLayout.Tab?) {

    }

    override fun onTabSelected(tab: TabLayout.Tab?) {
        when(tab!!.position){
            0 -> friendsPresenter.getMyFriends()
            1 -> friendsPresenter.getRequests()
            2 -> friendsPresenter.getSuggested()
            3 -> friendsPresenter.getInvites()
        }
    }

    override fun showMyFriends(myFriends: ArrayList<FriendModel>) {
        var fragment = MyFriendsFragment()
        fragment.setData(myFriends)
        childFragmentManager.beginTransaction().replace(R.id.friendsFrame, fragment).commit()
    }

    override fun showRequests(requests: ArrayList<FriendModel>) {
        var fragment = RequestsFragment()
        fragment.setData(requests)
        childFragmentManager.beginTransaction().replace(R.id.friendsFrame, fragment).commit()
    }

    override fun showSuggested(suggested: ArrayList<FriendModel>) {
        var fragment = SuggestFragment()
        fragment.setData(suggested)
        childFragmentManager.beginTransaction().replace(R.id.friendsFrame, fragment).commit()
    }

    override fun showInvites(invites: ArrayList<FriendModel>) {
        var fragment = InviteFragment()
        fragment.setData(invites)
        childFragmentManager.beginTransaction().replace(R.id.friendsFrame, fragment).commit()
    }

    fun setupTabs(){
        val friendsTab = tabLayout!!.newTab()
        friendsTab.text = "Friends"
        tabLayout!!.addTab(friendsTab)

        val requestsTab = tabLayout!!.newTab()
        requestsTab.text = "Requests"
        tabLayout!!.addTab(requestsTab)

        val suggestedTab = tabLayout!!.newTab()
        suggestedTab.text = "Suggested"
        tabLayout!!.addTab(suggestedTab)

        val inviteTab = tabLayout!!.newTab()
        inviteTab.text = "Invite"
        tabLayout!!.addTab(inviteTab)

        tabLayout!!.addOnTabSelectedListener(this)
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

}

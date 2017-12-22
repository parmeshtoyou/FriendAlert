package com.friendalert.shivangshah.friendalert.friends


import android.Manifest
import android.content.Context
import android.content.DialogInterface
import android.os.Bundle
import android.support.annotation.Nullable
import android.support.design.widget.TabLayout
import android.support.v13.app.FragmentCompat
import android.support.v4.app.Fragment
import android.support.v4.widget.SwipeRefreshLayout
import android.support.v7.app.AlertDialog
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.Toast
import com.facebook.FacebookSdk
import com.friendalert.shivangshah.friendalert.DataLoadingListener
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
        PermissionUtils.PermissionResultCallback, TabLayout.OnTabSelectedListener, FriendsActionListener, SwipeRefreshLayout.OnRefreshListener {

    @Inject lateinit var friendsPresenter : FriendsContract.Presenter

    private val READ_CONTACTS_REQUEST = 3000

    var permissions: ArrayList<String> = ArrayList()
    var permissionUtils: PermissionUtils? = null

    var isPermissionGranted: Boolean = false

    var friendsAdapter = FriendsAdapter(this)
    private var friendsRecyclerView: RecyclerView? = null
    private var swipeContainer: SwipeRefreshLayout? = null
    private var tabLayout: TabLayout? = null

    private var retryButton: Button? = null

    var screenType : FriendType = FriendType.MyFriend

    var dataLoadingListener: DataLoadingListener? = null

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
        friendsRecyclerView!!.setLayoutManager(LinearLayoutManager(getContext()));
        friendsRecyclerView!!.adapter = friendsAdapter

        swipeContainer = view.findViewById<SwipeRefreshLayout>(R.id.swipeContainer);

        retryButton = view.findViewById<Button>(R.id.retryButton)

        swipeContainer!!.setOnRefreshListener (this)

        tabLayout = view.findViewById<TabLayout>(R.id.tabLayout)

        retryButton!!.setOnClickListener {

            v: View? ->  friendsPresenter.getFriends()

        }

        setupTabs()

        return view
    }

    override fun showFriends(friendsDictionary: HashMap<String, ArrayList<FriendModel>>) {

        when(screenType){

            FriendType.MyFriend -> friendsAdapter.setData(friendsDictionary["Friends"]!!, screenType)

            FriendType.Suggested -> friendsAdapter.setData(friendsDictionary["Suggested"]!!, screenType)

            FriendType.Request -> friendsAdapter.setData(friendsDictionary["Requests"]!!, screenType)

            FriendType.Invite -> friendsAdapter.setData(friendsDictionary["Invite"]!!, screenType)

        }

        friendsAdapter.notifyDataSetChanged()

    }

    override fun showNoFriends() {

        var builder = AlertDialog.Builder(context!!);
        builder.setTitle("Friends list is empty")
        builder.setMessage("Please add friends from :\nSuggested (Your phone contacts who have FriendAlert)\nRequests (Accept any pending friend requests)\nInvite (Invite your friends to join FriendAlert)");
        builder.setCancelable(true);

        builder.setPositiveButton("Okay", { dialog, which -> dialog.cancel() })

        var alert = builder.create()
        alert.show();

    }

    override fun onTabReselected(tab: TabLayout.Tab?) {

    }

    override fun onTabUnselected(tab: TabLayout.Tab?) {

    }

    override fun onTabSelected(tab: TabLayout.Tab?) {
        when(tab!!.position){

            0 -> screenType = FriendType.MyFriend

            1 -> screenType = FriendType.Request

            2 -> screenType = FriendType.Suggested

            3 -> screenType = FriendType.Invite

        }

        friendsPresenter.getFriendsByType()
    }

    override fun DeleteFriendClicked(position: Int, friendModel: FriendModel?) {
        friendsPresenter.deleteFriend(position, friendModel!!)
    }

    override fun AcceptFriendRequestClicked(position: Int, friendModel: FriendModel?) {
        friendsPresenter.acceptFriendRequest(position, friendModel!!)
    }

    override fun DeclineFriendRequestClicked(position: Int, friendModel: FriendModel?) {
        friendsPresenter.declineFriendRequest(position, friendModel!!)
    }

    override fun SendFriendRequestClicked(position: Int, friendModel: FriendModel?) {
        friendsPresenter.sendFriendRequest(position, friendModel!!)
    }

    override fun InviteFriendClicked() {

    }

    override fun showSuccess() {

        swipeContainer!!.visibility = View.VISIBLE
        friendsRecyclerView!!.visibility = View.VISIBLE
        tabLayout!!.visibility = View.VISIBLE

        retryButton!!.visibility = View.GONE

    }

    override fun showFailure(firstTime: Boolean, errorMessage: String) {

        Toast.makeText(activity!!.applicationContext,errorMessage, Toast.LENGTH_LONG).show();

        if(firstTime){
            swipeContainer!!.visibility = View.GONE
            friendsRecyclerView!!.visibility = View.GONE
            tabLayout!!.visibility = View.GONE

            retryButton!!.visibility = View.VISIBLE
        }

    }

    override fun showProgress() {

        dataLoadingListener!!.dataLoadingStart()

    }

    override fun hideProgress() {

        swipeContainer!!.setRefreshing(false);
        dataLoadingListener!!.dataLoadingStop()

    }

    fun setupTabs(){
        val friendsTab = tabLayout!!.newTab()
        friendsTab.text = "Friends"
        tabLayout!!.addTab(friendsTab, true)

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

    override fun onRefresh() {
        swipeContainer!!.setRefreshing(true);
        friendsPresenter.getFriends()
    }

    override fun PartialPermissionGranted(request_code: Int, granted_permissions: ArrayList<String>) {

    }

    override fun PermissionDenied(request_code: Int) {

    }

    override fun NeverAskAgain(request_code: Int) {

    }

    override fun onAttach(context: Context?) {
        AndroidSupportInjection.inject(this)
        dataLoadingListener = activity as DataLoadingListener
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

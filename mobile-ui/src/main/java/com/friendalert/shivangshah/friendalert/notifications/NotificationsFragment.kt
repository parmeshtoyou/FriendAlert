package com.friendalert.shivangshah.friendalert.notifications

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.support.annotation.Nullable
import android.support.v4.app.Fragment
import android.support.v4.widget.SwipeRefreshLayout
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.friendalert.shivangshah.friendalert.DataLoadingListener
import com.friendalert.shivangshah.friendalert.R
import com.friendalert.shivangshah.model.notifications.response.NotificationModel
import com.friendalert.shivangshah.presentation.notifications.NotificationsContract
import com.google.gson.Gson
import dagger.android.support.AndroidSupportInjection
import javax.inject.Inject


/**
 * A simple [Fragment] subclass.
 */
class NotificationsFragment : Fragment(), NotificationsContract.View, NotificationClickedListener, SwipeRefreshLayout.OnRefreshListener {

    @Inject lateinit var notificationsPresenter : NotificationsContract.Presenter


    var notificationsAdapter = NotificationsAdapter(this)
    private var notificationsRecyclerView: RecyclerView? = null
    private var swipeContainer: SwipeRefreshLayout? = null

    var dataLoadingListener: DataLoadingListener? = null

    override fun setPresenter(presenter: NotificationsContract.Presenter) {
        notificationsPresenter = presenter;
    }

    fun instantiate(@Nullable arguments: Bundle): NotificationsFragment {
        val notificationsFragment = NotificationsFragment()
        notificationsFragment.setArguments(arguments)
        return notificationsFragment
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        var view = inflater!!.inflate(R.layout.fragment_notifications, container, false)

        notificationsRecyclerView = view.findViewById<RecyclerView>(R.id.notificationsRecyclerView)
        swipeContainer = view.findViewById<SwipeRefreshLayout>(R.id.swipeContainer);

        swipeContainer!!.setOnRefreshListener (this)


        setupNotificationsRecyclerView()

        return view;
    }

    override fun showNotifications(notifications: List<NotificationModel>) {
        notificationsAdapter.notifications = notifications
        notificationsAdapter.notifyDataSetChanged()
        notificationsRecyclerView?.visibility = View.VISIBLE
    }

    override fun notificationClicked(position: Int, notification: NotificationModel?) {

        notificationsPresenter.markAsRead(notification!!)

        val myIntent = Intent(this.activity, NotificationDetailActivity::class.java)
        val gson = Gson()
        val json = gson.toJson(notification)
        myIntent.putExtra("Notification", json)
        this.activity!!.startActivity(myIntent)

    }

    override fun onRefresh() {

        swipeContainer!!.setRefreshing(true);
        notificationsPresenter.retrieveNotifications()

    }

    override fun showProgress() {

        dataLoadingListener!!.dataLoadingStart()

    }

    override fun hideProgress() {

        swipeContainer!!.setRefreshing(false);
        dataLoadingListener!!.dataLoadingStop()

    }

    override fun showFailure() {



    }

    override fun showSuccess() {



    }

    override fun onAttach(context: Context?) {
        AndroidSupportInjection.inject(this)
        dataLoadingListener = activity as DataLoadingListener
        super.onAttach(context)
    }

    override fun onStart() {
        super.onStart()

        notificationsPresenter.start()
    }

    private fun setupNotificationsRecyclerView() {
        notificationsRecyclerView?.layoutManager = LinearLayoutManager(context)
        notificationsRecyclerView?.adapter = notificationsAdapter
    }

    override fun onDestroy() {
        notificationsPresenter.stop()
        super.onDestroy()
    }
}// Required empty public constructor

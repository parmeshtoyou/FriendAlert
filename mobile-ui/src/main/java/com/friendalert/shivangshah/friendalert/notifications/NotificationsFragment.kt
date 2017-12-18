package com.friendalert.shivangshah.friendalert.notifications

import android.content.Context
import android.os.Bundle
import android.support.annotation.Nullable
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.friendalert.shivangshah.friendalert.R
import com.friendalert.shivangshah.model.notifications.response.NotificationModel
import com.friendalert.shivangshah.presentation.notifications.NotificationsContract
import dagger.android.support.AndroidSupportInjection
import javax.inject.Inject


/**
 * A simple [Fragment] subclass.
 */
class NotificationsFragment : Fragment(), NotificationsContract.View {

    @Inject lateinit var notificationsPresenter : NotificationsContract.Presenter
    @Inject lateinit var notificationsAdapter : NotificationsAdapter

    private var notificationsRecyclerView: RecyclerView? = null

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
        setupNotificationsRecyclerView()

        return view;
    }

    override fun showNotifications(notifications: List<NotificationModel>) {
        notificationsAdapter.notifications = notifications
        notificationsAdapter.notifyDataSetChanged()
        notificationsRecyclerView?.visibility = View.VISIBLE
    }

    override fun onAttach(context: Context?) {
        AndroidSupportInjection.inject(this)
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

package com.friendalert.shivangshah.friendalert.notifications

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.friendalert.shivangshah.friendalert.R
import com.friendalert.shivangshah.friendalert.model.NotificationsViewModel
import javax.inject.Inject

/**
 * Created by shivangshah on 11/11/17.
 */

class NotificationsAdapter @Inject constructor(): RecyclerView.Adapter<NotificationsAdapter.ViewHolder>() {

    var notifications: List<NotificationsViewModel> = arrayListOf()

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val notification = notifications[position]
        holder.nameText.text = notification.name
        holder.titleText.text = notification.title
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val itemView = LayoutInflater
                .from(parent.context)
                .inflate(R.layout.item_notification, parent, false)
        return ViewHolder(itemView)
    }

    override fun getItemCount(): Int {
        return notifications.size
    }

    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        var nameText: TextView
        var titleText: TextView

        init {
            nameText = view.findViewById(R.id.text_name)
            titleText = view.findViewById(R.id.text_title)
        }
    }

}
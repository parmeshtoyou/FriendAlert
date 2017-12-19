package com.friendalert.shivangshah.friendalert.notifications

import android.opengl.Visibility
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.friendalert.shivangshah.friendalert.R
import com.friendalert.shivangshah.model.notifications.response.NotificationModel
import javax.inject.Inject

/**
 * Created by shivangshah on 11/11/17.
 */

class NotificationsAdapter @Inject constructor(): RecyclerView.Adapter<NotificationsAdapter.ViewHolder>() {

    var notifications: List<NotificationModel> = arrayListOf()

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        val notification = notifications[position]

        holder.initialsTextView.text = "SS"
        holder.nameTextView.text = "Shivang Shah"
        holder.locationTextView.text = "New York, New York"
        holder.timestampTextView.text = "20 minutes ago"

        if(notification.isRead){
            holder.readImageView.visibility = View.INVISIBLE
        }else{
            holder.readImageView.visibility = View.VISIBLE
        }

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

        var initialsTextView: TextView
        var nameTextView: TextView
        var locationTextView: TextView
        var timestampTextView: TextView

        var readImageView: ImageView

        init {
            initialsTextView = view.findViewById(R.id.initialsTextView)
            nameTextView = view.findViewById(R.id.nameTextView)
            locationTextView = view.findViewById(R.id.locationTextView)
            timestampTextView = view.findViewById(R.id.timestampTextView)

            readImageView = view.findViewById(R.id.readImageView)
        }
    }

}
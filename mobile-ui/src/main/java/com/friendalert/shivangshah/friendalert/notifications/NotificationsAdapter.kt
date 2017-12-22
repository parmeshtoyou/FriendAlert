package com.friendalert.shivangshah.friendalert.notifications

/**
 * Created by shivangshah on 12/20/17.
 */
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.friendalert.shivangshah.friendalert.R
import com.friendalert.shivangshah.model.notifications.response.NotificationModel

/**
 * Created by shivangshah on 11/11/17.
 */

class NotificationsAdapter constructor(listener: NotificationClickedListener) : RecyclerView.Adapter<NotificationsAdapter.ViewHolder>(), NotificationClickedListener {

    var notifications: List<NotificationModel> = arrayListOf()

    var listener: NotificationClickedListener

    init {
        this.listener = listener
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        val notification = notifications[position]

        var firstInitial = notification.first_name.take(1)
        var secondInitial = notification.last_name.take(1)

        holder.initialsTextView.text = firstInitial + secondInitial
        holder.nameTextView.text = notification.first_name + " " + notification.last_name
        holder.locationTextView.text = if (notification.location == null) "Location Not Available" else notification.location
        holder.timestampTextView.text = notification.timestamp

        if(notification.isRead){
            holder.readImageView.visibility = View.INVISIBLE
        }else{
            holder.readImageView.visibility = View.VISIBLE
        }

    }

    override fun notificationClicked(position: Int, notification: NotificationModel?) {
        listener.notificationClicked(position, notifications.get(position))
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val itemView = LayoutInflater
                .from(parent.context)
                .inflate(R.layout.row_notification, parent, false)
        return ViewHolder(itemView, this)
    }

    override fun getItemCount(): Int {
        return notifications.size
    }

    inner class ViewHolder(view: View, listener: NotificationClickedListener) : RecyclerView.ViewHolder(view), View.OnClickListener {

        var initialsTextView: TextView
        var nameTextView: TextView
        var locationTextView: TextView
        var timestampTextView: TextView

        var readImageView: ImageView

        var listener: NotificationClickedListener

        init {
            this.listener = listener

            initialsTextView = view.findViewById(R.id.initialsTextView)
            nameTextView = view.findViewById(R.id.nameTextView)
            locationTextView = view.findViewById(R.id.locationTextView)
            timestampTextView = view.findViewById(R.id.timestampTextView)

            readImageView = view.findViewById(R.id.readImageView)

            view.setOnClickListener(this)
        }

        override fun onClick(v: View?) {
            listener.notificationClicked(adapterPosition, null)
        }
    }

}

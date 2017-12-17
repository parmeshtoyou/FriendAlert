package com.friendalert.shivangshah.friendalert.friends

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import com.friendalert.shivangshah.friendalert.R
import com.friendalert.shivangshah.friendalert.notifications.NotificationsAdapter
import com.friendalert.shivangshah.model.friends.response.FriendModel
import com.friendalert.shivangshah.model.notifications.response.NotificationModel

/**
 * Created by shivangshah on 12/14/17.
 */
class FriendsAdapter constructor(actionListener: FriendsActionListener) : RecyclerView.Adapter<FriendsAdapter.ViewHolder>(), FriendsActionListener{

    var friends: java.util.ArrayList<FriendModel> = ArrayList()
    var type: FriendType = FriendType.MyFriend
    var actionListener: FriendsActionListener

    init {
        this.actionListener = actionListener
    }

    fun setData(friends: java.util.ArrayList<FriendModel>, type: FriendType){
        this.friends = friends
        this.type = type
    }

    override fun DeleteFriendClicked(position: Int, friendModel: FriendModel?) {
        this.actionListener.DeleteFriendClicked(position, friendModel)
    }

    override fun AcceptFriendRequestClicked(position: Int, friendModel: FriendModel?) {
        this.actionListener.AcceptFriendRequestClicked(position, friendModel)
    }

    override fun DeclineFriendRequestClicked(position: Int, friendModel: FriendModel?) {
        this.actionListener.DeclineFriendRequestClicked(position, friendModel)
    }

    override fun SendFriendRequestClicked(position: Int, friendModel: FriendModel?) {
        this.actionListener.SendFriendRequestClicked(position, friends.get(position))
    }

    override fun InviteFriendClicked() {
        this.actionListener.InviteFriendClicked()
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val friend = friends[position]
        holder.nameText.text = friend.first_name  + " " + friend.last_name

        when(type){
            FriendType.MyFriend -> {

                holder.actionButton.text = "Delete"

            }
            FriendType.Suggested -> {

                holder.actionButton.text = "Add"

            }
            FriendType.Request -> {

                holder.actionButton.text = "Accept"

            }
            FriendType.Invite -> {

                holder.actionButton.text = "Invite"

            }
        }
    }

    override fun getItemCount(): Int {
        return friends.size
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val itemView = LayoutInflater
                .from(parent.context)
                .inflate(R.layout.row_friends_child, parent, false)
        return ViewHolder(itemView, this)
    }


    inner class ViewHolder(view: View, actionListener: FriendsActionListener) : RecyclerView.ViewHolder(view), View.OnClickListener {

        var nameText: TextView
        var actionButton: Button
        var actionListener: FriendsActionListener

        init {
            this.actionListener = actionListener

            nameText = view.findViewById(R.id.nameTextView)
            actionButton = view.findViewById(R.id.actionButton)

            actionButton.setOnClickListener(this)
        }

        override fun onClick(v: View?) {

            when(type){
                FriendType.MyFriend -> this.actionListener.DeleteFriendClicked(adapterPosition, null)
                FriendType.Suggested -> this.actionListener.SendFriendRequestClicked(adapterPosition, null)
                FriendType.Request -> this.actionListener.AcceptFriendRequestClicked(adapterPosition, null)
                FriendType.Invite -> this.actionListener.InviteFriendClicked()
            }

        }
    }

}
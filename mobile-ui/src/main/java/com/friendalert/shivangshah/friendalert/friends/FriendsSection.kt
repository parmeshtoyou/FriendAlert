package com.friendalert.shivangshah.friendalert.friends

import android.support.v7.widget.RecyclerView
import android.view.View
import com.friendalert.shivangshah.friendalert.R
import com.friendalert.shivangshah.model.friends.response.FriendModel
import io.github.luizgrp.sectionedrecyclerviewadapter.StatelessSection
import android.widget.TextView
import io.github.luizgrp.sectionedrecyclerviewadapter.SectionParameters.*


/**
 * Created by shivangshah on 12/13/17.
 */
class FriendsSection : StatelessSection {

    var myFriends : ArrayList<FriendModel>
    var title : String
    var sectionType : FriendType

    constructor(title: String, myFriends: java.util.ArrayList<FriendModel>, sectionType: FriendType) : super(Builder(R.layout.row_friends_child)
            .headerResourceId(R.layout.row_friends_header)
            .build()) {

        this.title = title
        this.myFriends = myFriends
        this.sectionType = sectionType
    }

    override fun getContentItemsTotal(): Int {
        return myFriends.size
    }

    override fun onBindItemViewHolder(holder: RecyclerView.ViewHolder?, position: Int) {
        val itemHolder = holder as FriendViewHolder

        // bind your view here
        itemHolder.nameTextView.setText(myFriends.get(position).first_name)

    }

    override fun getItemViewHolder(view: View?): RecyclerView.ViewHolder {
        return FriendViewHolder(view!!)
    }

    internal class FriendViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val nameTextView: TextView

        init {
            nameTextView = itemView.findViewById<View>(R.id.nameTextView) as TextView
        }
    }

    override fun onBindHeaderViewHolder(holder: RecyclerView.ViewHolder?) {
        val itemHolder = holder as HeaderViewHolder

        // bind your view here
        itemHolder.titleTextView.setText(title)
    }

    override fun getHeaderViewHolder(view: View?): RecyclerView.ViewHolder {
        return HeaderViewHolder(view!!)
    }

    internal class HeaderViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
        val titleTextView : TextView

        init{
            titleTextView = itemView.findViewById<View>(R.id.headerTextView) as TextView
        }
    }


}

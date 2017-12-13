package com.friendalert.shivangshah.friendalert.friends

import com.afollestad.sectionedrecyclerview.ItemCoord
import com.afollestad.sectionedrecyclerview.SectionedViewHolder
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.afollestad.sectionedrecyclerview.SectionedRecyclerViewAdapter
import com.friendalert.shivangshah.friendalert.R


/**
 * Created by shivangshah on 12/12/17.
 */
class FriendsAdapter : SectionedRecyclerViewAdapter<FriendsAdapter.MainVH>() {

    override fun getSectionCount(): Int {
        return 4
    }

    override fun getItemCount(sectionIndex: Int): Int {
        return 8 // number of items in section, you could also pull this from a map of lists
    }

    override fun onBindHeaderViewHolder(holder: MainVH, section: Int, expanded: Boolean) {
        // Setup header view
    }

    override fun onBindViewHolder(holder: MainVH, section: Int, relativePosition: Int, absolutePosition: Int) {
        // Setup non-header view.
        // 'section' is section index.
        // 'relativePosition' is index in this section.
        // 'absolutePosition' is index out of all items, including headers and footers.
        // See sample project for a visual of how these indices work.
    }

    override fun onBindFooterViewHolder(holder: MainVH, section: Int) {
        // Setup footer view, if footers are enabled (see the next section)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MainVH {
        // Change inflated layout based on type
        val layoutRes: Int
        when (viewType) {
            SectionedRecyclerViewAdapter.VIEW_TYPE_HEADER -> layoutRes = R.layout.abc_action_menu_item_layout
            else -> layoutRes = R.layout.abc_action_menu_item_layout
        }
        val v = LayoutInflater.from(parent.context)
                .inflate(layoutRes, parent, false)
        return MainVH(v)
    }

    class MainVH(itemView: View) : SectionedViewHolder(itemView), View.OnClickListener {

        init {
            // Setup view holder. You'd want some views to be optional, e.g. the
            // header/footer will have views that normal item views do or do not have.
            itemView.setOnClickListener(this)
        }

        override fun onClick(view: View) {
            // SectionedViewHolder exposes methods such as:
            val isHeader = isHeader
            val isFooter = isFooter
            val position = relativePosition
            val section = position.section()
            val relativePos = position.relativePos()
        }
    }
}
package com.kippz07.creditonparishchurchmusic.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.kippz07.creditonparishchurchmusic.R
import com.kippz07.creditonparishchurchmusic.dao.MusicEntity
import com.kippz07.creditonparishchurchmusic.model.Day
import com.kippz07.creditonparishchurchmusic.model.Piece


class ServiceItemAdapter(private val context: Context, private val dataset: List<MusicEntity>) : RecyclerView.Adapter<ServiceItemAdapter.ItemViewHolder>() {
    class ItemViewHolder(private val view: View) : RecyclerView.ViewHolder(view) {
        val typeTextView: TextView = view.findViewById(R.id.item_type)
        val titleView: TextView = view.findViewById(R.id.item_title)
        val composerTextView: TextView = view.findViewById(R.id.item_composer)
        val linkTextView: TextView = view.findViewById(R.id.item_link_link)
//        val linkTextTextView: TextView = view.findViewById(R.id.item_link)
        val itemLayout: LinearLayout = view.findViewById(R.id.item_layout)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        val adapterLayout = LayoutInflater.from(parent.context).inflate(R.layout.service_list_item_new, parent, false)
        return ItemViewHolder(adapterLayout)
    }

    override fun getItemCount() = dataset.size

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        val item = dataset[position]
        holder.titleView.text = item.title

        if (item.type != "") {
            holder.typeTextView.text = item.type
        } else {
            holder.typeTextView.visibility = View.GONE
            holder.itemLayout.setPadding(0, 45, 0, 30)
        }

        if (item.composer != "") {
            holder.composerTextView.text = item.composer
        } else {
            holder.composerTextView.visibility = View.GONE
            holder.itemLayout.setPadding(0, 45, 0, 30)
        }

        if (item.link != "") {
            holder.linkTextView.text = item.link
        } else {
//            holder.linkTextTextView.visibility = View.GONE
            holder.linkTextView.visibility = View.GONE
        }

    }
}
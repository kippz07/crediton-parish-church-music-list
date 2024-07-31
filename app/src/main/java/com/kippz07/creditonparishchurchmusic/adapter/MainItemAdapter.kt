package com.kippz07.creditonparishchurchmusic.adapter

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.kippz07.creditonparishchurchmusic.R
import com.kippz07.creditonparishchurchmusic.ServiceActivity
import com.kippz07.creditonparishchurchmusic.dao.ServiceEntity
import com.kippz07.creditonparishchurchmusic.model.Day
import com.kippz07.creditonparishchurchmusic.model.Piece


class MainItemAdapter(private val context: Context, private val dataset: List<ServiceEntity>) : RecyclerView.Adapter<MainItemAdapter.ItemViewHolder>() {
    class ItemViewHolder(private val view: View) : RecyclerView.ViewHolder(view) {
        val serviceDateTextView: TextView = view.findViewById(R.id.main_service_date)
        val serviceTypeTextView: TextView = view.findViewById(R.id.main_service_type)
        val mainItemTextView: LinearLayout = view.findViewById(R.id.main_list_item)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        val adapterLayout = LayoutInflater.from(parent.context).inflate(R.layout.main_list_item, parent, false)
        return ItemViewHolder(adapterLayout)
    }

    override fun getItemCount() = dataset.size

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        val item = dataset[position]
        holder.serviceDateTextView.text = item.date.toString()
        holder.serviceTypeTextView.text = item.serviceType
        holder.mainItemTextView.setOnClickListener {
            val intent = Intent(context, ServiceActivity::class.java)
            intent.putExtra("music", item)
            context.startActivity(intent)
        }
    }
}



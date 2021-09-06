package com.rockex6.app.foodsslots.adapter

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.rockex6.app.foodsslots.R
import com.rockex6.app.foodsslots.db.Table
import kotlinx.android.synthetic.main.item_list_name.view.*

class ListContentAdapter(
    private val data: List<Table.ListContent>,
    private val deleteFunction: (Table.ListContent) -> Unit
) :
    RecyclerView.Adapter<ListContentViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListContentViewHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.item_list_name, parent, false)
        return ListContentViewHolder(view)
    }

    override fun onBindViewHolder(holder: ListContentViewHolder, position: Int) {
        holder.vListName.text = data[position].content_name
        holder.vListName.setOnLongClickListener {
            deleteFunction(data[position])
            true
        }
    }

    override fun getItemCount(): Int {
        return data.size
    }
}


class ListContentViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    val vListName = itemView.vListName
}



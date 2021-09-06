package com.rockex6.app.foodsslots.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.rockex6.app.foodsslots.R
import kotlinx.android.synthetic.main.item_slots_text.view.*

class FirstTextAdapter(private val position: Int) : RecyclerView.Adapter<SlotsTextViewHolder>() {

    private val defaultString: Array<String> = arrayOf("今", "天", "吃", "什", "麼", "～")
    private var data = ArrayList<String>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SlotsTextViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_slots_text, parent, false)
        return SlotsTextViewHolder(view)
    }

    override fun onBindViewHolder(holder: SlotsTextViewHolder, position: Int) {
        if (data.size == 0) {
            holder.vSlotsText.text = (defaultString[this.position])
            return
        }
        holder.vSlotsText.text = (data[position])
    }

    override fun getItemCount(): Int {
        return if (data.size == 0) 1 else data.size
    }

    public fun clearData() {
        data.clear()
        notifyDataSetChanged()
    }

    public fun getUserData(): ArrayList<String> {
        return data
    }
}

class SlotsTextViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    val vSlotsText: TextView = itemView.vSlotsText
}
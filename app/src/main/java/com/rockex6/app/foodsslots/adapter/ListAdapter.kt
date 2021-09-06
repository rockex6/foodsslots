package com.rockex6.app.foodsslots.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.ViewTreeObserver.OnGlobalLayoutListener
import android.widget.BaseAdapter
import android.widget.TextView
import com.rockex6.app.foodsslots.R
import com.rockex6.app.foodsslots.db.Table


class ListAdapter(
    private val mContext: Context,
    private val data: List<Table.ListName>,
    private val setSlotsLayout: (Int) -> Unit
) :
    BaseAdapter() {

    override fun getCount(): Int {
        return data.size
    }

    override fun getItem(p0: Int): Any {
        return data[p0]
    }

    override fun getItemId(p0: Int): Long {
        return p0.toLong()
    }


    override fun getView(p0: Int, p1: View?, p2: ViewGroup?): View {
        val contentView = LayoutInflater.from(mContext).inflate(R.layout.item_list_name, null)
        val item = getItem(p0) as Table.ListName
        val textView = contentView?.findViewById<TextView>(R.id.vListName)
        textView?.let {
            it.text = item.name
            it.tag = item
            it.viewTreeObserver.addOnGlobalLayoutListener(object : OnGlobalLayoutListener {
                override fun onGlobalLayout() {
                    setSlotsLayout(it.height + 7)
                    it.viewTreeObserver.removeOnGlobalLayoutListener(this)
                }
            })
        }
        return contentView
    }
}
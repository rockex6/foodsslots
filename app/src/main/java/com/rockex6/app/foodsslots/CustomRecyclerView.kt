package com.rockex6.app.foodsslots

import android.content.Context
import android.view.MotionEvent
import androidx.recyclerview.widget.RecyclerView

class CustomRecyclerView(context: Context) : RecyclerView(context) {

    override fun onInterceptTouchEvent(event: MotionEvent?): Boolean {

        return false
    }


}
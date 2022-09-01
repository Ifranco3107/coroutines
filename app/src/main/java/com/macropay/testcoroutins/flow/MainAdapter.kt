package com.macropay.testcoroutins.flow

import android.content.Context

import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter

class MainAdapter: BaseAdapter() {
val TAG = "MainAdapter"

  //  var binding: MovtoLayoutBinding? = null
    var context: Context? = null

    var items: List<String>? = null
        set(value) {
            field = value
        }


    override fun getCount(): Int {
       return  items!!.size
    }

    override fun getItem(p0: Int): Any {
     return items!!.get(p0)
    }

    override fun getItemId(p0: Int): Long {
        return p0.toLong()
    }

    override fun getView(p0: Int, p1: View?, p2: ViewGroup?): View {
        TODO("Not yet implemented")
       println(TAG +" -  "+ p0+ ".- " +items!!.get(p0))
    }
}
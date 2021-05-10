package com.wapchief.imitationlol

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import java.util.*

/**
 * Created by apple on 2017/6/21.
 */
class FragmentAdapter(data: MutableList<MainBean?>, context: Context) : RecyclerView.Adapter<RecyclerView
.ViewHolder>() {
    var datas: MutableList<MainBean?> = ArrayList()
    private val context: Context
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.item_fragment, parent, false)
        return ItemViewHolder(view)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        (holder as? ItemViewHolder)?.itemView?.setOnClickListener {
            val position = holder.getLayoutPosition()
            //                    onItemClickListener.onItemClick(holder.itemView, position);
        }
    }

    override fun getItemCount(): Int {
        return datas.size
    }

    private inner class ItemViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        var iv: ImageView
        var tvTitle: TextView
        var tvContent: TextView
        var tvTime: TextView

        init {
            iv = view.findViewById<View>(R.id.item_img) as ImageView
            tvTitle = view.findViewById<View>(R.id.item_title) as TextView
            tvContent = view.findViewById<View>(R.id.item_content) as TextView
            tvTime = view.findViewById<View>(R.id.item_time) as TextView
        }
    }

    interface OnItemClickListener {
        fun onItemClick(view: View?, position: Int)
        fun onItemLongClick(view: View?, position: Int)
    }

    private var onItemClickListener: OnItemClickListener? = null
    fun setOnItemClickListener(onItemClickListener: OnItemClickListener?) {
        this.onItemClickListener = onItemClickListener
    }

    init {
        this.datas = data
        this.context = context
    }
}
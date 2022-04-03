package com.example.android_practice.lib

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.BaseAdapter
import android.widget.ListView
import androidx.databinding.BindingAdapter
import com.example.android_practice.databinding.ListItemCellBinding
import com.example.android_practice.models.DoD

@BindingAdapter("onItemClickListener")
fun setOnItemClickListener(listView: ListView, listener: AdapterView.OnItemClickListener) {
    listView.onItemClickListener = listener
}

class ListViewAdapter(
    private val context: Context,
    private var dataList: List<DoD>
) : BaseAdapter() {
    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        val binding =
            if (convertView == null) {
                val inflater = LayoutInflater.from(context)
                // https://developer.android.com/topic/libraries/data-binding/expressions?hl=ja
                val tBinding: ListItemCellBinding =
                    ListItemCellBinding.inflate(inflater, parent, false)
                // tagにインスタンスをセット(convertViewが存在する場合に使い回すため)
                tBinding.root.tag = tBinding
                tBinding
            } else {
                convertView.tag as ListItemCellBinding
            }

        binding.dod = getItem(position) as DoD
        // 即時バインド
        binding.executePendingBindings()

        return binding.root
    }

    fun updateItems(newItems: List<DoD>) {
        dataList = newItems
        // 変更の通知
        notifyDataSetChanged()
    }

    override fun getItem(position: Int): Any {
        return dataList[position]
    }

    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

    override fun getCount(): Int {
        return dataList.size
    }
}
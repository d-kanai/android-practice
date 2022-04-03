package com.example.android_practice.lib

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.BaseAdapter
import android.widget.ListView
import androidx.databinding.BindingAdapter
import com.example.android_practice.databinding.DodListItemBinding
import com.example.android_practice.models.DoD

@BindingAdapter("onItemClickListener")
fun setOnItemClickListener(listView: ListView, listener: AdapterView.OnItemClickListener) {
    listView.onItemClickListener = listener
}

class ListViewAdapter<T>(
    private val context: Context,
    private var items: List<T>
) : BaseAdapter() {
    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        val binding = if (convertView == null) {
            val inflater = LayoutInflater.from(context)
            // https://developer.android.com/topic/libraries/data-binding/expressions?hl=ja
            val tBinding: DodListItemBinding = DodListItemBinding.inflate(inflater, parent, false)
            // tagにインスタンスをセット(convertViewが存在する場合に使い回すため)
            tBinding.root.tag = tBinding
            tBinding
        } else {
            convertView.tag as DodListItemBinding
        }

        binding.dod = getItem(position) as DoD
        binding.executePendingBindings()
        return binding.root
    }

    fun updateItems(items: List<T>) {
        this.items = items
        notifyDataSetChanged()
    }

    override fun getItem(position: Int): T {
        return items[position]
    }

    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

    override fun getCount(): Int {
        return items.size
    }
}
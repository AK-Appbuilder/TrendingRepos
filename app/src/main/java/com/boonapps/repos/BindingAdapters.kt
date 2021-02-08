package com.boonapps.repos

import android.view.View
import android.widget.ImageView
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide


@BindingAdapter("adapterData")
fun <T> RecyclerView.bindDataSet(data: List<T>?) {
    adapter?.let {
        val adapter = adapter as ListAdapter<T, in RecyclerView.ViewHolder>
        adapter.submitList(data)
    }
}

@BindingAdapter(value = ["setAdapter"])
fun RecyclerView.bindRecyclerViewAdapter(adapter: RecyclerView.Adapter<*>?) {
    this.run {
        this.adapter = adapter
        this.addItemDecoration(
            DividerItemDecoration(
                this.getContext(),
                DividerItemDecoration.VERTICAL
            )
        )
    }
}

@BindingAdapter("loadImage")
fun ImageView.loadImageUri(url: String?){
    url?.let {
        Glide.with(context)
            .load(it)
            .placeholder(R.drawable.ic_avatar)
            .error(R.drawable.ic_avatar)
            .into(this)
    }
}


@BindingAdapter("visibleFlag")
fun View.visibleFlag(flag: Boolean) {
    if (flag) {
        this.show()
    } else {
        this.gone()
    }
}
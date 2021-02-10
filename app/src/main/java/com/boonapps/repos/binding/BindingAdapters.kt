package com.boonapps.repos.binding

import android.view.View
import android.widget.ImageView
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.boonapps.repos.R
import com.boonapps.repos.extensions.gone
import com.boonapps.repos.extensions.show
import com.boonapps.repos.models.Result
import com.boonapps.repos.models.successOr
import com.bumptech.glide.Glide
import com.facebook.shimmer.ShimmerFrameLayout


@BindingAdapter("adapterData")
fun <T> RecyclerView.bindDataSet(result: Result<List<T>>?) {
    adapter?.let {
        val adapter = adapter as ListAdapter<T, in RecyclerView.ViewHolder>
        adapter.submitList(result?.successOr(mutableListOf()))
    }
}

@BindingAdapter(value = ["setAdapter"])
fun RecyclerView.bindRecyclerViewAdapter(adapter: RecyclerView.Adapter<*>?) {
    this.run {
        this.adapter = adapter
        this.addItemDecoration(
            DividerItemDecoration(
                this.context,
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
            .circleCrop()
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

@BindingAdapter("shimmerAnimation")
fun ShimmerFrameLayout.setAnimation(flag: Boolean){
    if (flag) {
        this.startShimmer()
    } else {
        this.stopShimmer()
    }
}
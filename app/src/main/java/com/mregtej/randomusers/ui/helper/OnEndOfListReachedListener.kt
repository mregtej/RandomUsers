package com.mregtej.randomusers.ui.helper

import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.OnScrollListener

class OnEndOfListReachedListener(private val onEndOfListReachedAction: () -> Unit): OnScrollListener() {

    override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
        super.onScrolled(recyclerView, dx, dy)

        if (dy < 0) return

        val layoutManager = recyclerView.layoutManager as LinearLayoutManager

        if (layoutManager.findLastCompletelyVisibleItemPosition() == layoutManager.itemCount -1) {
            onEndOfListReachedAction()
        }
    }
}
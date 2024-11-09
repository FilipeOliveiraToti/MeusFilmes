package com.foliveira.pacientes.meusfilmes.ui.decoration

import android.graphics.Rect
import android.view.View
import androidx.annotation.DimenRes
import androidx.recyclerview.widget.RecyclerView

class GridItemDecoration(private val spanCount: Int, @DimenRes private val spacing: Int) : RecyclerView.ItemDecoration() {

    override fun getItemOffsets(outRect: Rect, view: View, parent: RecyclerView, state: RecyclerView.State) {
        val spacing = view.context.resources.getDimensionPixelSize(spacing)
        val position = parent.getChildAdapterPosition(view)
        val column = position % spanCount

        outRect.left = spacing - column * spacing / spanCount
        outRect.right = (column + 1) * spacing / spanCount

        if (position < spanCount) {
            outRect.top = spacing
        }
        outRect.bottom = spacing
    }
}
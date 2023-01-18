package kg.ruslansupataev.currencyapp.features.currency_list.mine.recycler

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.drawable.ColorDrawable
import android.graphics.drawable.Drawable
import android.view.View
import androidx.annotation.NonNull
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.RecyclerView
import kg.ruslansupataev.currencyapp.R


class SwipeToDeleteCallback(
    context: Context,
    private val onSwiped: (pos: Int) -> Unit
) : ItemTouchHelper.Callback() {

    private val paint = Paint()
    private val colorDrawable = ColorDrawable()
    private val backgroundColor = Color.parseColor("#00FFFF")
    private val deleteDrawable: Drawable? = ContextCompat.getDrawable(context, R.drawable.ic_delete)
    private val intrinsicWidth = deleteDrawable?.intrinsicWidth
    private val intrinsicHeight = deleteDrawable?.intrinsicHeight

    override fun getMovementFlags(
        recyclerView: RecyclerView,
        viewHolder: RecyclerView.ViewHolder
    ): Int = makeMovementFlags(0, ItemTouchHelper.LEFT)

    override fun onMove(
        recyclerView: RecyclerView,
        viewHolder: RecyclerView.ViewHolder,
        target: RecyclerView.ViewHolder
    ): Boolean = false

    override fun onChildDraw(
        c: Canvas,
        recyclerView: RecyclerView,
        viewHolder: RecyclerView.ViewHolder,
        dX: Float,
        dY: Float,
        actionState: Int,
        isCurrentlyActive: Boolean
    ) {
        val itemView: View = viewHolder.itemView
        val itemHeight: Int = itemView.height

        val isCancelled = dX == 0f && !isCurrentlyActive

        if (isCancelled) {
            clearCanvas(
                c,
                itemView.right + dX,
                itemView.top.toFloat(),
                itemView.right.toFloat(),
                itemView.bottom.toFloat()
            )
            super.onChildDraw(c, recyclerView, viewHolder, dX, dY, actionState, isCurrentlyActive)
            return
        }

        colorDrawable.color = backgroundColor
        colorDrawable.setBounds(
            itemView.right + dX.toInt(),
            itemView.top,
            itemView.right,
            itemView.bottom
        )
        colorDrawable.draw(c)

        val deleteIconTop: Int = itemView.top + (itemHeight - intrinsicHeight!!) / 2
        val deleteIconMargin = (itemHeight - intrinsicHeight) / 2
        val deleteIconLeft: Int = itemView.right - deleteIconMargin - intrinsicWidth!!
        val deleteIconRight: Int = itemView.right - deleteIconMargin
        val deleteIconBottom = deleteIconTop + intrinsicHeight


        deleteDrawable!!.setBounds(deleteIconLeft, deleteIconTop, deleteIconRight, deleteIconBottom)
        deleteDrawable.draw(c)

        super.onChildDraw(c, recyclerView, viewHolder, dX, dY, actionState, isCurrentlyActive)

    }

    private fun clearCanvas(c: Canvas, left: Float, top: Float, right: Float, bottom: Float) {
        c.drawRect(left, top, right, bottom, paint)
    }

    override fun getSwipeThreshold(@NonNull viewHolder: RecyclerView.ViewHolder): Float {
        return 0.7f
    }

    override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
        val position = viewHolder.adapterPosition
        onSwiped(position)
    }
}
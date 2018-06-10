package br.com.danielsan.notafiscalcidada.features.main

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.util.TypedValue
import android.view.View
import android.view.ViewGroup
import br.com.danielsan.notafiscalcidada.R

/**
 * Created by daniel on 19/08/17.
 */
class TicketsCirclesAdapter : RecyclerView.Adapter<TicketsCirclesAdapter.CircleViewHolder>() {

    var invoicesCount = 0
        set(value) {
            if (field != value) {
                field = value
                notifyDataSetChanged()
            }
        }

    override fun getItemCount(): Int = 10

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CircleViewHolder {
        return CircleViewHolder(parent.context)
    }

    override fun onBindViewHolder(holder: CircleViewHolder, position: Int) {
        holder.itemView.setBackgroundResource(if (holder.adapterPosition < invoicesCount) {
            R.drawable.shape_circle_medium_aquamarine
        } else {
            R.drawable.shape_circle_gainsboro
        })
    }

    class CircleViewHolder(context: Context) : RecyclerView.ViewHolder(View(context)) {
        init {
            val size = TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 8F, context.resources.displayMetrics)
            itemView.layoutParams = RecyclerView.LayoutParams(size.toInt(), size.toInt())
            itemView.setBackgroundResource(R.drawable.shape_circle_gainsboro)
        }
    }

}

package br.com.danielsan.notafiscalcidada.shared.recyclerview

import android.support.v7.widget.RecyclerView

/**
 * Created by daniel on 01/10/17.
 */
abstract class BasicListAdapter<T, VH : BasicViewHolder<T>> : RecyclerView.Adapter<VH>() {

    var list = mutableListOf<T>()
        set(value) {
            if (value != field) {
                field = value
                notifyDataSetChanged()
            }
        }

    override fun getItemCount(): Int = list.size

    override fun onBindViewHolder(holder: VH, position: Int) {
        holder.bind(list[position])
    }

}

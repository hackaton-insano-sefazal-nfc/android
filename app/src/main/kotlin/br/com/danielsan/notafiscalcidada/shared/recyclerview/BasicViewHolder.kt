package br.com.danielsan.notafiscalcidada.shared.recyclerview

import android.support.v7.widget.RecyclerView
import android.view.View

/**
 * Created by daniel on 01/10/17.
 */
abstract class BasicViewHolder<T>(itemView: View) : RecyclerView.ViewHolder(itemView) {

    abstract fun bind(obj: T)

}

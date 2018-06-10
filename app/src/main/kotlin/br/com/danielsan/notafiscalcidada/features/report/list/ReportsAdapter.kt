package br.com.danielsan.notafiscalcidada.features.report.list

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import br.com.danielsan.notafiscalcidada.databinding.ItemReportBinding
import br.com.danielsan.notafiscalcidada.domain.report.Report

/**
 * Created by saturo on 20/08/17.
 */
class ReportsAdapter(val listResports: MutableList<Report>, val presenter: ReportsPresenter) :
        RecyclerView.Adapter<ReportsViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ReportsViewHolder {
        val layoutInflate = LayoutInflater.from(parent.context)
        return ReportsViewHolder(ItemReportBinding.inflate(layoutInflate, parent, false), presenter)
    }

    override fun getItemCount(): Int = listResports.size

    override fun onBindViewHolder(holder: ReportsViewHolder?, position: Int) {
        holder?.onBind(listResports[position])
    }

}
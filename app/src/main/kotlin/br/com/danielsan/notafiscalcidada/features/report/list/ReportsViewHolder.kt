package br.com.danielsan.notafiscalcidada.features.report.list

import android.support.v7.widget.RecyclerView
import br.com.danielsan.notafiscalcidada.Constants
import br.com.danielsan.notafiscalcidada.databinding.ItemReportBinding
import br.com.danielsan.notafiscalcidada.domain.report.Report
import java.util.*

/**
 * Created by saturo on 20/08/17.
 */
class ReportsViewHolder(
        private val binding: ItemReportBinding,
        private val presenter: ReportsPresenter
) : RecyclerView.ViewHolder(binding.root) {

    fun onBind(report: Report) {
        binding.apply {
            report.dateOfIssue = Constants.DATE_FORMAT.format(Date())
            date.text = report.dateOfIssue
            edit.setOnClickListener {
                presenter.onClickEdit(report)
            }
        }
    }

}

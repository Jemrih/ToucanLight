package com.toucan.light.toucanlightapplication.mvp.views.history.impl

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import com.toucan.light.toucanlightapplication.R
import com.toucan.light.toucanlightapplication.mvp.core.BaseViewHolder
import com.toucan.light.toucanlightapplication.mvp.views.history.impl.HistoryListTypes.*
import com.toucan.light.toucanlightapplication.utils.extensions.gone
import kotlinx.android.synthetic.main.view_history_list_content_item.view.*
import kotlinx.android.synthetic.main.view_history_list_small_header.view.*

class HistoryFragmentListAdapter(private val onClick: (HistoryListTypes) -> Unit) :
    ListAdapter<HistoryListTypes, BaseViewHolder<*>>(
        HistoryFragmentListDiffer()
    ) {

    //region Members
    private val typeHeader = 1
    private val typeSmallHeader = 2
    private val typeTransaction = 3
    //endregion

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder<*> {
        val inflater = LayoutInflater.from(parent.context)
        return when (viewType) {
            typeTransaction -> HistoryListTransactionViewHolder(
                inflater.inflate(
                    R.layout.view_history_list_content_item,
                    parent,
                    false
                )
            )
            typeHeader -> {
                HistoryListHeaderViewHolder(
                    inflater.inflate(
                        R.layout.view_history_list_content_item,
                        parent,
                        false
                    )
                )
            }
            typeSmallHeader -> {
                HistoryListSmallHeaderViewHolder(
                    inflater.inflate(
                        R.layout.view_history_list_small_header,
                        parent,
                        false
                    )
                )
            }
            else -> throw IllegalArgumentException("Type sent to history adapter is wrong")
        }
    }

    override fun onBindViewHolder(holder: BaseViewHolder<*>, position: Int) {
        val item = getItem(position)
        //It is safe to cast it to any because we know it works
        //but the compiler dosen't
        (holder as BaseViewHolder<Any>).bind(item)
    }

    override fun getItemViewType(position: Int): Int {
        return when (getItem(position)) {
            is SmallHeader -> typeSmallHeader
            is Header -> typeHeader
            is Transaction -> typeTransaction
        }
    }

    //region ViewHolders
    inner class HistoryListHeaderViewHolder(view: View) : BaseViewHolder<Header>(view) {
        private val header: TextView = view.txt_view_history_content_title
        private val description: TextView = view.txt_view_history_content_description
        private val amount: TextView = view.txt_view_history_content_amount

        override fun bind(item: Header) {
            amount.gone()
            header.text = item.header
            description.text = item.description
        }
    }

    inner class HistoryListSmallHeaderViewHolder(view: View) : BaseViewHolder<SmallHeader>(view) {
        private val header: TextView = view.txt_view_history_small_header

        override fun bind(item: SmallHeader) {
            header.text = item.header
        }
    }

    inner class HistoryListTransactionViewHolder(view: View) : BaseViewHolder<Transaction>(view) {
        private val header: TextView = view.txt_view_history_content_title
        private val description: TextView = view.txt_view_history_content_description
        private val amount: TextView = view.txt_view_history_content_amount

        override fun bind(item: Transaction) {
            header.text = item.header
            description.text = item.description
            amount.text = item.amount
            view.setOnClickListener { onClick.invoke(item) }
        }
    }
    //endregion
}

class HistoryFragmentListDiffer : DiffUtil.ItemCallback<HistoryListTypes>() {
    override fun areItemsTheSame(item1: HistoryListTypes, item2: HistoryListTypes): Boolean {
        return item1::class.java == item2::class.java
    }

    override fun areContentsTheSame(l1: HistoryListTypes, l2: HistoryListTypes): Boolean {
        return when (l1) {
            is SmallHeader -> l1.header == (l2 as SmallHeader).header
            is Header -> l1.header == (l2 as Header).header && l1.description == l2.description
            is Transaction -> l1.transactionId == (l2 as Transaction).transactionId
        }
    }
}
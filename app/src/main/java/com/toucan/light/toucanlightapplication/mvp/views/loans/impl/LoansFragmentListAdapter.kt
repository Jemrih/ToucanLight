package com.toucan.light.toucanlightapplication.mvp.views.loans.impl

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.toucan.light.toucanlightapplication.R
import com.toucan.light.toucanlightapplication.storage.entity.Loan
import com.toucan.light.toucanlightapplication.utils.extensions.gone
import kotlinx.android.synthetic.main.view_account_list_item.view.*


class LoansFragmentListAdapter(private val onClick: (Loan) -> Unit) :
    ListAdapter<Loan, LoansFragmentListAdapter.LoanFragmentListViewHolder>(
        LoansFragmentListDiffer()
    ) {

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): LoanFragmentListViewHolder {
        val inflater = LayoutInflater.from(parent.context)

        return LoanFragmentListViewHolder(
            inflater.inflate(
                R.layout.view_account_list_item,
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: LoanFragmentListViewHolder, position: Int) {
        val account = getItem(position)
        with(holder) {
            title.text = account.title
            accountNumber.text = account.accountNumber
            amount.text = account.amount

            itemView.setOnClickListener {
                onClick.invoke(account)
            }

            icon.setImageResource(R.drawable.ic_list)
            moreButton.gone()
        }
    }

    //region ViewHolders
    inner class LoanFragmentListViewHolder(view: View) :
        RecyclerView.ViewHolder(view) {
        val title = view.txt_view_account_list_item_title
        val accountNumber = view.txt_view_account_list_item_number
        val amount = view.txt_view_account_list_item_amount
        val icon = view.img_view_account_list_item_icon
        val moreButton = view.img_view_account_list_item_more
    }
    //endregion
}

class LoansFragmentListDiffer : DiffUtil.ItemCallback<Loan>() {
    override fun areItemsTheSame(l1: Loan, l2: Loan): Boolean {
        return l1.accountNumber == l2.accountNumber
    }

    override fun areContentsTheSame(l1: Loan, l2: Loan): Boolean {
        return (l1.accountNumber == l2.accountNumber) && (l1.amount == l2.amount)
    }
}
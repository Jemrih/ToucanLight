package com.toucan.light.toucanlightapplication.mvp.views.accounts.impl

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.toucan.light.toucanlightapplication.R
import com.toucan.light.toucanlightapplication.storage.entity.Account
import kotlinx.android.synthetic.main.view_account_list_item.view.*

class AccountsFragmentListAdapter(private val onClick: (Account) -> Unit) :
    ListAdapter<Account, AccountsFragmentListAdapter.AccountFragmentListViewHolder>(
        AccountsFragmentListDiffer()
    ) {

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): AccountFragmentListViewHolder {
        val inflater = LayoutInflater.from(parent.context)

        return AccountFragmentListViewHolder(
            inflater.inflate(
                R.layout.view_account_list_item,
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: AccountFragmentListViewHolder, position: Int) {
        val account = getItem(position)
        with(holder) {
            title.text = account.title
            accountNumber.text = account.accountNumber
            amount.text = account.amount

            itemView.setOnClickListener {
                onClick.invoke(account)
            }
        }
    }

    //region ViewHolders
    inner class AccountFragmentListViewHolder(private val view: View) :
        RecyclerView.ViewHolder(view) {
        val title = view.txt_view_account_list_item_title
        val accountNumber = view.txt_view_account_list_item_number
        val amount = view.txt_view_account_list_item_amount
    }
    //endregion
}

class AccountsFragmentListDiffer : DiffUtil.ItemCallback<Account>() {
    override fun areItemsTheSame(a1: Account, a2: Account): Boolean {
        return a1.accountNumber == a2.accountNumber
    }

    override fun areContentsTheSame(a1: Account, a2: Account): Boolean {
        return (a1.accountNumber == a2.accountNumber) && (a1.amount == a2.amount)
    }
}
package org.demo.archtree

import android.arch.lifecycle.ViewModel
import android.databinding.ViewDataBinding
import archtree.list.util.BindableListItem

class BindableListItemImpl(private val value: String): BindableListItem {

    override fun bind(viewModel: ViewModel?, binding: ViewDataBinding) {
        binding.setVariable(BR.value, value)
    }
}
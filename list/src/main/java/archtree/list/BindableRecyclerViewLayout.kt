package archtree.list

import android.arch.lifecycle.ViewModel
import android.content.Context
import android.databinding.BindingAdapter
import android.databinding.DataBindingUtil
import android.databinding.ViewDataBinding
import android.databinding.adapters.ListenerUtil
import android.support.annotation.LayoutRes
import android.support.v7.widget.RecyclerView
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.ViewGroup

class BindableRecyclerViewLayout : RecyclerView {

    constructor(context: Context) : super(context)

    constructor(context: Context, attrs: AttributeSet) : super(context, attrs)

    constructor(context: Context, attrs: AttributeSet, defStyleAttr: Int) : super(context, attrs, defStyleAttr)

    companion object {

        @JvmStatic
        @BindingAdapter("archtree_itemsSource", "archtree_itemTemplate")
        fun <T : BindableListItem> bindItemsSource(
                container: BindableRecyclerViewLayout,
                oldItems: List<T>?,
                @LayoutRes oldEntryLayout: Int,
                newItems: List<T>?,
                @LayoutRes newEntryLayout: Int) {
            bindItemsSource<T, ViewModel>(container, oldItems, oldEntryLayout,
                    null, newItems, newEntryLayout, null)
        }

        @JvmStatic
        @BindingAdapter("archtree_itemsSource", "archtree_itemTemplate", "archtree_parentDataContext")
        fun <T : BindableListItem, V : ViewModel> bindItemsSource(
                container: BindableRecyclerViewLayout,
                oldItems: List<T>?,
                @LayoutRes oldEntryLayout: Int,
                oldParentDataContext: V?,
                newItems: List<T>?,
                @LayoutRes newEntryLayout: Int,
                newParentDataContext: V?) {

            if (oldItems === newItems
                    && oldEntryLayout == newEntryLayout
                    && oldParentDataContext == newParentDataContext) {
                // Nothing changed
                return
            }

            val adapter = object : RecyclerView.Adapter<RecyclerView.ViewHolder>(), BindableListAdapter {

                override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
                    val binding = DataBindingUtil.inflate<ViewDataBinding>(
                            LayoutInflater.from(container.context),
                            newEntryLayout,
                            parent,
                            false
                    )

                    return DataContextAwareViewHolder<T, V>(binding)
                }

                @Suppress("UNCHECKED_CAST")
                override fun onBindViewHolder(viewHolder: RecyclerView.ViewHolder, position: Int) {
                    if (viewHolder is DataContextAwareViewHolder<*, *>) {
                        (viewHolder as DataContextAwareViewHolder<T, V>).bind(newItems!![position], newParentDataContext)
                    }
                }

                override fun getItemCount(): Int {
                    return newItems?.size ?: 0
                }
            }

            val listener: OnListChangedCallbackAdapter<T>? = ListenerUtil.getListener(container, R.id.listChangedListener)
            BindableListUtil.initialiseListBinding(oldItems, newItems, listener, adapter, container)
            container.adapter = adapter
        }
    }
}

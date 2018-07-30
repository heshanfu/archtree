package archtree.action

import android.databinding.BindingAdapter
import android.view.View

class ActionAdapter {

    companion object {

        @JvmStatic
        @BindingAdapter("action", "actionParameter")
        fun <T> setAction(view: View, action: Action<T>?, actionParameter: T?) {
            action?.let {
                view.setOnClickListener(ActionListener(view, it, actionParameter))
            } ?: throw IllegalStateException("Action object should not be null.")
        }

        @JvmStatic
        @BindingAdapter("action")
        fun setAction(view: View, action: Action<Any>?) {
            setAction(view, action, null)
        }
    }

    private class ActionListener<T>
    internal constructor(private val view: View, private val action: Action<T>,
                         private val actionParameter: T?) : View.OnClickListener, OnConditionChangedListener<T> {

        init {
            this.action.listeners.add(this)
            this.updateView()
        }

        private fun updateView() {
            this.view.isEnabled = this.action.canExecute(this.actionParameter)
        }

        override fun onConditionChanged(action: Action<T>) {
            this.updateView()
        }

        override fun onClick(view: View) {
            if (this.action.canExecute(this.actionParameter)) {
                this.action.execute(this.actionParameter)
            }
        }
    }
}

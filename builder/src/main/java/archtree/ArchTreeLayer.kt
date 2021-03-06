package archtree

import android.databinding.ViewDataBinding
import android.os.Bundle
import android.support.annotation.CallSuper
import android.view.View
import archtree.viewmodel.BaseViewModel

abstract class ArchTreeLayer<in ViewModel : BaseViewModel> {

    @CallSuper
    open fun onResume(viewModel: ViewModel?, binding: ViewDataBinding?, bundle: Bundle?) {
        //do nothing by default
    }

    @CallSuper
    open fun onResume(viewModel: ViewModel?, view: View?, bundle: Bundle?) {
        //do nothing by default
    }

    @CallSuper
    open fun onCreate(viewModel: ViewModel?, binding: ViewDataBinding?, bundle: Bundle?) {
        //do nothing by default
    }

    @CallSuper
    open fun onCreate(viewModel: ViewModel?, view: View?, bundle: Bundle?) {
        //do nothing by default
    }

    @CallSuper
    open fun onStop(viewModel: ViewModel?, binding: ViewDataBinding?) {
        //do nothing by default
    }

    @CallSuper
    open fun onStop(viewModel: ViewModel?, view: View?) {
        //do nothing by default
    }
}
package org.archtree.activity

import org.archtree.ArchTreeBuilder
import org.archtree.viewmodel.BaseViewModel
import org.autotarget.util.HasFragmentFlow

open class ActivityBuilder<ViewModel : BaseViewModel> : ArchTreeBuilder<ViewModel, ActivityBuilder<ViewModel>>() {

    var fragmentFlow: HasFragmentFlow? = null
        private set

    open fun setFragmentFlow(fragmentFlow: HasFragmentFlow): ActivityBuilder<ViewModel> {
        this.fragmentFlow = fragmentFlow
        return this
    }

    open fun build(layer: ActivityLayer<ViewModel>): ActivityResource<ViewModel> {
        internalBuild(layer)
        return ActivityResource(this)
    }

    open fun build(): ActivityResource<ViewModel> {
        internalBuild(object: ActivityLayer<ViewModel>(){

        })
        return ActivityResource(this)
    }
}

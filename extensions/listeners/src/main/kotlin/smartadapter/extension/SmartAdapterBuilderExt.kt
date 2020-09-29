package smartadapter.extension

import smartadapter.SmartAdapterBuilder
import smartadapter.SmartRecyclerAdapter
import smartadapter.SmartViewHolderBinder
import smartadapter.listeners.OnViewEventListener
import smartadapter.listeners.ViewEventViewModel

fun SmartAdapterBuilder.add(viewModel: OnViewEventListener<*>): SmartAdapterBuilder {
    addBinder(viewModel)
    return this
}

fun SmartRecyclerAdapter.add(viewModel: OnViewEventListener<*>) {
    addBinder(viewModel)
}

fun SmartAdapterBuilder.add(smartViewHolderBinder: SmartViewHolderBinder): SmartAdapterBuilder {
    addBinder(smartViewHolderBinder)
    return this
}

fun SmartRecyclerAdapter.add(smartViewHolderBinder: SmartViewHolderBinder) {
    addBinder(smartViewHolderBinder)
}

fun SmartAdapterBuilder.add(smartViewHolderBinder: ViewEventViewModel<*>): SmartAdapterBuilder {
    addBinder(smartViewHolderBinder.viewEventListener)
    return this
}

fun SmartRecyclerAdapter.add(smartViewHolderBinder: ViewEventViewModel<*>) {
    addBinder(smartViewHolderBinder.viewEventListener)
}

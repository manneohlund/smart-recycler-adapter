package smartadapter.viewevent.extension

import smartadapter.SmartAdapterBuilder
import smartadapter.SmartRecyclerAdapter
import smartadapter.SmartViewHolderBinder
import smartadapter.viewevent.viewmodel.ViewEventViewModel

fun SmartAdapterBuilder.add(smartViewHolderBinder: ViewEventViewModel<*, *>): SmartAdapterBuilder {
    addBinder(smartViewHolderBinder.viewEventListener as SmartViewHolderBinder)
    return this
}

fun SmartRecyclerAdapter.add(smartViewHolderBinder: ViewEventViewModel<*, *>) {
    addBinder(smartViewHolderBinder.viewEventListener as SmartViewHolderBinder)
}

fun <T : SmartViewHolderBinder> SmartAdapterBuilder.add(smartViewHolderBinder: T): SmartAdapterBuilder {
    addBinder(smartViewHolderBinder)
    return this
}

fun <T : SmartViewHolderBinder> SmartRecyclerAdapter.add(smartViewHolderBinder: T) {
    addBinder(smartViewHolderBinder)
}

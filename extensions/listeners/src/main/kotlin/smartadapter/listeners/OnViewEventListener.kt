package smartadapter.listeners

import android.view.View
import androidx.annotation.IdRes
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import io.github.manneohlund.smartrecycleradapter.listeners.R
import smartadapter.SmartViewHolderBinder
import smartadapter.ViewId
import smartadapter.viewholder.SmartViewHolder

/**
 * Basic definition of an event listener.
 */
interface ViewEventListener<T : ViewEvent> {

    fun observe(lifecycle: LifecycleOwner, observer: Observer<T>): OnViewEventListener<T>

    fun observe(lifecycle: LifecycleOwner, listener: (T) -> Unit): OnViewEventListener<T>

    fun removeObserver(observer: Observer<T>)

    fun removeObservers(lifecycleOwner: LifecycleOwner)
}

/**
 * Base event listener for [ViewEvent].
 */
open class OnViewEventListener<T : ViewEvent> : ViewEventListener<T>, SmartViewHolderBinder {

    internal val eventListener by lazy(mode = LazyThreadSafetyMode.PUBLICATION) {
        MutableLiveData<T>()
    }

    override fun observe(
        lifecycle: LifecycleOwner,
        observer: Observer<T>
    ): OnViewEventListener<T> {
        eventListener.observe(lifecycle, observer)
        return this
    }

    override fun observe(
        lifecycle: LifecycleOwner,
        listener: (T) -> Unit
    ): OnViewEventListener<T> {
        eventListener.observe(lifecycle, Observer {
            listener.invoke(it)
        })
        return this
    }

    override fun removeObserver(
        observer: Observer<T>
    ) = eventListener.removeObserver(observer)

    override fun removeObservers(
        lifecycleOwner: LifecycleOwner
    ) = eventListener.removeObservers(lifecycleOwner)
}

internal fun OnViewEventListener<*>.findView(
    @IdRes id: ViewId,
    smartViewHolder: SmartViewHolder<Any>
) = when (id) {
    R.id.undefined -> smartViewHolder.itemView
    else -> smartViewHolder.itemView.findViewById<View>(id)
}

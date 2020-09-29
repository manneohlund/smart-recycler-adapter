package smartadapter.listeners

import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel

/**
 * Basic view model that wraps an [OnViewEventListener].
 */
open class ViewEventViewModel<T : OnViewEventListener<ViewEvent>>(
    val viewEventListener: T
) : ViewModel(), ViewEventListener<ViewEvent> {

    override fun observe(
        lifecycle: LifecycleOwner,
        observer: Observer<ViewEvent>
    ): T = viewEventListener.observe(lifecycle, observer) as T

    override fun observe(
        lifecycle: LifecycleOwner,
        listener: (ViewEvent) -> Unit
    ): T = viewEventListener.observe(lifecycle, Observer {
        listener.invoke(it)
    }) as T

    override fun removeObserver(observer: Observer<ViewEvent>) =
        viewEventListener.removeObserver(observer)

    override fun removeObservers(lifecycleOwner: LifecycleOwner) =
        viewEventListener.removeObservers(lifecycleOwner)
}

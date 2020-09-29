package smartadapter.listeners

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.Observer

/**
 * Basic android view model that wraps an [OnViewEventListener].
 */
open class ViewEventApplicationViewModel<T : OnViewEventListener<ViewEvent>>(
    application: Application,
    val viewEventListener: T
) : AndroidViewModel(application), ViewEventListener<ViewEvent> {

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

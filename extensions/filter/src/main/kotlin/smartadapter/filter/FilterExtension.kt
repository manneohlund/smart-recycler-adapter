package smartadapter.filter

import androidx.lifecycle.LifecycleCoroutineScope
import androidx.recyclerview.widget.RecyclerView
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import smartadapter.SmartRecyclerAdapter
import smartadapter.extension.SmartRecyclerAdapterBinder
import kotlin.reflect.KClass

class FilterExtension(
    override val identifier: Any = FilterExtension::class,
    val filterPredicate: (item: Any, query: CharSequence) -> Boolean,
    val targetFilterTypes: List<KClass<*>> = listOf(),
    val fastReset: Boolean = false,
    val loadingStateListener: (isLoading: Boolean) -> Unit = {}
) : SmartRecyclerAdapterBinder {

    private lateinit var smartRecyclerAdapter: SmartRecyclerAdapter
    private var items: MutableList<Any> = mutableListOf()
    private var filterJob: Job? = null
    private val observer = object : RecyclerView.AdapterDataObserver() {
        override fun onChanged() {
            items = smartRecyclerAdapter.getItems()
        }
    }

    fun filter(
        lifecycleScope: LifecycleCoroutineScope,
        constraint: CharSequence?,
        autoSetNewItems: Boolean = false,
        result: ((Result<List<Any>>) -> Unit)? = {}
    ) {
        cancelFilterJob()

        if (constraint == null) {
            return
        }

        if (fastReset && (constraint.isBlank())) {
            smartRecyclerAdapter.setItems(items)
            result?.invoke(Result.failure(Exception("Empty query, set initial list and returning!")))
            return
        }

        loadingStateListener.invoke(true)

        filterJob = lifecycleScope.launch(Dispatchers.IO) {
            val filterItems = items.filter {
                if (targetFilterTypes.isEmpty() || it::class in targetFilterTypes) {
                    filterPredicate.invoke(it, constraint)
                } else {
                    true
                }
            }
            withContext(Dispatchers.Main) {
                loadingStateListener.invoke(false)
                if (filterJob?.isCancelled == false) {
                    result?.invoke(Result.success(filterItems))
                    if (autoSetNewItems) {
                        smartRecyclerAdapter.unregisterAdapterDataObserver(observer)
                        smartRecyclerAdapter.setItems(filterItems as MutableList<*>)
                        smartRecyclerAdapter.registerAdapterDataObserver(observer)
                    }
                }
            }
        }
    }

    fun cancelFilterJob() {
        filterJob?.cancel()
    }

    override fun bind(smartRecyclerAdapter: SmartRecyclerAdapter) {
        this.smartRecyclerAdapter = smartRecyclerAdapter
        items = smartRecyclerAdapter.getItems()
        this.smartRecyclerAdapter.registerAdapterDataObserver(observer)
    }
}
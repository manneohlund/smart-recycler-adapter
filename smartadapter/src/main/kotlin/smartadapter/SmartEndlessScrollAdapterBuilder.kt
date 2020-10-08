package smartadapter

class SmartEndlessScrollAdapterBuilder : SmartAdapterBuilder() {

    override fun getSmartRecyclerAdapter(): SmartRecyclerAdapter {
        return SmartEndlessScrollRecyclerAdapter(items)
    }
}

package smartadapter.viewevent.listeners

import smartadapter.viewevent.models.ViewEvent

interface OnViewEventListener<T : ViewEvent> {

    var eventListener: (T) -> Unit
}
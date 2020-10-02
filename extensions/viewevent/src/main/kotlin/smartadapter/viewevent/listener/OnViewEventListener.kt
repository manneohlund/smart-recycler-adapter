package smartadapter.viewevent.listener

import smartadapter.viewevent.model.ViewEvent

interface OnViewEventListener<T : ViewEvent> {

    var eventListener: (T) -> Unit
}
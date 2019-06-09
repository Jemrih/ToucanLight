package com.toucan.light.toucanlightapplication.eventbus

import io.reactivex.Observable
import io.reactivex.subjects.PublishSubject

class RxBus {
    private val subject = PublishSubject.create<Event>()

    /** Pass any event down to event listeners.  */
    fun send(event: Event) {
        
        subject.onNext(event)
    }

    /** Subscribe to specific events from the stream  */
    fun getEvents(eventId: Int): Observable<Event> {
        return subject.filter { event -> event.id == eventId }
    }

    /** Events id */
    companion object {
    
    }
}


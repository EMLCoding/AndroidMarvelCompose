package com.emlcoding.marvelcompose.repositories

import com.emlcoding.marvelcompose.models.Event
import com.emlcoding.marvelcompose.network.api.ApiClient
import com.emlcoding.marvelcompose.network.models.Result

object EventsRepository: Repository<Event>() {

    suspend fun get(): Result<List<Event>> = super.get {
        ApiClient
            .eventsService
            .getEvents(0, 100)
            .data
            .results
            .map { it.asEvent() }
    }

    suspend fun find(id: Int): Result<Event> = super.find(
        id = id,
        findActionRemote = {
            ApiClient
                .eventsService
                .findEvent(id)
                .data
                .results
                .first()
                .asEvent()
        }
    )
}
package edu.ucne.tickets.data.remote

import edu.ucne.tickets.data.remote.dto.EventDto
import retrofit2.Response
import retrofit2.http.*

interface EventApi {
    @GET("events/")
    suspend fun GetListEvent(): List<EventDto>

    @GET("events?id={id}/")
    suspend fun GetEvent(@Path("id") id: Int): EventDto

    @PUT("events?id={id}/")
    suspend fun PutEvent(@Path("id") id: Int, @Body eventDto: EventDto): Response<Unit>
}
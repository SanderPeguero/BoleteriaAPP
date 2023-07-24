package edu.ucne.tickets.data.remote.repository

import edu.ucne.tickets.data.remote.EventApi
import edu.ucne.tickets.data.remote.dto.EventDto
import edu.ucne.tickets.utils.Resources
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.Flow
import java.io.IOException
import javax.inject.Inject

class EventRepository @Inject constructor(
    private val evento : EventApi
) {
    fun getListTickets(): Flow<Resources<List<EventDto>>> = flow {
        try {
            emit(Resources.Loading())
            emit(Resources.Success(evento.GetListEvent()))
        } catch (e: IOException) {
            emit(Resources.Error(e.message ?: "verificar tu conexion a internet"))
        }
    }

    suspend fun getTicket(id: Int): EventDto {
        try {
            return evento.GetEvent(id)
        } catch (e: Exception) {
            throw e
        }
    }

    suspend fun putTickets(id: Int, eventDto: EventDto, eventoDto: EventDto) {
        evento.PutEvent(id, eventDto)
    }
}
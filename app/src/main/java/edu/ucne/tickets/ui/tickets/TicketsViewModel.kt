package edu.ucne.tickets.ui.tickets

import javax.inject.Inject
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import edu.ucne.tickets.data.remote.dto.EventDto
import edu.ucne.tickets.data.remote.repository.EventRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

data class EventoUiState(
    val evento: EventDto = EventDto(
        0,
        "",
        "",
        "",
        0,
        0,
        0,
        0,
        0,
        0,
        0,
        0
    )
)

@HiltViewModel
class EventoSelectedViewModel @Inject constructor(
    val repository: EventRepository
) : ViewModel() {

    private val _uiState = MutableStateFlow(EventoUiState())
    val uiState: StateFlow<EventoUiState> = _uiState.asStateFlow()

    var id by mutableStateOf(0)

    fun getById(id: Int) {
        viewModelScope.launch {
            _uiState.update {
                it.copy(evento = repository.getTicket(id))
            }
        }
    }

    /*fun save(eventoDto: EventDto) {
        viewModelScope.launch {
            repository.putTickets(id, eventoDto = eventoDto)
        }
    }*/
}
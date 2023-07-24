package edu.ucne.tickets.ui.ticket_list

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import javax.inject.Inject
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import edu.ucne.tickets.utils.Resources
import edu.ucne.tickets.data.remote.dto.EventDto
import edu.ucne.tickets.data.remote.repository.EventRepository
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach

data class EventListUiState(
    val isLoading : Boolean = false,
    val eventos: List<EventDto> = emptyList(),
    val error : String = ""
)

@HiltViewModel
class TicketListViewModel @Inject constructor(
    repository: EventRepository
): ViewModel() {

    private val _uiState = MutableStateFlow(EventListUiState())
    val uiState: StateFlow<EventListUiState> = _uiState.asStateFlow()

    var id by mutableStateOf(0)

    init {
        repository.getListTickets().onEach { result ->
            when(result) {
                is Resources.Loading -> {
                    _uiState.value = EventListUiState(isLoading = true)
                }
                is Resources.Success -> {
                    _uiState.value = EventListUiState(eventos = result.data ?: emptyList())
                }
                is Resources.Error -> {
                    _uiState.value = EventListUiState(error = result.message ?: "Error")
                }
            }
        }.launchIn(viewModelScope)
    }
}
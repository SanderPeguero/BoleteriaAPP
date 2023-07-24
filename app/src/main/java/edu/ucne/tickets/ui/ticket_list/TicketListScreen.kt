package edu.ucne.tickets.ui.ticket_list

import android.annotation.SuppressLint
import android.os.Build
import android.widget.Toast
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.*
import androidx.compose.material.*
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import coil.compose.AsyncImage
import coil.request.ImageRequest
import edu.ucne.tickets.data.remote.dto.EventDto
import edu.ucne.tickets.ui.tickets.TicketScreen

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TicketListScreen(
    viewModel: TicketListViewModel = hiltViewModel(),
    onClickSelected: (Int) -> Unit
) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Row(
                        modifier = Modifier.fillMaxSize(),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.Center
                    ) {
                        Text(text = "Lista de tickets", fontSize = 16.sp)
                    }
                },
                modifier = Modifier.border(width = 1.dp, color = Color.White)
            )
        }
    ) {
        val uiState by viewModel.uiState.collectAsState()

        Column(modifier = Modifier.fillMaxSize(), horizontalAlignment = Alignment.CenterHorizontally) {
            if(uiState.isLoading)
                CircularProgressIndicator(modifier = Modifier
                    .size(80.dp)
                    .padding(0.dp, 50.dp), strokeWidth = 8.dp)

            EventList(
                eventos = uiState.eventos,
                viewModel = viewModel,
                modifier = Modifier
                    .fillMaxSize()
            ){
                onClickSelected(it)
            }
        }
    }
}

@Composable
private fun EventList(
    eventos: List<EventDto>,
    viewModel: TicketListViewModel,
    modifier: Modifier = Modifier,
    onClickSelected: (Int) -> Unit
) {
    LazyColumn(modifier = modifier) {

        items(eventos) { evento ->
            IconButton(
                onClick = { onClickSelected(evento.Id) },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(130.dp)
                    .padding(5.dp, 5.dp)
            ) {
                SelectedEvent(evento = evento, viewModel)
            }
        }
    }
}

@Composable
private fun SelectedEvent(
    evento: EventDto,
    viewModel: TicketListViewModel
) {
    val context = LocalContext.current
    Card(
        modifier = Modifier
            .fillMaxSize()
            .height(250.dp),
        elevation = CardDefaults.elevatedCardElevation(2.dp)
    ) {
        Column(modifier = Modifier.fillMaxSize()) {
            Card(
                modifier = Modifier
                    .fillMaxHeight()
                    .width(130.dp),
                colors = CardDefaults.cardColors(Color.Cyan)
            ) {
                AsyncImage(
                    model = ImageRequest.Builder(LocalContext.current)
                        .data(evento.Image)
                        .crossfade(true)
                        .build(),
                    contentDescription = null,
                    contentScale = ContentScale.Crop,
                    modifier = Modifier.fillMaxSize()
                )
            }

            Spacer(modifier = Modifier.padding(3.dp, 0.dp))

            Column(modifier = Modifier.fillMaxSize()) {
                Text(
                    text = evento.Name,
                    fontWeight = FontWeight.Black,
                    textAlign = TextAlign.Left,
                    fontSize = 14.sp
                )

                Text(
                    text = evento.Date,
                    textAlign = TextAlign.Left,
                    fontSize = 12.sp
                )

                Row(
                    Modifier.fillMaxSize(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                }
            }
        }
    }
}
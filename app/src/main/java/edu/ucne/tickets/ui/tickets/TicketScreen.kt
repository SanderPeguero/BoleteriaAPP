package edu.ucne.tickets.ui.tickets

import android.annotation.SuppressLint
import android.widget.Toast
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.*
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TicketScreen(
    id: Int = 0,
    viewModel: EventoSelectedViewModel = hiltViewModel(),
    onNavigateBack: () -> Unit
) {
    remember {
        viewModel.getById(id)
        0
    }

    val uiState by viewModel.uiState.collectAsState()

    val scrollState = rememberScrollState()
        Scaffold(
            topBar = {
                TopAppBar(
                    title = {
                        Row(
                            modifier = Modifier.fillMaxSize(),
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.Center
                        ) {
                            Text(text = "Tickets", fontSize = 16.sp)
                        }
                    }
                )
            }
        ) {
            Spacer(modifier = Modifier.height(80.dp))

            /*Column {
                Spacer(modifier = Modifier.height(50.dp))
                Banner()
                Spacer(modifier = Modifier.height(10.dp))
                Titulo(uiState.evento)
                Spacer(modifier = Modifier.height(10.dp))

                Column(modifier = Modifier.verticalScroll(scrollState)) {
                    Descripcion(uiState.evento)
                }
            }*/
        }
}
/*
@Composable
private fun Banner() {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(130.dp)
    ) {
        Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Top
        ) {
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(90.dp)
                    .padding(4.dp, 0.dp),
                shape = RoundedCornerShape(20.dp)
            ) {}
        }

        Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Bottom,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Card(
                modifier = Modifier.size(90.dp),
                border = BorderStroke(3.dp, Color.White),
                shape = RoundedCornerShape(35.dp),
                colors = CardDefaults.cardColors(Color.LightGray)
            ) {
            }
        }
    }
}

@Composable
private fun Titulo(evento: EventoDto) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(4.dp, 0.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = evento.nombre,
            fontWeight = FontWeight.Black,
            textAlign = TextAlign.Center,
            fontSize = 18.sp
        )
    }
}

@Composable
private fun Descripcion(evento: EventoDto) {
    Column(
        modifier = Modifier.padding(4.dp, 0.dp),
        horizontalAlignment = Alignment.Start
    ) {
        Text(text = "Fecha:", fontWeight = FontWeight.Black, fontSize = 14.sp)
        Text(text = "   " + evento.fecha.format("dd/MM/yyyy HH:mm") + "\n", fontSize = 12.sp)
        Text(text = "Dirección:", fontWeight = FontWeight.Black, fontSize = 14.sp)
        Text(text = "   " + evento.direccion + "\n", fontSize = 12.sp)
        Text(text = "Descripción: \n", fontWeight = FontWeight.Black, fontSize = 14.sp)
        Text(text = "   " + evento.descripcion + "\n", fontSize = 12.sp)
    }
}

@Composable
private fun Boletas(
    boletos: BoletosDto,
    viewModel: EventoSelectedViewModel = hiltViewModel()
) {
    val context = LocalContext.current
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(2.dp, 0.dp),
    ){
        Column(modifier = Modifier.fillMaxSize()) {
            Text(
                text = boletos.nombre,
                fontWeight = FontWeight.Black,
                textAlign = TextAlign.Left,
                fontSize = 14.sp
            )

            Text(
                text = "" + boletos.cantidad,
                textAlign = TextAlign.Left,
                fontSize = 12.sp
            )
            Button(onClick = {
                viewModel.id = boletos.id
                boletos.cantidad.dec()
                viewModel.save(boletos)
                Toast.makeText(
                    context,
                    "Se ha guardado la propuesta de empleo con exito 👍",
                    Toast.LENGTH_SHORT
                ).show()
            }) {
                Text(text = "Reservar")
            }
        }
    }
}*/
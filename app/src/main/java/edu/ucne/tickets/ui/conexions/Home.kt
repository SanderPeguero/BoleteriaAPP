package edu.ucne.tickets.ui.conexions

import android.annotation.SuppressLint
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.*
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import edu.ucne.tickets.ui.ticket_list.TicketListScreen
import edu.ucne.tickets.ui.ticket_list.TicketListViewModel
import edu.ucne.tickets.ui.tickets.TicketScreen

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Home(navController: NavController) {
        navController.popBackStack()
        val navControllerMenu = rememberNavController()

        Scaffold (
            modifier = Modifier.fillMaxSize(),
            containerColor = MaterialTheme.colorScheme.background
        ) {
            Menu(navController = navControllerMenu)
        }
}

@Composable
private fun Menu(navController: NavHostController) {
    NavHost(
        navController = navController,
        startDestination = Screen.TicketListScreen.route
    ) {
        composable(Screen.TicketListScreen.route) {
            TicketListScreen () {
                navController.navigate(Screen.TicketScreen.route + "/$it")
            }
        }

        composable(
            Screen.TicketScreen.route + "/{id}",
            arguments = listOf(navArgument("id") {type = NavType.IntType })
        ) {
            val id = it.arguments?.getInt("id") ?: 0
            TicketScreen(
                id = id,
                onNavigateBack = { navController.navigateUp() }
            )
        }
    }
}
package edu.ucne.tickets.ui.conexions

sealed class Screen(val route: String) {
    object Home: Screen("Home")
    object TicketListScreen: Screen("Eventos")
    object TicketScreen: Screen("Tickets")
}
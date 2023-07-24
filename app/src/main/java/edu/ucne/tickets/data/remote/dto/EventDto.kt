package edu.ucne.tickets.data.remote.dto

data class EventDto(
    val Id: Int,
    val Name: String,
    val Date: String,
    val Image: String,
    val TotalTickets: Int,
    val TicketsSold: Int,
    val VIPTickets: Int,
    val RegularTickets: Int,
    val RegularTicketsPrice: Int,
    val SpecialGuestTickets: Int,
    val SpecialGuestTicketsPrice: Int,
    val GuestTickets: Int,
)
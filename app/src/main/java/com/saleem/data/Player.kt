package com.saleem.data

data class Player(
    val id: String = "",
    val name: String = "",
    val teamId: String = "",
    val number: String = "",
    val position: String = "",
    val photoUrl: String = "",
    val rating: Double = 0.0,
    val transferPrice: Double = 0.0,
    val cardType: String = "Free",
    val country: String = "",
    val rewards: Double = 0.0,

) {
}
package com.saleem.data

data class FantasyTeam(
    val id: String = "",
    val name: String = "",
    val formation: String = "",
    val roster: List<Player> = emptyList(),
    val totalPoints: Int = 0,
    val totalValue: Int = 0
)

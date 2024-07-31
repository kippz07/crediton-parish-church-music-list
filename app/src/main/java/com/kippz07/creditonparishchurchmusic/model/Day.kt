package com.kippz07.creditonparishchurchmusic.model

import java.io.Serializable
import java.time.LocalDate

data class Day(
    val date: LocalDate,
    val serviceType: String,
    val music: List<Piece>
) : Serializable
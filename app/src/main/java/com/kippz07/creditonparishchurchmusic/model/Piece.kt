package com.kippz07.creditonparishchurchmusic.model

import java.io.Serializable
import java.time.LocalDate


data class Piece(
    val date: LocalDate,
    val service: String,
    val type: String,
    val title: String,
    val composer: String?,
    val link: String?
) : Serializable
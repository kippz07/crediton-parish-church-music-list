package com.kippz07.creditonparishchurchmusic.model

import android.icu.lang.UCharacter.GraphemeClusterBreak.T
import com.kippz07.creditonparishchurchmusic.R
import com.opencsv.CSVReaderHeaderAware
import java.io.FileReader
import java.io.InputStream
import java.time.LocalDate

class Service(val resource: InputStream) {

    fun readCsv(): MutableList<Day> {
        val reader = resource.bufferedReader()
        val header = reader.readLine()
        val grouped = reader.lineSequence()
            .filter { it.isNotBlank() }
            .map {
                val (date, service, type, title, composer, link) = it.split(',', ignoreCase = false)
                Piece(LocalDate.parse(date), service, type, title, composer, link)
            }.toList()
            .groupBy { listOf(it.date, it.service) }
//        val nlSplit = resource.split("\n").drop(1)
//        val grouped = nlSplit.map {
//            val (date, service, type, title, composer, link) = it.split(',', ignoreCase = false)
//                Piece(LocalDate.parse(date), service, type, title, composer, link)
//            }.toList()
//            .groupBy { listOf(it.date, it.service) }

        val serviceList = mutableListOf<Day>()

        grouped.forEach { (key, value) ->
            serviceList.add(
                Day(
                    LocalDate.parse(key[0].toString()),
                    key[1].toString(),
                    value
                )
            )
        }
        serviceList.sortBy {it.date}
        return serviceList
    }

}

class ServiceNew(val resource: String) {
    fun parseServiceCsv(): MutableList<Day> {
        val nlSplit = resource.replace("\"", "").split("\n").drop(1)
        val grouped = nlSplit.map {
            val (date, service, type, title, composer, link) = it.split(',', ignoreCase = false)
                Piece(LocalDate.parse(date), service, type, title, composer, link)
            }.toList()
            .groupBy { listOf(it.date, it.service) }

        val serviceList = mutableListOf<Day>()

        grouped.forEach { (key, value) ->
            serviceList.add(
                Day(
                    LocalDate.parse(key[0].toString()),
                    key[1].toString(),
                    value
                )
            )
        }
        serviceList.sortBy {it.date}
        return serviceList
    }
}

class ServiceDbObj(val resource: String) {
    fun parseServiceCsv(): MutableList<Day> {
        val nlSplit = resource.replace("\"", "").split("\n").drop(1)
        val grouped = nlSplit.map {
            val (date, service, type, title, composer, link) = it.split(',', ignoreCase = false)
            Piece(LocalDate.parse(date), service, type, title, composer, link)
        }.toList()
            .groupBy { listOf(it.date, it.service) }

        val serviceList = mutableListOf<Day>()

        grouped.forEach { (key, value) ->
            serviceList.add(
                Day(
                    LocalDate.parse(key[0].toString()),
                    key[1].toString(),
                    value
                )
            )
        }
        serviceList.sortBy {it.date}
        return serviceList
    }
}

operator fun <T> List<T>.component6(): T = get(5)


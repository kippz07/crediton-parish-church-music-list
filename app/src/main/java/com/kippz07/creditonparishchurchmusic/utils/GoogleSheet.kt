package com.kippz07.creditonparishchurchmusic.utils

import io.ktor.client.*
import io.ktor.client.call.*
import io.ktor.client.engine.android.*
import io.ktor.client.request.*
import io.ktor.client.statement.*
import io.ktor.http.*
import java.net.Proxy
import java.net.InetSocketAddress
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext


val musicLink ="https://docs.google.com/spreadsheets/d/1r71O_Bm_-dkKBTtyAPMYMfhh1lg5-MwypKAnEs2eYkQ/gviz/tq?tqx=out:csv&sheet=schedule"



suspend fun fetchData(): String {
    return withContext(Dispatchers.IO) {
        var stringBody: String = ""

        val client = HttpClient(Android) {
            engine {
                // this: AndroidEngineConfig
                connectTimeout = 100_000
                socketTimeout = 100_000
//                proxy = Proxy(Proxy.Type.HTTP, InetSocketAddress("localhost", 8080))
            }
        }

        val status = client.use { client ->
            val response: HttpResponse = client.get(musicLink)
            stringBody = response.body()
        }

        return@withContext stringBody
    }

}



package com.kippz07.creditonparishchurchmusic


import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.CountDownTimer
import android.widget.Button
import android.widget.Toast
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.RecyclerView
import com.kippz07.creditonparishchurchmusic.adapter.MainItemAdapter
import com.kippz07.creditonparishchurchmusic.dao.DatabaseBuilder
import com.kippz07.creditonparishchurchmusic.dao.MusicEntity
import com.kippz07.creditonparishchurchmusic.dao.ServiceEntity
import com.kippz07.creditonparishchurchmusic.model.Day
import com.kippz07.creditonparishchurchmusic.model.ServiceNew
import com.kippz07.creditonparishchurchmusic.utils.fetchData
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {
    lateinit var resource: String
    var refreshDisabled = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val refreshTimer = object : CountDownTimer(5000, 1000) {
            override fun onTick(p0: Long) {}

            override fun onFinish() {
                refreshDisabled = false
//                Toast.makeText(applicationContext, "Refresh enabled", Toast.LENGTH_SHORT).show()
            }
        }

        findViewById<Button>(R.id.button_refresh)
            .setOnClickListener {
                if (!refreshDisabled) {
//                    Toast.makeText(applicationContext, "Refresh disabled", Toast.LENGTH_SHORT).show()
                    refreshDisabled = true
                    lifecycleScope.launch(Dispatchers.Main) {
                        resource = fetchData()
                        val data = parseData(resource)
                        addService(data)
                        displayData()
                    }
                    refreshTimer.start()
                }
            }

        lifecycleScope.launch(Dispatchers.Main) {
            displayData()
        }

    }

    private fun parseData(resource: String): List<Day> {
        val service = ServiceNew(resource)
        val serviceList = service.parseServiceCsv()
        return serviceList
    }

    private suspend fun displayData() {
        val serviceRecyclerView = findViewById<RecyclerView>(R.id.main_recycler_view)
        val serviceList = DatabaseBuilder.getInstance(applicationContext).ServiceDao().getAll()
        serviceRecyclerView.adapter = MainItemAdapter(this, serviceList)
        serviceRecyclerView.setHasFixedSize(true)
    }

    private suspend fun addService(serviceList: List<Day>) {
        if (serviceList.isEmpty()) {
            return
        }
        DatabaseBuilder.getInstance(applicationContext).ServiceDao().deleteAll()
        DatabaseBuilder.getInstance(applicationContext).MusicDao().deleteAll()
        serviceList.forEach{(date, name, music) ->
            val entity = ServiceEntity(date = date, serviceType = name)
            DatabaseBuilder.getInstance(applicationContext).ServiceDao().insert(entity)

            music.forEach{
                val musicEntity = MusicEntity(
                    serviceId = 0,
                    composer = it.composer,
                    date = it.date,
                    link = it.link,
                    serviceType = it.service,
                    title = it.title,
                    type = it.type
                )
                DatabaseBuilder.getInstance(applicationContext).MusicDao().insert(musicEntity)
            }
        }
    }
}
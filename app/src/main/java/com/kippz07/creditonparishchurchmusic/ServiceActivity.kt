package com.kippz07.creditonparishchurchmusic

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.RecyclerView
import com.kippz07.creditonparishchurchmusic.adapter.ServiceItemAdapter
import com.kippz07.creditonparishchurchmusic.dao.DatabaseBuilder
import com.kippz07.creditonparishchurchmusic.dao.MusicEntity
import com.kippz07.creditonparishchurchmusic.dao.ServiceEntity
import com.kippz07.creditonparishchurchmusic.model.Day
import com.kippz07.creditonparishchurchmusic.model.Piece
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.time.LocalDate

class ServiceActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_service)
        getSupportActionBar()?.setDisplayHomeAsUpEnabled(true)

        val service = intent.extras?.get("music") as ServiceEntity

        lifecycleScope.launch(Dispatchers.Main) {
            val music = DatabaseBuilder.getInstance(applicationContext).MusicDao().get(serviceType = service.serviceType, date = service.date)
//            val music = DatabaseBuilder.getInstance(applicationContext).MusicDao().getAll()
            displayData(service, music)
        }


    }

    private fun displayData(service: ServiceEntity, music: List<MusicEntity>) {
        val pieceRecyclerView = findViewById<RecyclerView>(R.id.recycler_view)

        pieceRecyclerView.adapter = ServiceItemAdapter(this, music)
        pieceRecyclerView.setHasFixedSize(true)

        val serviceDate: TextView = findViewById<TextView>(R.id.service_date)
        val serviceType: TextView = findViewById<TextView>(R.id.service_type)

        serviceDate.text = service.date.toString()
        serviceType.text = service.serviceType
    }

}
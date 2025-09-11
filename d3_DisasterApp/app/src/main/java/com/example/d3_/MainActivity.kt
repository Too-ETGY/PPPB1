package com.example.d3_

import android.app.Activity
import android.os.Bundle
import android.view.LayoutInflater
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.d3_.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

//    lateinit var binding: ActivityMainBinding

    private val binding by lazy {
        ActivityMainBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        // set binding
//        binding = ActivityMainBinding.inflate(layoutInflater)

        setContentView(binding.root)

        var disaster = generateDummy()

//        init adapter, sekaligus kirim data
        val adapterDisaster = DisasterAdapter(disaster)

        with(binding){
            rvDisaster.apply{
                adapter = adapterDisaster
                layoutManager = GridLayoutManager(this@MainActivity, 2)
            }
        }
    }

//    function to generate dummy
    fun generateDummy(): List<Disaster> {
        return listOf(
            Disaster(name = "Banjir", types = "Alam"),
            Disaster(name = "Tsunami", types = "Alam"),
            Disaster(name = "Gempa Bumi", types = "Alam"),
            Disaster(name = "Gunung Meletus", types = "Alam"),
            Disaster(name = "Tanah Longsor", types = "Alam"),
            Disaster(name = "Kebakaran Hutan", types = "Alam"),
            Disaster(name = "Angin Topan", types = "Alam"),
            Disaster(name = "Kekeringan", types = "Alam"),
            Disaster(name = "Puting Beliung", types = "Alam"),
            Disaster(name = "Gelombang Pasang", types = "Alam"),

            Disaster(name = "Kecelakaan Kereta", types = "Kecelakaan"),
            Disaster(name = "Kecelakaan Pesawat", types = "Kecelakaan"),
            Disaster(name = "Kecelakaan Kapal", types = "Kecelakaan"),
            Disaster(name = "Kecelakaan Mobil", types = "Kecelakaan"),
            Disaster(name = "Kecelakaan Bus", types = "Kecelakaan"),

            Disaster(name = "Kebakaran Gedung", types = "Buatan Manusia"),
            Disaster(name = "Ledakan Pabrik", types = "Buatan Manusia"),
            Disaster(name = "Tumpahan Minyak", types = "Buatan Manusia"),
            Disaster(name = "Pencemaran Lingkungan", types = "Buatan Manusia"),
            Disaster(name = "Kerusuhan", types = "Buatan Manusia")

        )
    }
}
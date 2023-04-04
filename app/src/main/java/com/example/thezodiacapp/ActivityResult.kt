package com.example.thezodiacapp

import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import androidx.room.Room
import com.example.thezodiacapp.R
import com.example.thezodiacapp.database.ZodiacDatabase
import kotlinx.coroutines.launch

class ActivityResult : AppCompatActivity() {
    private lateinit var zodiacNameTV: TextView
    private lateinit var zodiacDescTV: TextView
    private lateinit var zodiacSymbolTV: TextView
    private lateinit var zodiacMonthTV: TextView

    override fun onCreate(savedInstanceState: Bundle?){
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_result)

        val name = intent.getStringExtra("zodiac_name")
        val descr = intent.getStringExtra("zodiac_desc")
        val symbol = intent.getStringExtra("zodiac_symbol")
        val month = intent.getStringExtra("zodiac_month")
        val horoscope = intent.getStringExtra("zodiac_horoscope")

        findViewById<TextView>(R.id.zodiac_name).setText(name)
        findViewById<TextView>(R.id.zodiac_desc).setText(descr)
        findViewById<TextView>(R.id.zodiac_month).setText(month)
        findViewById<TextView>(R.id.zodiac_symbol).setText(symbol)
        findViewById<TextView>(R.id.zodiac_horoscope).setText(horoscope)
    }
}

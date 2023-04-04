package com.example.thezodiacapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.RecyclerView
import com.example.thezodiacapp.adapter.ItemAdapter
import com.example.thezodiacapp.database.Zodiac
import com.example.thezodiacapp.database.ZodiacDatabase
import com.example.thezodiacapp.network.ZodiacApiService
import com.example.thezodiacapp.network.ZodiacDataCombined
import com.example.thezodiacapp.network.ZodiacsApi
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {
    private lateinit var adapter: ItemAdapter
    val db: ZodiacDatabase by lazy {ZodiacDatabase.getDatabase(this)}
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val zodiacDao = db.zodiacDao()



        val zodiacsList = listOf(
            Zodiac(
                name = "Aries",
                descr = "Courageous and Energetic.",
                symbol = "Ram",
                month = "April"
            ),
            Zodiac(
                name = "Taurus",
                descr = "Reliable, Practical, Ambitious and Sensual.",
                symbol = "Bull",
                month = "May"
            ),
            Zodiac(
                name = "Gemini",
                descr = "Gemini-born are Clever and Intellectual.",
                symbol = "Twins",
                month = "June"
            ),
            Zodiac(
                name = "Cancer",
                descr = "Tenacious, Loyal and Sympathetic.",
                symbol = "Crab",
                month = "July"
            ),
            Zodiac(
                name = "Leo",
                descr = "Warm, Action-oriented, Desire to be Loved and Admired.",
                symbol = "Lion",
                month = "August"
            ),
            Zodiac(
                name = "Virgo",
                descr = "Methodical, Meticulous, Analytical and Mentally Astute.",
                symbol = "Virgin",
                month = "September"
            ),
            Zodiac(
                name = "Libra",
                descr = "Maintaining Balance and Harmony.",
                symbol = "Scales",
                month = "October"
            ),
            Zodiac(
                name = "Scorpio",
                descr = "Strong Willed and Mysterious.",
                symbol = "Scorpion",
                month = "November"
            ),
            Zodiac(
                name = "Sagittarius",
                descr = "Born Adventurers.",
                symbol = "Archer",
                month = "December"
            ),
            Zodiac(
                name = "Capricorn",
                descr = "The Most Determined Sign in the Zodiac.",
                symbol = "Goat",
                month = "January"
            ),
            Zodiac(
                name = "Aquarius",
                descr = "Humanitarians to the Core.",
                symbol = "Water Bearer",
                month = "February"
            ),
            Zodiac(
                name = "Pisces",
                descr = "Proverbial Dreamers of the Zodiac.",
                symbol = "Fish",
                month = "March"
            )
        )

        var combinedData: MutableList<ZodiacDataCombined> = mutableListOf<ZodiacDataCombined>()

        var recycler_view = findViewById<RecyclerView>(R.id.recycler_view)
        lifecycleScope.launch {
            zodiacDao.deleteAll()
            for(sign in zodiacsList){
                zodiacDao.insert(sign)
            }

//            val listOfZodiacSigns: List<Zodiac> = zodiacDao.getAllNames()!!

//            val recyclerView = findViewById<RecyclerView>(R.id.recycler_view)
//            recyclerView.adapter = ItemAdapter(applicationContext, listOfZodiacSigns)
//            recyclerView.setHasFixedSize(true)

        }
        lifecycleScope.launch{
            try {
                val response = ZodiacsApi.retrofitService.getHoroscopes()
                Log.d("j k", "onCreate: ${response}")
                zodiacsList.forEachIndexed { index, zodiacItem ->
                    val zodiacData = ZodiacDataCombined(zodiacItem.name, zodiacItem.descr, zodiacItem.symbol, zodiacItem.month, response[index].title)
                    combinedData.add(zodiacData)
                }
                adapter = ItemAdapter(applicationContext, combinedData)
                recycler_view.adapter = adapter
                recycler_view.setHasFixedSize(true)
            }catch (e: java.lang.Exception){
                Log.d("nooo", e.toString())

            }
        }



    }
}

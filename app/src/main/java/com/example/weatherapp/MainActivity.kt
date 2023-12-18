package com.example.weatherapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.android.volley.Request
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.example.weatherapp.databinding.ActivityMainBinding
import org.json.JSONObject

const val API_KEY = "11c9bd7f53c742ccbf594442231812"
class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater).also { setContentView(it.root) }
        binding.bGet.setOnClickListener {
            getResult("Odessa")
        }
    }
    private fun getResult(name: String) {
        val url = "https://api.weatherapi.com/v1/current.json" +
                "?key=$API_KEY&q=$name&aqi=no"
        val queue = Volley.newRequestQueue(this)
        val stringRequest = StringRequest(Request.Method.GET,
            url,
            { response ->
                val obj = JSONObject(response)
                val temp = obj.getJSONObject("current")
                Log.d("MyLog", "Response: ${temp.getString("temp_c")}")
                binding.tvTemp.text = "Temp: ${temp.getString("temp_c")}"
            },
            {
                //  volley error
                Log.d("MyLog", "VolleyError: $it")
            }
            )
        queue.add(stringRequest)
    }
}
package com.francineochoa.gameofthronesoficial.ui.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.core.os.bundleOf
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.francineochoa.gameofthronesoficial.R
import com.francineochoa.gameofthronesoficial.databinding.ActivityMainBinding
import com.francineochoa.gameofthronesoficial.model.Personaje
import com.francineochoa.gameofthronesoficial.network.RetrofitService
import com.francineochoa.gameofthronesoficial.network.personajes_api
import com.francineochoa.gameofthronesoficial.ui.adapters.PersonajesAdapter
import com.francineochoa.gameofthronesoficial.utils.Constants
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val call = RetrofitService.getRetrofit()
            .create(personajes_api::class.java)
            .getPersonajes(Constants.PERSONAJES_URL)

        call.enqueue(object: Callback<ArrayList<Personaje>> {
            override fun onResponse(
                call: Call<ArrayList<Personaje>>,
                response: Response<ArrayList<Personaje>>
            ) {
                binding.pbConexion.visibility = View.INVISIBLE
                Log.d(Constants.LOGTAG,"Respuesta del servidor: ${response.toString()}")
                Log.d(Constants.LOGTAG,"Datos: ${response.body().toString()}")


                //!! not null assertion
                val personajesAdapter = PersonajesAdapter(response.body()!!){ personaje ->
                    //Toast.makeText(this@MainActivity, "Click en personaje: ${personaje.fullName}", Toast.LENGTH_LONG).show()

                    val bundle = bundleOf(
                        "id" to personaje.id
                    )
                    val intent =Intent(this@MainActivity, DetailsActivity::class.java)
                    intent.putExtras(bundle)
                    startActivity(intent)
                }

                binding.rvMenu.layoutManager = LinearLayoutManager(this@MainActivity, RecyclerView.VERTICAL,false)
                binding.rvMenu.adapter= personajesAdapter
            }

            override fun onFailure(call: Call<ArrayList<Personaje>>, t: Throwable) {
                binding.pbConexion.visibility = View.INVISIBLE
                Toast.makeText(this@MainActivity,
                    getString(R.string.noConn),
                    Toast.LENGTH_LONG).show()
            }

        })



    }
}
package com.francineochoa.gameofthronesoficial.ui.activities

import android.graphics.drawable.Drawable
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import com.bumptech.glide.Glide
import com.francineochoa.gameofthronesoficial.R
import com.francineochoa.gameofthronesoficial.databinding.ActivityDetailsBinding
import com.francineochoa.gameofthronesoficial.databinding.ElementoPersonajeBinding
import com.francineochoa.gameofthronesoficial.model.PersonajeDetails
import com.francineochoa.gameofthronesoficial.network.RetrofitService
import com.francineochoa.gameofthronesoficial.network.personajes_api
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.create

class DetailsActivity : AppCompatActivity() {

    private lateinit var binding: ActivityDetailsBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val bundle = intent.extras

        val id = bundle?.getString("id","0")

        val call = RetrofitService.getRetrofit()
                    .create(personajes_api::class.java)
                    .getPersonajesDetail(id)

        call.enqueue(object: Callback<PersonajeDetails>{
            override fun onResponse(
                call: Call<PersonajeDetails>,
                response: Response<PersonajeDetails>
            ) {
                binding.tvFullName.text=response.body()?.fullName
                binding.tvFamilia.text=response.body()?.family
                binding.tvTitulo.text=response.body()?.title

                Glide.with(this@DetailsActivity)
                    .load(response.body()?.imageUrl)
                    .into(binding.imPersonaje)

                val imagen = when (response.body()?.family) {
                    "House Stark" ->  R.drawable.house_stark
                    "Stark" ->  R.drawable.house_stark
                    "House Tarly" -> R.drawable.house_tarly
                    "House Lannister" -> R.drawable.house_lannister
                    "Lannister" -> R.drawable.house_lannister
                    "House Lanister" -> R.drawable.house_lannister
                    "House Baelish" -> R.drawable.house_baelish
                    "House Seaworth" -> R.drawable.house_seaworth
                    "House Targaryen" -> R.drawable.house_targaryen
                    "Targaryan" -> R.drawable.house_targaryen
                    "House Baratheon" -> R.drawable.house_baratheon
                    "Baratheon" -> R.drawable.house_baratheon
                    "House Greyjoy" -> R.drawable.house_greyjoy
                    "Greyjoy" -> R.drawable.house_greyjoy
                    "House Tyrell" -> R.drawable.house_tyrell
                    "Tyrell" -> R.drawable.house_tyrell
                    "House Martell" -> R.drawable.house_martell
                    "House Clegane" -> R.drawable.house_clegane
                    "Clegane" -> R.drawable.house_clegane
                    "House Tarth" -> R.drawable.house_tarth
                    "Tarth" -> R.drawable.house_tarth
                    "House Bolton" -> R.drawable.house_bolton
                    "Bolton" -> R.drawable.house_bolton
                    "House Mormont" -> R.drawable.house_mormont
                    "Mormont" -> R.drawable.house_mormont
                    else -> R.drawable.dragon_trans
                }

                Glide.with(this@DetailsActivity)
                    .load(imagen)
                    .into(binding.imFamilia)

            }

            override fun onFailure(call: Call<PersonajeDetails>, t: Throwable) {

                Toast.makeText(this@DetailsActivity,
                    getString(R.string.noConn),
                    Toast.LENGTH_LONG).show()
            }

        })

    }
}
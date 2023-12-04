package com.francineochoa.gameofthronesoficial.network

import com.francineochoa.gameofthronesoficial.model.Personaje
import com.francineochoa.gameofthronesoficial.model.PersonajeDetails
import com.francineochoa.gameofthronesoficial.utils.Constants
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Url


interface personajes_api {
        @GET
        fun getPersonajes(
            @Url url: String?
        ):Call<ArrayList<Personaje>>

        @GET(Constants.ID_PERSONAJE_URL)
        fun getPersonajesDetail(
            @Path("id") id: String?
        ):Call<PersonajeDetails>

}
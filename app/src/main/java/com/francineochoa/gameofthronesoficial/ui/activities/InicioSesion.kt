package com.francineochoa.gameofthronesoficial.ui.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.InputType
import android.view.View
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import com.francineochoa.gameofthronesoficial.R
import com.francineochoa.gameofthronesoficial.databinding.ActivityInicioSesionBinding
import com.google.android.gms.tasks.Task
import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseAuthException

class InicioSesion : AppCompatActivity() {

    private lateinit var binding: ActivityInicioSesionBinding

    private lateinit var firebaseAuth: FirebaseAuth

    private var correo: String = ""
    private var contrasena: String = ""
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityInicioSesionBinding.inflate(layoutInflater)
        setContentView(binding.root)

        firebaseAuth = FirebaseAuth.getInstance()

        if(firebaseAuth.currentUser != null){
            startActivity(Intent(this, MainActivity::class.java))
            finish()
        }


    }//OnCreate


}//Clase
package com.example.alarm

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.Spinner
import androidx.constraintlayout.widget.ConstraintLayout

class Settings : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_settings)

        val btnSetChange : Button = findViewById(R.id.btnSetChange)
        val spinnerCarType : Spinner = findViewById(R.id.spinnerCarType)
        val loadingLayout : ConstraintLayout = findViewById(R.id.loadingSetting)

        btnSetChange.setOnClickListener {
            loadingLayout.visibility = ConstraintLayout.VISIBLE
            val intent = Intent(this,MainActivity::class.java)
            val carTitle = spinnerCarType.selectedItem.toString()
            intent.putExtra("carTitle",carTitle)
            startActivity(intent)
        }


        val carColor : Int

        val spinnerBackground : Spinner = findViewById(R.id.spinnerBackground)
        val backgroundName = spinnerBackground.selectedItem.toString()
        intent.putExtra("backgroundName",backgroundName)
        }
    }

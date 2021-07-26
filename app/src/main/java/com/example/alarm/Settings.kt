package com.example.alarm

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Spinner

class Settings : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_settings)

        val spinnerCarType : Spinner = findViewById(R.id.spinnerCarType)
        val carTitle = spinnerCarType.selectedItem.toString()
        val intent = Intent(this,MainActivity::class.java)
        intent.putExtra("carTitle",carTitle)

        val carColor : Int

        val spinnerBackground : Spinner = findViewById(R.id.spinnerBackground)
        val backgroundName = spinnerBackground.selectedItem.toString()
        intent.putExtra("backgroundName",backgroundName)
        }
    }

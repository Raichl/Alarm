package com.example.alarm

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //val ibLeft : ImageButton = findViewById(R.id.ib_left)
        val ibEmergency : ImageButton = findViewById(R.id.ib_Emergency)
        val ibRight : ImageButton = findViewById(R.id.ib_right)
        val ibHighBeam : ImageButton = findViewById(R.id.ib_High_Beam)
        val ibLowBeam : ImageButton = findViewById(R.id.ib_low_beam)
        val ibDimensions : ImageButton = findViewById(R.id.ib_dimensions)
        val ibWindow : ImageButton = findViewById(R.id.ib_Window)
        val ibTrunk : ImageButton = findViewById(R.id.ib_Trunk)
        val ibStart : ImageButton = findViewById(R.id.ib_Start)
        val ibLock : ImageButton = findViewById(R.id.ib_Lock)

        val tvTemp : TextView = findViewById(R.id.tv_Temp)
        val tvFuel : TextView = findViewById(R.id.tv_Fuel)
        val tvCharge : TextView = findViewById(R.id.tv_Charge)

        val ibLeft : ImageButton = findViewById(R.id.ib_left)
        val ivLeft : ImageView = findViewById(R.id.iv_Left)
        ibLeft.setOnClickListener{
            ivLeft.visibility = ImageView.VISIBLE
        }
    }


}
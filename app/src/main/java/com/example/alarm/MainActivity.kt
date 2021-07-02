package com.example.alarm

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.TextView

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val blinding = Blinding()



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
            blinding.blind(ivLeft,this)
        }

        val ibRight : ImageButton = findViewById(R.id.ib_right)
        val ivRight : ImageView = findViewById(R.id.iv_Right)
        ibRight.setOnClickListener{
            blinding.blind(ivRight,this)
        }

        val ibEmergency : ImageButton = findViewById(R.id.ib_Emergency)
        ibEmergency.setOnClickListener{
            blinding.blindDoble(ivLeft,ivRight,this)
        }

        val ibLowBeam : ImageButton = findViewById(R.id.ib_low_beam)
        val ivLowBeam : ImageView = findViewById(R.id.iv_LowBeam)
        val LowBeamON = R.drawable.low_beam
        val LowBeamOff = R.drawable.low_beam_dark
        var clickCheakLowBeam = false
        ibLowBeam.setOnClickListener{
            blinding.blindONorOFF(ivLowBeam)
            blinding.swapPict(ibLowBeam,LowBeamON,LowBeamOff,clickCheakLowBeam)
            clickCheakLowBeam = !clickCheakLowBeam
        }

        val ibHighBeam : ImageButton = findViewById(R.id.ib_High_Beam)
        val ivHighBeam : ImageView = findViewById(R.id.iv_HighBeam)
        val HighBeamON = R.drawable.high_beam_btn
        val HighBeamOff = R.drawable.high_beam_dark
        var clickCheakHighBeam = false
        ibHighBeam.setOnClickListener{
            blinding.blindONorOFF(ivHighBeam)
            blinding.swapPict(ibHighBeam,HighBeamON,HighBeamOff,clickCheakHighBeam)
            clickCheakHighBeam = !clickCheakHighBeam
        }

        val ibDimensions : ImageButton = findViewById(R.id.ib_dimensions)
        val ivRunningStop : ImageView = findViewById(R.id.iv_RunningStop)
        val ivRunningl : ImageView = findViewById(R.id.iv_RunnungL)
        val DimensionsIconFirst = R.drawable.running_lights_dark
        val DimensionsIconSecond = R.drawable.running_lights
        var clickCheakDimensions = false
        ibDimensions.setOnClickListener{
            blinding.blindONorOFF(ivRunningStop)
            blinding.blindONorOFF(ivRunningl)
            blinding.swapPict(ibDimensions,DimensionsIconSecond,DimensionsIconFirst,clickCheakDimensions)
            clickCheakDimensions = !clickCheakDimensions
        }





}


}
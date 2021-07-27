package com.example.alarm

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.View
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)




        val blinding = Blinding()

        val TIME : Long = (1 * 1000)/2




        val ibLeft : ImageButton = findViewById(R.id.ib_left)
        val ivLeft : ImageView = findViewById(R.id.iv_Left)
        val leftON = R.drawable.leftbtn
        val leftOff = R.drawable.left_dark
        ibLeft.setOnClickListener{
            ibLeft.isClickable = false
            Handler(Looper.getMainLooper()).postDelayed({ ibLeft.isClickable = true },TIME)
            blinding.blind(ivLeft,this,ibLeft,leftON,leftOff)
        }

        val ibRight : ImageButton = findViewById(R.id.ib_right)
        val ivRight : ImageView = findViewById(R.id.iv_Right)
        val rightON = R.drawable.rightbtn
        val rightOff = R.drawable.right_dark
        ibRight.setOnClickListener{
            ibRight.isClickable = false
            Handler(Looper.getMainLooper()).postDelayed({ ibRight.isClickable = true },TIME)
            blinding.blind(ivRight,this,ibRight,rightON,rightOff)
        }

        val ibEmergency : ImageButton = findViewById(R.id.ib_Emergency)
        val lowEmergencyON = R.drawable.emergency_gang
        val lowEmergencyOff = R.drawable.emergency_gang_dark
        ibEmergency.setOnClickListener{
            ibEmergency.isClickable = false
            Handler(Looper.getMainLooper()).postDelayed({ ibEmergency.isClickable = true },TIME)
            blinding.blindDoble(ivLeft,ivRight,this,ibEmergency,lowEmergencyON,lowEmergencyOff)
        }

        val ibLowBeam : ImageButton = findViewById(R.id.ib_low_beam)
        val ivLowBeam : ImageView = findViewById(R.id.iv_LowBeam)
        val lowBeamON = R.drawable.low_beam
        val lowBeamOff = R.drawable.low_beam_dark
        var clickCheakLowBeam = false
        ibLowBeam.setOnClickListener{
            blinding.blindONorOFF(ivLowBeam)
            blinding.swapPict(ibLowBeam,lowBeamON,lowBeamOff,clickCheakLowBeam)
            clickCheakLowBeam = !clickCheakLowBeam
        }

        val ibHighBeam : ImageButton = findViewById(R.id.ib_High_Beam)
        val ivHighBeam : ImageView = findViewById(R.id.iv_HighBeam)
        val highBeamON = R.drawable.high_beam_btn
        val highBeamOff = R.drawable.high_beam_dark
        var clickCheakHighBeam = false
        ibHighBeam.setOnClickListener{
            blinding.blindONorOFF(ivHighBeam)
            blinding.swapPict(ibHighBeam,highBeamON,highBeamOff,clickCheakHighBeam)
            clickCheakHighBeam = !clickCheakHighBeam
        }

        val ibDimensions : ImageButton = findViewById(R.id.ib_dimensions)
        val ivRunningStop : ImageView = findViewById(R.id.iv_RunningStop)
        val ivRunningl : ImageView = findViewById(R.id.iv_RunnungL)
        val dimensionsIconFirst = R.drawable.running_lights_dark
        val dimensionsIconSecond = R.drawable.running_lights
        var clickCheakDimensions = false
        ibDimensions.setOnClickListener{
            blinding.blindONorOFF(ivRunningStop)
            blinding.blindONorOFF(ivRunningl)
            blinding.swapPict(ibDimensions,dimensionsIconSecond,dimensionsIconFirst,clickCheakDimensions)
            clickCheakDimensions = !clickCheakDimensions
        }

        val ibFrontFoglights : ImageButton = findViewById(R.id.ib_front_foglights)
        val frontFoglightsFirst = R.drawable.front_foglights_dark
        val frontFoglightsSecond = R.drawable.front_foglights
        val ivFrontFog : ImageView = findViewById(R.id.iv_front_fog)
        var clickCheakFrontFoglights = false
        ibFrontFoglights.setOnClickListener{
            blinding.blindONorOFF(ivFrontFog)
            clickCheakFrontFoglights = !clickCheakFrontFoglights
            blinding.swapPict(ibFrontFoglights,frontFoglightsFirst,frontFoglightsSecond,clickCheakFrontFoglights)
        }

        val ibBackFogLights : ImageButton = findViewById(R.id.ib_back_foglights)
        val backFogLightsFirst = R.drawable.rear_foglights_dark
        val backFogLightsSecond = R.drawable.rear_foglights
        val ivBackFog : ImageView = findViewById(R.id.iv_back_fog)
        var clickCheakBackFogLights = false
        ibBackFogLights.setOnClickListener{
            blinding.blindONorOFF(ivBackFog)
            clickCheakBackFogLights = !clickCheakBackFogLights
            blinding.swapPict(ibBackFogLights,backFogLightsFirst,backFogLightsSecond,clickCheakBackFogLights)
        }

        val ibStart : ImageButton = findViewById(R.id.ib_Start)
        val tvTemp : TextView = findViewById(R.id.tv_Temp)
        val tvFuel : TextView = findViewById(R.id.tv_Fuel)
        val tvCharge : TextView = findViewById(R.id.tv_Charge)

        val ivTemp : ImageView = findViewById(R.id.iv_Temp)
        val tempON = R.drawable.engine_temperature_dark
        val tempOff = R.drawable.engine_temperature
        val ivFuel : ImageView = findViewById(R.id.iv_Fuel)
        val fuelON = R.drawable.fuel_dark
        val fuelOff = R.drawable.fuel
        val ivCharge: ImageView = findViewById(R.id.iv_Charge)
        val chargeON = R.drawable.charge_dark
        val chargeOff = R.drawable.charge

        var clickCheakibStart = true
        ibStart.setOnLongClickListener{
            ibStart.isClickable = false
            Handler(Looper.getMainLooper()).postDelayed({ ibStart.isClickable = true },1000)
            Handler(Looper.getMainLooper()).postDelayed({
                blinding.swapPict(ivTemp,tempON,tempOff,clickCheakibStart)
                blinding.swapPict(ivFuel,fuelON,fuelOff,clickCheakibStart)
                blinding.swapPict(ivCharge,chargeON,chargeOff,clickCheakibStart)
                if(clickCheakibStart){
                    tvTemp.visibility = View.VISIBLE
                    tvFuel.visibility = View.VISIBLE
                    tvCharge.visibility = View.VISIBLE
                }
                else{
                    tvTemp.visibility = View.INVISIBLE
                    tvFuel.visibility = View.INVISIBLE
                    tvCharge.visibility = View.INVISIBLE
                }
                clickCheakibStart = !clickCheakibStart
            },200)
            true
        }

        val ivCarTint : ImageView = findViewById(R.id.ivCarTint)
        val ivBack : ImageView = findViewById(R.id.iv_Back)
        val ibBackground : ImageView = findViewById(R.id.iv_BackGround)
        val lineStart : LinearLayout = findViewById(R.id.liner_StartLine)



        val carType : String? =
            if (intent.getStringExtra("carTitle") != null){
            intent.getStringExtra("carTitle")
        }else{
            savedInstanceState?.getString("carType")
        }

        val backColor : Int? =
            if (intent.extras?.getInt("colorCar") != null){
            intent.extras!!.getInt("colorCar")
        }else{
            savedInstanceState?.getInt("colorCar")
        }

        val lineStartBackgroundColor : Int? =
        if(carType != null){
            intent.extras!!.getInt("linerStartColor")
        }else{
            savedInstanceState?.getInt("linerStartColor")
        }

        if (intent.getStringExtra("carTitle") != null){
            val carTypeArray = resources.getStringArray(R.array.spinnerCarType)
            when (carType){
                carTypeArray[0] -> {
                    ivRunningStop.setImageResource(R.drawable.mercedes_stop)
                    ivLowBeam.setImageResource(R.drawable.mercedes_low_beaml)
                    ivHighBeam.setImageResource(R.drawable.mercedes_high_beam)
                    ivRight.setImageResource(R.drawable.mercedes_right)
                    ivRunningl.setImageResource(R.drawable.mercedes_running_lightsl)
                    ivLeft.setImageResource(R.drawable.mercedes_left)
                    ivCarTint.setImageResource(R.drawable.mercedes_car_model)
                    ibBackground.setImageResource(R.drawable.mercedes_background)
                }
                carTypeArray[1] -> {
                    ivRunningStop.setImageResource(R.drawable.mustang_stop)
                    ivLowBeam.setImageResource(R.drawable.mustang_low_beam)
                    ivHighBeam.setImageResource(R.drawable.mustang_high_beam)
                    ivRight.setImageResource(R.drawable.mustang_right)
                    ivRunningl.setImageResource(R.drawable.mustang_running_lightsl)
                    ivLeft.setImageResource(R.drawable.mustang_left)
                    ivCarTint.setImageResource(R.drawable.mustang_car_model)
                    ibBackground.setImageResource(R.drawable.mustang_background)
                    // ivFrontFog.setImageResource(R.drawable.mustang_car_model)
                    //ivBackFog.setImageResource(R.drawable.mustang_car_model)
                }
            }
        }
        if (backColor != null){
            ivBack.setBackgroundColor(backColor)
        }
        if (lineStartBackgroundColor != null){
            lineStart.setBackgroundColor(lineStartBackgroundColor)
        }

        savedInstanceState?.putString("carType",carType)
        if (lineStartBackgroundColor != null) savedInstanceState?.putInt("linerStartColor",lineStartBackgroundColor)
        if (backColor != null) savedInstanceState?.putInt("colorCar",backColor)

    }

    fun openSettings(view: View) {
        intent = Intent(this,Settings::class.java)
        startActivity(intent)
    }

}
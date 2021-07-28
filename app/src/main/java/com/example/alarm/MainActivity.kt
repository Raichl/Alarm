package com.example.alarm

import android.content.Intent
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.View
import android.widget.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val carTypeArray = resources.getStringArray(R.array.spinnerCarType)
        var carType = carTypeArray[0]
        if (intent.getStringExtra("carTitle") != null)
            carType = intent.getStringExtra("carTitle")

        val ivCarTint: ImageView = findViewById(R.id.ivCarTint)
        val ivBack: ImageView = findViewById(R.id.iv_Back)
        val ibBackground: ImageView = findViewById(R.id.iv_BackGround)
        val lineStart: LinearLayout = findViewById(R.id.liner_StartLine)

        var backColor = Color.RED
        if (intent.extras?.getInt("colorCar") != null)
            backColor = intent.extras?.getInt("colorCar")!!
        var lineStartBackgroundColor = Color.GRAY
        if (intent.extras?.getInt("linerStartColor") != null)
        lineStartBackgroundColor = intent.extras?.getInt("linerStartColor")!!


        val blinding = Blinding()

        val TIME: Long = (1 * 1000) / 2


        val ibLeft: ImageButton = findViewById(R.id.ib_left)
        val ivLeft: ImageView = findViewById(R.id.iv_Left)
        val leftON = R.drawable.leftbtn
        val leftOff = R.drawable.left_dark
        ibLeft.setOnClickListener {
            ibLeft.isClickable = false
            Handler(Looper.getMainLooper()).postDelayed({ ibLeft.isClickable = true }, TIME)
            blinding.blind(ivLeft, this, ibLeft, leftON, leftOff)
        }

        val ibRight: ImageButton = findViewById(R.id.ib_right)
        val ivRight: ImageView = findViewById(R.id.iv_Right)
        val rightON = R.drawable.rightbtn
        val rightOff = R.drawable.right_dark
        ibRight.setOnClickListener {
            ibRight.isClickable = false
            Handler(Looper.getMainLooper()).postDelayed({ ibRight.isClickable = true }, TIME)
            blinding.blind(ivRight, this, ibRight, rightON, rightOff)
        }

        val ibEmergency: ImageButton = findViewById(R.id.ib_Emergency)
        val lowEmergencyON = R.drawable.emergency_gang
        val lowEmergencyOff = R.drawable.emergency_gang_dark
        ibEmergency.setOnClickListener {
            ibEmergency.isClickable = false
            Handler(Looper.getMainLooper()).postDelayed({ ibEmergency.isClickable = true }, TIME)
            blinding.blindDoble(ivLeft, ivRight, this, ibEmergency, lowEmergencyON, lowEmergencyOff)
        }

        val ibLowBeam: ImageButton = findViewById(R.id.ib_low_beam)
        val ivLowBeam: ImageView = findViewById(R.id.iv_LowBeam)
        val lowBeamON = R.drawable.low_beam
        val lowBeamOff = R.drawable.low_beam_dark
        var clickCheakLowBeam = false
        ibLowBeam.setOnClickListener {
            blinding.blindONorOFF(ivLowBeam)
            blinding.swapPict(ibLowBeam, lowBeamON, lowBeamOff, clickCheakLowBeam)
            clickCheakLowBeam = !clickCheakLowBeam
        }

        val ibHighBeam: ImageButton = findViewById(R.id.ib_High_Beam)
        val ivHighBeam: ImageView = findViewById(R.id.iv_HighBeam)
        val highBeamON = R.drawable.high_beam_btn
        val highBeamOff = R.drawable.high_beam_dark
        var clickCheakHighBeam = false
        ibHighBeam.setOnClickListener {
            blinding.blindONorOFF(ivHighBeam)
            blinding.swapPict(ibHighBeam, highBeamON, highBeamOff, clickCheakHighBeam)
            clickCheakHighBeam = !clickCheakHighBeam
        }

        val ibDimensions: ImageButton = findViewById(R.id.ib_dimensions)
        val ivRunningStop: ImageView = findViewById(R.id.iv_RunningStop)
        val ivRunningl: ImageView = findViewById(R.id.iv_RunnungL)
        val dimensionsIconFirst = R.drawable.running_lights_dark
        val dimensionsIconSecond = R.drawable.running_lights
        var clickCheakDimensions = false
        ibDimensions.setOnClickListener {
            blinding.blindONorOFF(ivRunningStop)
            blinding.blindONorOFF(ivRunningl)
            blinding.swapPict(
                ibDimensions,
                dimensionsIconSecond,
                dimensionsIconFirst,
                clickCheakDimensions
            )
            clickCheakDimensions = !clickCheakDimensions
        }

        val ibFrontFoglights: ImageButton = findViewById(R.id.ib_front_foglights)
        val frontFoglightsFirst = R.drawable.front_foglights_dark
        val frontFoglightsSecond = R.drawable.front_foglights
        val ivFrontFog: ImageView = findViewById(R.id.iv_front_fog)
        var clickCheakFrontFoglights = false
        ibFrontFoglights.setOnClickListener {
            blinding.blindONorOFF(ivFrontFog)
            clickCheakFrontFoglights = !clickCheakFrontFoglights
            blinding.swapPict(
                ibFrontFoglights,
                frontFoglightsFirst,
                frontFoglightsSecond,
                clickCheakFrontFoglights
            )
        }

        val ibBackFogLights: ImageButton = findViewById(R.id.ib_back_foglights)
        val backFogLightsFirst = R.drawable.rear_foglights_dark
        val backFogLightsSecond = R.drawable.rear_foglights
        val ivBackFog: ImageView = findViewById(R.id.iv_back_fog)
        var clickCheakBackFogLights = false
        ibBackFogLights.setOnClickListener {
            blinding.blindONorOFF(ivBackFog)
            clickCheakBackFogLights = !clickCheakBackFogLights
            blinding.swapPict(
                ibBackFogLights,
                backFogLightsFirst,
                backFogLightsSecond,
                clickCheakBackFogLights
            )
        }

        val ibStart: ImageButton = findViewById(R.id.ib_Start)
        val tvTemp: TextView = findViewById(R.id.tv_Temp)
        val tvFuel: TextView = findViewById(R.id.tv_Fuel)
        val tvCharge: TextView = findViewById(R.id.tv_Charge)

        val ivTemp: ImageView = findViewById(R.id.iv_Temp)
        val tempON = R.drawable.engine_temperature_dark
        val tempOff = R.drawable.engine_temperature
        val ivFuel: ImageView = findViewById(R.id.iv_Fuel)
        val fuelON = R.drawable.fuel_dark
        val fuelOff = R.drawable.fuel
        val ivCharge: ImageView = findViewById(R.id.iv_Charge)
        val chargeON = R.drawable.charge_dark
        val chargeOff = R.drawable.charge

        var clickCheakibStart = true
        ibStart.setOnLongClickListener {
            ibStart.isClickable = false
            Handler(Looper.getMainLooper()).postDelayed({ ibStart.isClickable = true }, 1000)
            Handler(Looper.getMainLooper()).postDelayed({
                blinding.swapPict(ivTemp, tempON, tempOff, clickCheakibStart)
                blinding.swapPict(ivFuel, fuelON, fuelOff, clickCheakibStart)
                blinding.swapPict(ivCharge, chargeON, chargeOff, clickCheakibStart)
                if (clickCheakibStart) {
                    tvTemp.visibility = View.VISIBLE
                    tvFuel.visibility = View.VISIBLE
                    tvCharge.visibility = View.VISIBLE
                } else {
                    tvTemp.visibility = View.INVISIBLE
                    tvFuel.visibility = View.INVISIBLE
                    tvCharge.visibility = View.INVISIBLE
                }
                clickCheakibStart = !clickCheakibStart
            }, 200)
            true
        }



        val ivpl: ImageView = findViewById(R.id.iv_pl)
        val ivpr: ImageView = findViewById(R.id.iv_pr)
        val ivzl: ImageView = findViewById(R.id.iv_zl)
        val ivzr: ImageView = findViewById(R.id.iv_zr)


        val ibWindow : ImageButton = findViewById(R.id.ib_Window)
        var clickCount = 0
        ibWindow.setOnClickListener {
            when (clickCount){
                0 ->{
                    when (carType) {
                        carTypeArray[0] -> {ivpl.setImageResource(R.drawable.mercedes_o_p_l)}
                        carTypeArray[1] -> {ivpl.setImageResource(R.drawable.mustang_p_l)}
                        carTypeArray[2] -> {ivpl.setImageResource(R.drawable.rolls_royce_o_p_l)}
                    }
                }
                1 -> {
                    when (carType) {
                        carTypeArray[0] -> {ivpr.setImageResource(R.drawable.mercedes_o_p_r)}
                        carTypeArray[1] -> {ivpr.setImageResource(R.drawable.mustang_p_r)}
                        carTypeArray[2] -> {ivpr.setImageResource(R.drawable.rolls_royce_o_p_r)}
                    }
                }
                2 -> {
                    when (carType) {
                        carTypeArray[0] -> {ivzl.setImageResource(R.drawable.mercedes_o_z_l)}
                        carTypeArray[1] -> {
                            ivpl.setImageResource(R.drawable.mustang_c_p_l)
                            ivpr.setImageResource(R.drawable.mustang_c_p_r)
                            clickCount = -1
                        }
                        carTypeArray[2] -> {ivzl.setImageResource(R.drawable.rolls_royce_o_z_l)}
                    }
                }
                3 -> {
                    when (carType) {
                        carTypeArray[0] -> {ivzr.setImageResource(R.drawable.mercedes_o_z_r)}
                        carTypeArray[2] -> {ivzr.setImageResource(R.drawable.rolls_royce_o_z_r)}
                    }
                }
                else -> {
                    when (carType) {
                        carTypeArray[0] -> {
                            ivpl.setImageResource(R.drawable.mercedes_c_p_l)
                            ivpr.setImageResource(R.drawable.mercedes_c_p_r)
                            ivzl.setImageResource(R.drawable.mercedes_c_z_l)
                            ivzr.setImageResource(R.drawable.mercedes_c_z_r)
                        }
                        carTypeArray[2] -> {
                            ivpl.setImageResource(R.drawable.rolls_royce_c_p_l)
                            ivpr.setImageResource(R.drawable.rolls_royce_c_p_r)
                            ivzl.setImageResource(R.drawable.rolls_royce_c_z_l)
                            ivzr.setImageResource(R.drawable.rolls_royce_c_z_r)


                        }
                    }
                    clickCount = -1
                }
            }
            clickCount++
        }





        if (carType != null) {
            when (carType) {
                carTypeArray[0] -> {
                    ivRunningStop.setImageResource(R.drawable.mercedes_stop)
                    ivLowBeam.setImageResource(R.drawable.mercedes_low_beaml)
                    ivHighBeam.setImageResource(R.drawable.mercedes_high_beam)
                    ivRight.setImageResource(R.drawable.mercedes_right)
                    ivRunningl.setImageResource(R.drawable.mercedes_running_lightsl)
                    ivLeft.setImageResource(R.drawable.mercedes_left)
                    ivCarTint.setImageResource(R.drawable.mercedes_car_model)
                    ibBackground.setImageResource(R.drawable.mercedes_background)
                    ivpl.setImageResource(R.drawable.mercedes_c_p_l)
                    ivpr.setImageResource(R.drawable.mercedes_c_p_r)
                    ivzl.setImageResource(R.drawable.mercedes_c_z_l)
                    ivzr.setImageResource(R.drawable.mercedes_c_z_r)
                    ivFrontFog.setImageResource(R.drawable.mercedes_front_foglights_tv)
                    ivBackFog.setImageResource(R.drawable.mercedes_rear_foglights_tv)
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
                    ivpl.setImageResource(R.drawable.mustang_c_p_l)
                    ivpr.setImageResource(R.drawable.mustang_c_p_r)
                    ivzl.visibility = ImageView.INVISIBLE
                    ivzr.visibility = ImageView.INVISIBLE
                    ivFrontFog.setImageResource(R.drawable.mustang_front_foglights)
                    ivBackFog.setImageResource(R.drawable.mustang_rear_foglights)
                }
                carTypeArray[2] -> {
                    ivRunningStop.setImageResource(R.drawable.rolls_royce_stop)
                    ivLowBeam.setImageResource(R.drawable.rolls_royce_low_beaml)
                    ivHighBeam.setImageResource(R.drawable.rolls_royce_high_beam)
                    ivRight.setImageResource(R.drawable.rolls_royce_right)
                    ivRunningl.setImageResource(R.drawable.rolls_royce_running_lightsl)
                    ivLeft.setImageResource(R.drawable.rolls_royce_left)
                    ivCarTint.setImageResource(R.drawable.rolls_royce_car_model)
                    ibBackground.setImageResource(R.drawable.rolls_royce_background)
                    ivpl.setImageResource(R.drawable.rolls_royce_c_p_l)
                    ivpr.setImageResource(R.drawable.rolls_royce_c_p_r)
                    ivzl.setImageResource(R.drawable.rolls_royce_c_z_l)
                    ivzr.setImageResource(R.drawable.rolls_royce_c_z_r)
                    ivFrontFog.setImageResource(R.drawable.rolls_royce_front_foglights)
                    ivBackFog.setImageResource(R.drawable.rolls_royce_rear_foglights)
                }
            }
        }

        ivBack.setBackgroundColor(backColor)

        lineStart.setBackgroundColor(lineStartBackgroundColor)



        val ibOpenSettings: ImageButton = findViewById(R.id.ib_OpenSetting)
        ibOpenSettings.setOnClickListener {
            intent = Intent(this, Settings::class.java)
            intent.putExtra("carTitle",carType)
            intent.putExtra("colorCar",backColor)
            intent.putExtra("linerStartColor",lineStartBackgroundColor)
            startActivity(intent)
        }
    }

}


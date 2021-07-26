package com.example.alarm

import android.content.Context
import android.view.View
import android.widget.ImageView

class SwapHelper {
    fun swapCarType(carType : String, view: View){



        //val ivBack : ImageView = view.findViewById(R.id.iv_Back)
        //val ivBackGround : ImageView = view.findViewById(R.id.iv_BarckGround)
        val ivRunningStop : ImageView = view.findViewById(R.id.iv_RunningStop)
        val ivLowBeam : ImageView = view.findViewById(R.id.iv_LowBeam)
        val ivHighBeam : ImageView = view.findViewById(R.id.iv_HighBeam)
        val ivRight : ImageView = view.findViewById(R.id.iv_Right)
        val ivRunningL : ImageView = view.findViewById(R.id.iv_RunnungL)
        val ivLeft : ImageView = view.findViewById(R.id.iv_Left)
        val ivCarTint : ImageView = view.findViewById(R.id.ivCarTint)
        //val ivFrontFog : ImageView = view.findViewById(R.id.iv_front_fog)
        //al ivBackFog : ImageView = view.findViewById(R.id.iv_back_fog)

        val carTypeArray = view.context.resources.getStringArray(R.array.spinnerCarType)

        when (carType){
            carTypeArray[0] -> {
                ivRunningStop.setImageResource(R.drawable.mercedes_stop)
                ivLowBeam.setImageResource(R.drawable.mercedes_low_beaml)
                ivHighBeam.setImageResource(R.drawable.mercedes_high_beam)
                ivRight.setImageResource(R.drawable.mercedes_right)
                ivRunningL.setImageResource(R.drawable.mercedes_running_lightsl)
                ivLeft.setImageResource(R.drawable.mercedes_left)
                ivCarTint.setImageResource(R.drawable.mercedes_car_model)
            }
            carTypeArray[1] -> {
                ivRunningStop.setImageResource(R.drawable.mustang_stop)
                ivLowBeam.setImageResource(R.drawable.mustang_low_beam)
                ivHighBeam.setImageResource(R.drawable.mustang_high_beam)
                ivRight.setImageResource(R.drawable.mustang_right)
                ivRunningL.setImageResource(R.drawable.mustang_running_lightsl)
                ivLeft.setImageResource(R.drawable.mustang_left)
                ivCarTint.setImageResource(R.drawable.mustang_car_model)
               // ivFrontFog.setImageResource(R.drawable.mustang_car_model)
                //ivBackFog.setImageResource(R.drawable.mustang_car_model)
            }
        }


    }
}
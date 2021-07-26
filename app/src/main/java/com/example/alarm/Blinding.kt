package com.example.alarm

import android.app.Activity
import android.os.Handler
import android.os.Looper
import android.view.View
import android.widget.ImageButton
import android.widget.ImageView

class Blinding {
    private var clikCheack = false


    fun blind (obj : ImageView, activity: Activity,objBtn : ImageButton,resOn : Int,resOff : Int){
        clikCheack = !clikCheack
        Thread{
            while (clikCheack){
               activity.runOnUiThread {
                    if (obj.visibility == View.INVISIBLE){
                        obj.visibility = View.VISIBLE
                        objBtn.setImageResource(resOn)
                    }else{
                        obj.visibility = View.INVISIBLE
                        objBtn.setImageResource(resOff)
                        }
                }
                Thread.sleep(500)
            }
            activity.runOnUiThread{obj.visibility = View.INVISIBLE}
            objBtn.setImageResource(resOff)
        }.start()
    }
    fun blindDoble(obj1 : ImageView,obj2 : ImageView, activity: Activity, objBtn : ImageButton,resOn : Int,resOff : Int){
        clikCheack = !clikCheack
        Thread{
            while (clikCheack){
                activity.runOnUiThread {
                    if ((obj1.visibility == View.INVISIBLE)&&(obj2.visibility == View.INVISIBLE)){
                        obj1.visibility = View.VISIBLE
                        obj2.visibility = View.VISIBLE
                        objBtn.setImageResource(resOn)
                    }else if ((obj1.visibility == View.VISIBLE)&&(obj2.visibility == View.VISIBLE)){
                        obj1.visibility = View.INVISIBLE
                        obj2.visibility = View.INVISIBLE
                        objBtn.setImageResource(resOff)
                    }
                }
                Thread.sleep(500)
            }
            activity.runOnUiThread{obj1.visibility = View.INVISIBLE}
            activity.runOnUiThread{obj2.visibility = View.INVISIBLE}
            objBtn.setImageResource(resOff)
        }.start()
    }
    fun blindONorOFF (obj : ImageView){
        if (obj.visibility == View.INVISIBLE){
            obj.visibility = View.VISIBLE
        }else{
            obj.visibility = View.INVISIBLE
        }
    }
    fun swapPict (obj : ImageButton, resFirst : Int, resSecond : Int, clickfoBtn : Boolean){
        if(clickfoBtn) obj.setImageResource(resSecond)
        else obj.setImageResource(resFirst)
    }
    fun swapPict (obj : ImageView, resFirst : Int, resSecond : Int, clickfoBtn : Boolean){
        if(clickfoBtn) obj.setImageResource(resSecond)
        else obj.setImageResource(resFirst)
    }
}
package com.example.alarm

import android.app.Activity
import android.view.View
import android.widget.ImageButton
import android.widget.ImageView

class Blinding {
    private var clikCheack = false
    fun blind (obj : ImageView, activity: Activity){
        clikCheack = !clikCheack
        Thread{
            while (clikCheack){
               activity.runOnUiThread() {
                    if (obj.visibility == View.INVISIBLE){
                        obj.visibility = View.VISIBLE
                    }else{
                        obj.visibility = View.INVISIBLE
                    }
                }
                Thread.sleep(500)
            }
            activity.runOnUiThread{obj.visibility = View.INVISIBLE}
        }.start()
    }
    fun blindDoble(obj1 : ImageView,obj2 : ImageView, activity: Activity,){
        clikCheack = !clikCheack
        Thread{
            while (clikCheack){
                activity.runOnUiThread() {
                    if ((obj1.visibility == View.INVISIBLE)&&(obj2.visibility == View.INVISIBLE)){
                        obj1.visibility = View.VISIBLE
                        obj2.visibility = View.VISIBLE
                    }else if ((obj1.visibility == View.VISIBLE)&&(obj2.visibility == View.VISIBLE)){
                        obj1.visibility = View.INVISIBLE
                        obj2.visibility = View.INVISIBLE
                    }
                }
                Thread.sleep(500)
            }
            activity.runOnUiThread{obj1.visibility = View.INVISIBLE}
            activity.runOnUiThread{obj2.visibility = View.INVISIBLE}
        }.start()
    }
    fun blindONorOFF (obj : ImageView){
        if (obj.visibility == View.INVISIBLE){
            obj.visibility = View.VISIBLE
        }else{
            obj.visibility = View.INVISIBLE
        }
    }
    fun SwapPict (obj : ImageButton, resFirst : Int, resSecond : Int, activity: Activity){
        if (obj.visibility == View.INVISIBLE){
            obj.visibility = View.VISIBLE
        }else{
            obj.visibility = View.INVISIBLE
        }
    }
}
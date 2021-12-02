package com.example.alarm

import android.bluetooth.BluetoothAdapter
import android.bluetooth.BluetoothDevice
import android.content.Context
import android.content.Intent
import androidx.core.content.ContextCompat.startActivity

class Bluetooth(ct:Context) {

    private val context = ct
    val adapter: BluetoothAdapter? = BluetoothAdapter.getDefaultAdapter()

    fun cheackAdapter(): Boolean{
        return if (adapter != null){
            if (adapter.isEnabled){
                true
            }else {
                val  enableBtIntent = Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE)
                startActivity(context,enableBtIntent,null)
                false
            }
        }else false
    }

    fun getBoundedDevices():ArrayList<BluetoothDevice>{
        val boundedDevices = ArrayList<BluetoothDevice>()
        val parriedDevices = adapter?.bondedDevices
        if(parriedDevices!= null && parriedDevices.isNotEmpty()){
            for (device in parriedDevices){
                boundedDevices.add(device)
            }
        }
        return boundedDevices
    }




}
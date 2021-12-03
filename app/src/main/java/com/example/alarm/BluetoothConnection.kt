package com.example.alarm

import android.bluetooth.BluetoothDevice
import android.bluetooth.BluetoothSocket

object BluetoothConnection {
    var device: BluetoothDevice? = null
    var socket: BluetoothSocket? = null

    fun clearConnection(){
        device = null
        socket = null
    }
}
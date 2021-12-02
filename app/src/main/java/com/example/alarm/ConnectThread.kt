package com.example.alarm

import android.bluetooth.BluetoothDevice
import android.bluetooth.BluetoothSocket
import android.os.ParcelUuid
import android.util.Log
import java.util.*

class ConnectThread: Thread(){

    private val uuid = ParcelUuid.fromString("00001101-0000-1000-8000-00805F9B34FB")

    fun connect(device: BluetoothDevice):BluetoothSocket?{
        val state = device.bondState
        return if (state != BluetoothDevice.BOND_BONDED) null
        else{
            val btSocket = device.createInsecureRfcommSocketToServiceRecord(uuid.uuid)
            return try {
                btSocket.connect()
                return btSocket
            }catch (e:Exception){
                btSocket.close()
                null
            }
        }

    }

    fun writeData(data:String,socket: BluetoothSocket){
        var outStream = socket.outputStream
        try {
            outStream = socket.outputStream
        }catch (e:Exception){
            e.printStackTrace()
        }
        val dataByte = data.toByteArray()
        try{
            outStream.write(dataByte)
        }catch (e:Exception){
            e.printStackTrace()
        }
    }

    fun disconnect(socket:BluetoothSocket){
        socket.close()
    }
}
package com.example.alarm

import android.app.Dialog
import android.bluetooth.BluetoothDevice
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.View
import android.widget.*
import androidx.appcompat.app.AppCompatDelegate
import androidx.core.content.res.ResourcesCompat


class MainActivity : AppCompatActivity() {
    private val bluetooth = Bluetooth(this)

    private val ivRunningStop: ImageView by lazy{findViewById(R.id.iv_RunningStop)}
    private val ivLowBeam: ImageView by lazy{findViewById(R.id.iv_LowBeam)}
    private val ivHighBeam: ImageView by lazy{findViewById(R.id.iv_HighBeam)}
    private val ivRight: ImageView by lazy{findViewById(R.id.iv_Right)}
    private val ivRunningl: ImageView by lazy{findViewById(R.id.iv_RunnungL)}
    private val ivLeft: ImageView by lazy{findViewById(R.id.iv_Left)}
    private val ivCarTint: ImageView by lazy{findViewById(R.id.ivCarTint)}
    private val ibBackground: ImageView by lazy{findViewById(R.id.iv_BackGround)}
    private val ivpl: ImageView by lazy{findViewById(R.id.iv_pl)}
    private val ivpr: ImageView by lazy{findViewById(R.id.iv_pr)}
    private val ivzl: ImageView by lazy{findViewById(R.id.iv_zl)}
    private val ivzr: ImageView by lazy{findViewById(R.id.iv_zr)}
    private val ivFrontFog: ImageView by lazy{findViewById(R.id.iv_front_fog)}
    private val ivBackFog: ImageView by lazy{findViewById(R.id.iv_back_fog)}

    private val ibDimensions: ImageButton by lazy { findViewById(R.id.ib_dimensions) }
    private val ivBack: ImageView by lazy{findViewById(R.id.iv_Back)}
    private val lineStart: LinearLayout by lazy{findViewById(R.id.liner_StartLine)}
    private val ibBluetooth: ImageButton by lazy { findViewById(R.id.ibBluetooth) }
    private val ibSignal: ImageButton by lazy { findViewById(R.id.ib_Signal) }
    private val ivHandBrake: ImageView by lazy { findViewById(R.id.iv_Hand_Brake) }
    private val ibLock: ImageButton by lazy { findViewById(R.id.ib_Lock) }
    private val ibTrunk: ImageButton by lazy { findViewById(R.id.ib_Trunk) }
    private val ibWindow : ImageButton by lazy { findViewById(R.id.ib_Window) }

    private var carType = Setting.selectedCar
    private var clickCount = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val dbManager = DbManager(this)
        dbManager.openDb()
        val saves = dbManager.getSaves()
        var backColor = Color.RED
        var lineStartBackgroundColor = Color.GRAY

        if (saves?.carType != null && saves.backgroundColor != null && saves.lineColor != null){
            carType = saves.carType
            backColor = saves.backgroundColor!!
            lineStartBackgroundColor = saves.lineColor!!
        }
        else {
            val addSave = SavesInfo(carType,backColor,lineStartBackgroundColor)
            dbManager.saveNew(addSave)
        }



        if (Setting.selectedCar != null) carType = Setting.selectedCar
        else Setting.selectedCar = carType


        if (Setting.backColor != null) backColor = Setting.backColor!!
        else Setting.backColor = backColor


        if (Setting.lineStartColor != null) lineStartBackgroundColor = Setting.lineStartColor!!
        else Setting.lineStartColor = lineStartBackgroundColor

        setCarSkin(carType)

        val blinding = Blinding()

        val waitTime: Long = (1 * 1000) / 2


        val ibLeft: ImageButton = findViewById(R.id.ib_left)
        val leftON = R.drawable.leftbtn
        val leftOff = R.drawable.left_dark
        ibLeft.setOnClickListener {
            if(connectionCheack()) ConnectThread().writeData(getString(R.string.leftbtn),BluetoothConnection.socket!!)
            ibLeft.isClickable = false
            Handler(Looper.getMainLooper()).postDelayed({ ibLeft.isClickable = true }, waitTime)
            blinding.blind(ivLeft, this, ibLeft, leftON, leftOff)
        }

        val ibRight: ImageButton = findViewById(R.id.ib_right)
        val rightON = R.drawable.rightbtn
        val rightOff = R.drawable.right_dark
        ibRight.setOnClickListener {
            if(connectionCheack()) ConnectThread().writeData(getString(R.string.rightbtn),BluetoothConnection.socket!!)
            ibRight.isClickable = false
            Handler(Looper.getMainLooper()).postDelayed({ ibRight.isClickable = true }, waitTime)
            blinding.blind(ivRight, this, ibRight, rightON, rightOff)
        }

        val ibEmergency: ImageButton = findViewById(R.id.ib_Emergency)
        val lowEmergencyON = R.drawable.emergency_gang
        val lowEmergencyOff = R.drawable.emergency_gang_dark
        ibEmergency.setOnClickListener {
            if(connectionCheack()) ConnectThread().writeData(getString(R.string.emergency_gang),BluetoothConnection.socket!!)
            ibEmergency.isClickable = false
            Handler(Looper.getMainLooper()).postDelayed({ ibEmergency.isClickable = true }, waitTime)
            blinding.blindDoble(ivLeft, ivRight, this, ibEmergency, lowEmergencyON, lowEmergencyOff)
        }

        val ibLowBeam: ImageButton = findViewById(R.id.ib_low_beam)
        val lowBeamON = R.drawable.low_beam
        val lowBeamOff = R.drawable.low_beam_dark
        var clickCheakLowBeam = false
        ibLowBeam.setOnClickListener {
            if(connectionCheack()) ConnectThread().writeData(getString(R.string.low_beam),BluetoothConnection.socket!!)
            blinding.blindONorOFF(ivLowBeam)
            blinding.swapPict(ibLowBeam, lowBeamON, lowBeamOff, clickCheakLowBeam)
            clickCheakLowBeam = !clickCheakLowBeam
        }

        val ibHighBeam: ImageButton = findViewById(R.id.ib_High_Beam)
        val highBeamON = R.drawable.high_beam_btn
        val highBeamOff = R.drawable.high_beam_dark
        var clickCheakHighBeam = false
        ibHighBeam.setOnClickListener {
            if(connectionCheack()) ConnectThread().writeData(getString(R.string.high_beam_btn),BluetoothConnection.socket!!)
            blinding.blindONorOFF(ivHighBeam)
            blinding.swapPict(ibHighBeam, highBeamON, highBeamOff, clickCheakHighBeam)
            clickCheakHighBeam = !clickCheakHighBeam
        }

        val dimensionsIconFirst = R.drawable.running_lights_dark
        val dimensionsIconSecond = R.drawable.running_lights
        var clickCheakDimensions = false
        ibDimensions.setOnClickListener {
            if(connectionCheack()) ConnectThread().writeData(getString(R.string.running_lights),BluetoothConnection.socket!!)
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
        var clickCheakFrontFoglights = false
        ibFrontFoglights.setOnClickListener {
            if(connectionCheack()) ConnectThread().writeData(getString(R.string.front_foglights),BluetoothConnection.socket!!)
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
        var clickCheakBackFogLights = false
        ibBackFogLights.setOnClickListener {
            if(connectionCheack()) ConnectThread().writeData(getString(R.string.rear_foglights),BluetoothConnection.socket!!)
            blinding.blindONorOFF(ivBackFog)
            clickCheakBackFogLights = !clickCheakBackFogLights
            blinding.swapPict(
                ibBackFogLights,
                backFogLightsFirst,
                backFogLightsSecond,
                clickCheakBackFogLights
            )
        }

        ibSignal.setOnClickListener {
            if(connectionCheack()) ConnectThread().writeData(getString(R.string.sound_signal),BluetoothConnection.socket!!)
        }
        ivHandBrake.setOnClickListener {
            if(connectionCheack()) ConnectThread().writeData(getString(R.string.hand_brake),BluetoothConnection.socket!!)
        }
        ibLock.setOnClickListener {
            if(connectionCheack()) ConnectThread().writeData(getString(R.string.Lock),BluetoothConnection.socket!!)
        }
        ibTrunk.setOnClickListener {
            if(connectionCheack()) ConnectThread().writeData(getString(R.string.Trunk),BluetoothConnection.socket!!)
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
            if(connectionCheack()) ConnectThread().writeData(getString(R.string.start_btn),BluetoothConnection.socket!!)
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












        ivBack.setBackgroundColor(backColor)

        lineStart.setBackgroundColor(lineStartBackgroundColor)



        val ibOpenSettings: ImageButton = findViewById(R.id.ib_OpenSetting)
        ibOpenSettings.setOnClickListener {
            intent = Intent(this, Settings::class.java)
            intent.putExtra("carTitle",carType)
            intent.putExtra("colorCar",backColor)
            intent.putExtra("linerStartColor",lineStartBackgroundColor)
            clickCount = 0
            startActivity(intent)
        }


        val filter = IntentFilter(BluetoothDevice.ACTION_UUID)
        filter.addAction(BluetoothDevice.ACTION_ACL_DISCONNECTED)
        registerReceiver(mReceiver,filter)


        ibBluetooth.setOnClickListener{
            if(bluetooth.cheackAdapter()){
                val dialogDevice = Dialog(this)
                dialogDevice.setContentView(R.layout.select_bluetooth_connection)
                dialogDevice.window?.setBackgroundDrawableResource(R.color.Transparent)
                val linearDevices = dialogDevice.findViewById<LinearLayout>(R.id.linearBluetoothDevice)
                val devices = bluetooth.getBoundedDevices()

                for (device in devices){
                    val deviceInfoView = layoutInflater.inflate(R.layout.bluetooth_device,linearDevices,false)
                    val name = deviceInfoView.findViewById<TextView>(R.id.tvDeviceName)
                    val address = deviceInfoView.findViewById<TextView>(R.id.tvDeviceAdress)
                    name.text = device.name
                    address.text = device.address
                    linearDevices.addView(deviceInfoView)

                    deviceInfoView.setOnClickListener {
                        dialogDevice.dismiss()
                        val socket = ConnectThread().connect(device)
                        if (socket != null){
                            BluetoothConnection.socket = socket
                            BluetoothConnection.device = device
                            ConnectThread().writeData("Hi Chef",socket)
                            ibBluetooth.setImageDrawable(ResourcesCompat.getDrawable(resources,R.drawable.bluetooth,null))
                            Toast.makeText(this, getString(R.string.connectionSuccess), Toast.LENGTH_SHORT).show()

                        }else{
                            Toast.makeText(this, getString(R.string.connectionError), Toast.LENGTH_SHORT).show()
                        }
                        updateConnectionInfo()
                    }
                }

                dialogDevice.show()

            }



        }
    }

    private val deviceList : ArrayList<BluetoothDevice> = ArrayList()

    private val mReceiver = object : BroadcastReceiver(){
        override fun onReceive(context: Context?, intent: Intent?) {

            when(intent?.action){
                BluetoothDevice.ACTION_FOUND -> {
                    val device = intent.getParcelableExtra<BluetoothDevice>(BluetoothDevice.EXTRA_DEVICE) !!
                    deviceList.add(device)
                }
                BluetoothDevice.ACTION_ACL_DISCONNECTED -> {
                    BluetoothConnection.clearConnection()
                    updateConnectionInfo()
                    ibBluetooth.setImageDrawable(ResourcesCompat.getDrawable(resources,R.drawable.bluetooth_dark,null))
                }
            }
        }
    }


    private val tvConnectionDeviceName:TextView by lazy { findViewById(R.id.tvDeviceName) }
    private val tvConnectionDeviceAddress:TextView by lazy { findViewById(R.id.tvDeviceAdress) }

    private fun updateConnectionInfo(){
        val name:String
        val address: String
        if(BluetoothConnection.device != null && BluetoothConnection.socket != null ){
            name = BluetoothConnection.device!!.name
            address = BluetoothConnection.device!!.address
        }else{
            name = getString(R.string.connectionLost)
            address = ""
        }
        tvConnectionDeviceName.text = name
        tvConnectionDeviceAddress.text = address

    }
    private fun connectionCheack():Boolean{
        val device = BluetoothConnection.device
        val socket = BluetoothConnection.socket

        return if(device != null && socket != null ){
            if (device.bondState != BluetoothDevice.BOND_BONDED){
                BluetoothConnection.clearConnection()
                updateConnectionInfo()
                false

            }else true

        }else false
    }

    private fun setCarSkin(carType:String?){
        val carTypeArray = resources.getStringArray(R.array.spinnerCarType)
        if (carType != null){
            ivzl.visibility = ImageView.VISIBLE
            ivzr.visibility = ImageView.VISIBLE
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
                    ivFrontFog.setImageResource(R.drawable.mustang_front_foglights)
                    ivBackFog.setImageResource(R.drawable.mustang_rear_foglights)
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
                    ivFrontFog.setImageResource(R.drawable.mustang_front_foglights)
                    ivBackFog.setImageResource(R.drawable.mustang_rear_foglights)
                }
            }
            ibWindow.setOnClickListener {
                if(connectionCheack()) ConnectThread().writeData(getString(R.string.window),BluetoothConnection.socket!!)

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
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        unregisterReceiver(mReceiver)
    }

    override fun onResume() {
        carType = Setting.selectedCar
        val backColor = Setting.backColor
        val lineStartBackgroundColor = Setting.lineStartColor
        clickCount = 0
        setCarSkin(carType)
        if (backColor != null)
        ivBack.setBackgroundColor(backColor)
        if (lineStartBackgroundColor != null)
        lineStart.setBackgroundColor(lineStartBackgroundColor)

        super.onResume()
    }

}


package com.example.alarm

import android.app.Dialog
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.*
import androidx.constraintlayout.widget.ConstraintLayout
import com.flask.colorpicker.ColorPickerView
import com.flask.colorpicker.builder.ColorPickerDialogBuilder

class Settings : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_settings)
        val db = DbManager(this)
        db.openDb()

        val btnSetChange : Button = findViewById(R.id.btnSetChange)
        val background = findViewById<ConstraintLayout>(R.id.SettinMainLaoyt)

        val spinnerCarType : Spinner = findViewById(R.id.spinnerCarType)
        val carTypeArray = resources.getStringArray(R.array.spinnerCarType)
        spinnerCarType.adapter = ArrayAdapter(this,R.layout.lining_by_spinners,carTypeArray)
        val spinnerBackground : Spinner = findViewById(R.id.spinnerBackground)
        val backgroundArray = resources.getStringArray(R.array.backGround)
        spinnerBackground.adapter = ArrayAdapter(this,R.layout.lining_by_spinners,backgroundArray)
        val btnCreateInput = findViewById<Button>(R.id.btnHandInput)


        var carTitle = intent.getStringExtra("carTitle")
        val arrayCarTitle = resources.getStringArray(R.array.spinnerCarType)
        if (carTitle != null) {
            val spinnerStartPosition = arrayCarTitle.indexOf(carTitle)
            spinnerCarType.setSelection(spinnerStartPosition)
        }



        val ibBack : ImageButton = findViewById(R.id.ib_back)
        ibBack.setOnClickListener {
            this.onBackPressed()
        }

        val btnSetColorBackground : Button = findViewById(R.id.btnSetColor)
        var linerStartColor = if(Setting.lineStartColor != null) Setting.lineStartColor!!
            else intent.extras!!.getInt("linerStartColor")

        val btnSetColorCar : Button = findViewById(R.id.btnChangeColorcCar)
        var colorCar = if (Setting.backColor != null) Setting.backColor!!
        else intent.extras!!.getInt("colorCar")


        btnSetColorCar.setBackgroundColor(colorCar)
        btnSetChange.setBackgroundColor(colorCar)
        btnSetColorBackground.setBackgroundColor(colorCar)
        background.setBackgroundColor(linerStartColor)
        btnCreateInput.setBackgroundColor(colorCar)


        btnSetColorBackground.setOnClickListener {
            ColorPickerDialogBuilder
                .with(this)
                .setTitle("Choose Color")
                .wheelType(ColorPickerView.WHEEL_TYPE.FLOWER)
                .density(12)
                .lightnessSliderOnly()
                .setPositiveButton("ok") { _, colorNumb, _ ->
                    linerStartColor = colorNumb
                    background.setBackgroundColor(colorNumb)
                }
                .setNegativeButton("cancel") { dialog, _ -> dialog.dismiss() }
                .build()
                .show()
        }

        btnSetColorCar.setOnClickListener {
            ColorPickerDialogBuilder
                .with(this)
                .setTitle("Choose Color")
                .wheelType(ColorPickerView.WHEEL_TYPE.FLOWER)
                .density(12)
                .lightnessSliderOnly()
                .setPositiveButton("ok") { _, colorNumb, _ ->
                    colorCar = colorNumb
                    btnSetColorCar.setBackgroundColor(colorNumb)
                    btnSetChange.setBackgroundColor(colorNumb)
                    btnSetColorBackground.setBackgroundColor(colorNumb)
                    btnCreateInput.setBackgroundColor(colorNumb)
                }
                .setNegativeButton("cancel") { dialog, _ -> dialog.dismiss() }
                .build()
                .show()
        }

        btnSetChange.setOnClickListener {
            carTitle = spinnerCarType.selectedItem.toString()
            Setting.backColor = colorCar
            Setting.lineStartColor = linerStartColor
            Setting.selectedCar = carTitle

            db.updateSaves(SavesInfo(Setting.selectedCar,Setting.lineStartColor,Setting.backColor))
            this.finish()
        }


        btnCreateInput.setOnClickListener {
            val device = BluetoothConnection.device
            val socket = BluetoothConnection.socket

            if(device != null && socket != null ){
                val inputDialog = Dialog(this)
                inputDialog.setContentView(R.layout.iput_line)
                inputDialog.window?.setBackgroundDrawableResource(R.color.black50)

                val etInput = inputDialog.findViewById<EditText>(R.id.etInput)
                val btnInput = inputDialog.findViewById<Button>(R.id.btnInput)

                btnInput.setOnClickListener {
                    val inputMessage = etInput.text.toString()
                    if (inputMessage.isNotEmpty()){
                        ConnectThread().writeData(inputMessage,socket)
                    }
                }
            }else{
                Toast.makeText(this, getString(R.string.connectionLost), Toast.LENGTH_SHORT).show()
            }

        }
    }
}

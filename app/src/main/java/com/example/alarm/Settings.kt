package com.example.alarm

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.ImageButton
import android.widget.Spinner
import androidx.constraintlayout.widget.ConstraintLayout
import com.flask.colorpicker.ColorPickerView
import com.flask.colorpicker.builder.ColorPickerDialogBuilder

class Settings : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_settings)

        val btnSetChange : Button = findViewById(R.id.btnSetChange)
        val spinnerCarType : Spinner = findViewById(R.id.spinnerCarType)
        val loadingLayout : ConstraintLayout = findViewById(R.id.loadingSetting)

        val ibBack : ImageButton = findViewById(R.id.ib_back)
        ibBack.setOnClickListener {
            this.onBackPressed()
        }

        val btnSetColorBackground : Button = findViewById(R.id.btnSetColor)
        var linerStartColor = 0

        btnSetColorBackground.setOnClickListener {
            ColorPickerDialogBuilder
                .with(this)
                .setTitle("Choose Color")
                .wheelType(ColorPickerView.WHEEL_TYPE.FLOWER)
                .density(12)
                .lightnessSliderOnly()
                .setPositiveButton("ok") { _, colorNumb, _ ->
                    linerStartColor = colorNumb
                }
                .setNegativeButton("cancel") { dialog, _ -> dialog.dismiss() }
                .build()
                .show()
        }

        var colorCar = 0
        val btnSetColorCar : Button = findViewById(R.id.btnChangeColorcCar)
        btnSetColorCar.setOnClickListener {
            ColorPickerDialogBuilder
                .with(this)
                .setTitle("Choose Color")
                .wheelType(ColorPickerView.WHEEL_TYPE.FLOWER)
                .density(12)
                .lightnessSliderOnly()
                .setPositiveButton("ok") { _, colorNumb, _ ->
                    colorCar = colorNumb
                }
                .setNegativeButton("cancel") { dialog, _ -> dialog.dismiss() }
                .build()
                .show()
        }


        val spinnerBackground : Spinner = findViewById(R.id.spinnerBackground)
        val backgroundName = spinnerBackground.selectedItem.toString()
        intent.putExtra("backgroundName",backgroundName)


        btnSetChange.setOnClickListener {
            loadingLayout.visibility = ConstraintLayout.VISIBLE
            val intent = Intent(this,MainActivity::class.java)
            val carTitle = spinnerCarType.selectedItem.toString()
            if (colorCar !=0 ) intent.putExtra("colorCar",colorCar)
            if (linerStartColor !=0 ) intent.putExtra("linerStartColor",linerStartColor)
            intent.putExtra("carTitle",carTitle)
            startActivity(intent)
        }
        }

    override fun onResume() {
        super.onResume()
        val loadingLayout : ConstraintLayout = findViewById(R.id.loadingSetting)
        loadingLayout.visibility = ConstraintLayout.INVISIBLE
    }
    }

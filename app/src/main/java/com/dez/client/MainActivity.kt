package com.dez.client

import android.app.Activity
import android.os.Bundle
import android.content.Intent
import android.graphics.Color
import android.view.Gravity
import android.widget.*

class MainActivity : Activity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val layout = LinearLayout(this).apply {
            orientation = LinearLayout.VERTICAL
            gravity = Gravity.CENTER
            setPadding(24, 35, 24, 25)
            setBackgroundColor(Color.rgb(12, 8, 20))
        }

        val title = TextView(this).apply {
            text = "DEZ CLIENT"
            textSize = 30f
            setTextColor(Color.WHITE)
            gravity = Gravity.CENTER
        }
        layout.addView(title)

        val launch = Button(this).apply {
            text = "ЗАПУСТИТЬ MINECRAFT"
        }
        layout.addView(launch)

        launch.setOnClickListener {
            val intent = packageManager.getLaunchIntentForPackage("com.mojang.minecraftpe")
            if (intent != null) {
                startActivity(intent)
            } else {
                Toast.makeText(this, "Minecraft не найден!", Toast.LENGTH_SHORT).show()
            }
        }

        setContentView(layout)
    }
}

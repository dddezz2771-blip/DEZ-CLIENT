package com.dez.client

import android.app.Activity
import android.os.Bundle
import android.content.Intent
import android.graphics.Color
import android.graphics.Typeface
import android.view.Gravity
import android.view.View
import android.widget.*

class MainActivity : Activity() {

    private val purple = Color.rgb(170, 70, 255)
    private val dark = Color.rgb(12, 8, 20)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val layout = LinearLayout(this).apply {
            orientation = LinearLayout.VERTICAL
            gravity = Gravity.CENTER
            setPadding(24, 35, 24, 25)
            setBackgroundColor(dark)
        }

        fun text(value: String, size: Float): TextView {
            return TextView(this).apply {
                text = value
                textSize = size
                setTextColor(Color.WHITE)
                gravity = Gravity.CENTER
                typeface = Typeface.DEFAULT_BOLD
            }
        }

        fun button(value: String): Button {
            return Button(this).apply {
                text = value
                textSize = 15f
                setTextColor(Color.WHITE)
                setBackgroundColor(Color.rgb(75, 25, 115))
            }
        }

        layout.addView(text("DEZ CLIENT", 30f))
        layout.addView(text("BEDROCK PVP CLIENT", 12f))

        val status = text("", 14f)
        status.setPadding(0, 30, 0, 30)
        layout.addView(status)

        val launch = button("ЗАПУСТИТЬ MINECRAFT")
        layout.addView(launch)

        launch.setOnClickListener {
            val intent = packageManager
                .getLaunchIntentForPackage("com.mojang.minecraftpe")

            if (intent != null) {
                startActivity(intent)
            } else {
                Toast.makeText(
                    this,
                    "Minecraft не найден!",
                    Toast.LENGTH_SHORT
                ).show()
            }
        }

        val home = button("HOME")
        layout.addView(home)

        val bottom = LinearLayout(this).apply {
            orientation = LinearLayout.HORIZONTAL
            gravity = Gravity.CENTER
        }

        listOf("HUD", "ЧАТ", "НАСТРОЙКИ").forEach { name ->
            val b = button(name)
            bottom.addView(
                b,
                LinearLayout.LayoutParams(0, 65, 1f)
            )
            b.setOnClickListener {
                Toast.makeText(
                    this,
                    "$name — скоро появится",
                    Toast.LENGTH_SHORT
                ).show()
            }
        }

        layout.addView(bottom)

        val telegram = text("TELEGRAM: @dezTGK", 13f)
        telegram.setTextColor(purple)
        telegram.setPadding(0, 35, 0, 0)
        layout.addView(telegram)

        telegram.setOnClickListener {
            startActivity(Intent(
                Intent.ACTION_VIEW,
                android.net.Uri.parse("https://t.me/dezTGK")
            ))
        }

        setContentView(layout)

        status.text =
            if (packageManager.getLaunchIntentForPackage(
                "com.mojang.minecraftpe"
            ) != null) "● MINECRAFT УСТАНОВЛЕН"
            else "● MINECRAFT НЕ НАЙДЕН"
    }
}

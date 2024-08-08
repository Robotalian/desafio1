package com.example.descuentos

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val btnFunction1: Button = findViewById(R.id.btnFunction1)
        val btnFunction2: Button = findViewById(R.id.btnFunction2)
        val btnFunction3: Button = findViewById(R.id.btnFunction3)

        btnFunction1.setOnClickListener {
            val intent = Intent(this, Function1Activity::class.java)
            startActivity(intent)
        }

        btnFunction2.setOnClickListener {
            val intent = Intent(this, Function2Activity::class.java)
            startActivity(intent)
        }

        btnFunction3.setOnClickListener {
            val intent = Intent(this, Function3Activity::class.java)
            startActivity(intent)
        }
    }
}

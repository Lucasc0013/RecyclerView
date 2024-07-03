package com.lucas.recyclerview

import android.content.Intent
import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class DetailContact : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_detail_contact)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val tvShare = findViewById<TextView>(R.id.tv_share_contact)
        val tvName = findViewById<TextView>(R.id.tv_name)
        val tvPhone = findViewById<TextView>(R.id.tv_phone)
        val ivImage = findViewById<ImageView>(R.id.image)

        val name = intent.getStringExtra("name")
        val phone = intent.getStringExtra("phone")
        val icon = intent.getIntExtra("icon", R.drawable.sample1)

        tvName.text = name
        tvPhone.text = phone
        ivImage.setImageResource(icon)

        tvShare.setOnClickListener {
            shareContact("$name $phone $icon")
        }
    }

    private fun shareContact(data: String) {
        val sendIntent: Intent = Intent().apply {
            action = Intent.ACTION_SEND
            putExtra(Intent.EXTRA_TEXT, data)
            type = "text/plain"
        }

        val shareIntent = Intent.createChooser(sendIntent, null)
        startActivity(shareIntent)
    }

}

package com.wonbin.waterreflectionimagetransformation

import android.os.Bundle
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        var origin_view = findViewById<ImageView>(R.id.origin_view)
        var trans_view = findViewById<ImageView>(R.id.trans_view)
        var sampleUrl = "https://developer.android.com/images/brand/Android_Robot_200.png"

        Glide.with(this).load(sampleUrl).into(origin_view)
        Glide.with(this).load(sampleUrl).transform(WaterReflectionTransformation()).into(trans_view)
    }
}
package com.ankur.idscanningproject

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.util.Log
import android.widget.ImageView
import androidx.appcompat.widget.AppCompatImageView
import java.io.InputStream
import java.net.URL
import java.net.URLConnection
import kotlin.concurrent.thread

class MainActivity : AppCompatActivity() {
    lateinit var imageView: AppCompatImageView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val url: String =
            "https://www.agora.io/en/wp-content/uploads/2021/05/how-to-create-an-android-video-streaming-application-with-3-lines-of-code-2-877x1024.png"

        imageView = findViewById(R.id.image)

        val handler = Handler(Looper.getMainLooper())

        thread(start = true) {
            val bitmap: Bitmap = downloadBitmap(url)
            Log.d("tag", Thread.currentThread().name.toString())
            handler.post {
                Log.d("tag", Thread.currentThread().name.toString())
                imageView.setImageBitmap(bitmap)
            }
        }
    }

    private fun downloadBitmap(imageUrl: String): Bitmap {
        val connection: URLConnection = URL(imageUrl).openConnection()
        connection.connect()
        val inputStreme: InputStream = connection.getInputStream()
        val bitmap = BitmapFactory.decodeStream(inputStreme)
        inputStreme.close()
        return bitmap
    }
}
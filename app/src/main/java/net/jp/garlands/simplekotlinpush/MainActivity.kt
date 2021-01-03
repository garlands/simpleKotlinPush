package net.jp.garlands.simplekotlinpush

import android.graphics.BitmapFactory
import android.os.Bundle
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.ktx.Firebase
import com.google.firebase.storage.ktx.storage
import java.io.File


class MainActivity : AppCompatActivity() {
    val storage = Firebase.storage

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

    }

    fun touchButton(view: View) {
        // Do something in response to button
        var storageRef = storage.reference
        var imageRef = storageRef.child("test.jpeg")
        val imageView = findViewById<ImageView>(R.id.imageView)

        val localFile = File.createTempFile("images", "jpg")

        imageRef.getFile(localFile).addOnSuccessListener {
            // Local temp file has been created
            val myBitmap = BitmapFactory.decodeFile(localFile.getAbsolutePath())
            imageView.setImageBitmap(myBitmap);
        }.addOnFailureListener {
            // Handle any errors
        }

        var textRef = storageRef.child("top.txt")

        textRef.getBytes(1024).addOnSuccessListener { byteArray ->
            // Data for "images/island.jpg" is returned, use this as needed
            val charset = Charsets.UTF_8
            val textView = findViewById<TextView>(R.id.textView)
            val textString = byteArray.toString(charset)
            textView.text = textString
        }.addOnFailureListener {
            // Handle any errors
        }


    }
}
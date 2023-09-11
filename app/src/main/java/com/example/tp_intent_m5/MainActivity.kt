package com.example.tp_intent_m5

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.net.Uri
import android.os.Build
import android.os.Parcelable
import android.view.View
import android.widget.ImageView
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.annotation.RequiresApi

class MainActivity : AppCompatActivity() {
    private val galleryImage = registerForActivityResult(ActivityResultContracts.GetContent()) { imageUri ->
        // Gérez ici l'image sélectionnée
        val imageView = findViewById<ImageView>(R.id.id_img_modifiable)
        imageView.setImageURI(imageUri)
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        val imageView = findViewById<ImageView>(R.id.id_img_modifiable)

        imageView.setOnClickListener {
            // Lancez l'activité de sélection d'image en utilisant l'API Activity Result
            galleryImage.launch("image/*")
        }

        when {
            intent?.action == Intent.ACTION_SEND -> {
                if (intent.type?.startsWith("image/") == true) {
                    handleSendImage(intent) // Handle single image being sent
                }
            }
            else -> {
                // Handle other intents, such as being started from the home screen
            }
        }

    }
    @RequiresApi(Build.VERSION_CODES.TIRAMISU)
    fun handleSendImage(intent: Intent) {
        val imageView = findViewById<ImageView>(R.id.id_img_modifiable)
        val uri = intent?.getParcelableExtra<Uri>(Intent.EXTRA_STREAM) // version 11 Android

        // val uri = intent?.getParcelableExtra(Intent.EXTRA_STREAM, Uri::class.java) ?: return // version actuelle
        imageView.setImageURI(uri)
    }
    fun  openURL(view: View){
        val url = "https://nickelodeon.fandom.com/wiki/SpongeBob_SquarePants_(character)"
        val  intent = Intent(Intent.ACTION_VIEW)
        intent.data = Uri.parse(url)
        startActivity(intent)

    }

    fun openNextImg(view: View){
        //val intent = Intent(this@MainActivity, SwipeActivity::class.java)
        val intent= Intent(this,SwipeActivity::class.java)

        startActivity(intent)

    }

}

// var toast = Toast.makeText(this, "test ", Toast.LENGTH_LONG)
// toast.show()
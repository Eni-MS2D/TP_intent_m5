package com.example.tp_intent_m5

import android.media.AudioManager
import android.media.MediaPlayer
import android.os.Build
import android.os.Bundle
import android.os.VibrationEffect
import android.os.Vibrator
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity


class SwipeActivity : AppCompatActivity() {
    private var mediaPlayer: MediaPlayer? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_swipe)

        val playButton = findViewById<Button>(R.id.id_play_music)
        val btnVibrate = findViewById<Button>(R.id.id_btn_vibrate)
        playButton.setOnClickListener {
            playAudio()
        }
        btnVibrate.setOnClickListener{
            vibratePhone()
        }

    }


    fun playAudio(){
        val audioUrl = "https://actions.google.com/sounds/v1/alarms/bugle_tune.ogg"
        mediaPlayer = MediaPlayer()
        mediaPlayer!!.setAudioStreamType(AudioManager.STREAM_MUSIC)
        mediaPlayer!!.setDataSource(audioUrl)
        mediaPlayer!!.prepare()
        mediaPlayer!!.start()
    }
    fun vibratePhone(){
        val v = getSystemService(VIBRATOR_SERVICE) as Vibrator
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            v.vibrate(VibrationEffect.createOneShot(500, VibrationEffect.DEFAULT_AMPLITUDE))
        } else {
            //deprecated in API 26
            v.vibrate(500)
        }
    }
}
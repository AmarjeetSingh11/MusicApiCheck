package com.example.musicapp

import android.content.Intent
import android.media.AudioManager
import android.media.MediaPlayer
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import android.widget.SeekBar
import android.widget.TextView
import android.widget.Toast
import com.squareup.picasso.Picasso
import java.lang.Exception

class PlayerScreen : AppCompatActivity() {

    lateinit var mediaPlayer: MediaPlayer
    var totalTime:Int=0
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_player_screen)

        val songImage = intent.getStringExtra("songImage")
        val songName = intent.getStringExtra("songName")

        val urlSong=intent.getStringExtra("songUrl")

        val image=findViewById<ImageView>(R.id.songImage)
        val name=findViewById<TextView>(R.id.songName)


        val imageurl=songImage
        Picasso.get().load(imageurl).into(image)
        name.text=songName



        mediaPlayer= MediaPlayer()

        val playbtn=findViewById<ImageView>(R.id.play)
        val pausebtn=findViewById<ImageView>(R.id.pause)
        val stopbtn=findViewById<ImageView>(R.id.stop)
        val seekBar=findViewById<SeekBar>(R.id.seekBar)

        playbtn.setOnClickListener {
            val UrlMusic=urlSong
            mediaPlayer.setAudioStreamType(AudioManager.STREAM_MUSIC)

            try{
                mediaPlayer.setDataSource(UrlMusic)
                mediaPlayer.prepare()
                mediaPlayer.start()
            }catch (e:Exception){
                e.printStackTrace()
            }
            Toast.makeText(this,"Music is playing",Toast.LENGTH_SHORT).show()
        }
        pausebtn.setOnClickListener {
            mediaPlayer.pause()
        }
        stopbtn.setOnClickListener {
            mediaPlayer.stop()
            mediaPlayer.reset()
            mediaPlayer.release()
        }
    }
}



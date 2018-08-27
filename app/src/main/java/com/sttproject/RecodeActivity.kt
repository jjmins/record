package com.sttproject

import android.Manifest
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.media.MediaRecorder
import android.os.Environment
import kotlinx.android.synthetic.main.activity_recode.*
import org.jetbrains.anko.sdk25.coroutines.onClick
import java.io.File
import java.text.SimpleDateFormat
import java.util.*
import android.media.MediaPlayer
import android.util.Log

class RecodeActivity : AppCompatActivity(){
    var player : MediaPlayer? = null
    var recorder : MediaRecorder? = null
    var path = ""
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_recode)

        recodeStart.onClick {
            recodestart()
        }

        recodeRead.onClick {
            recoderead()
        }

        recodeStop.onClick {
            recorder!!.release()
            recorder = null

        }
    }

    fun recoderead(){
        playAudio(path)
    }

    fun playAudio(url : String){
        killMediaPlayer()
        player = MediaPlayer()
        Log.e("url",url)
        player!!.setDataSource(url)
        player!!.prepare()
        player!!.start()
    }

    fun killMediaPlayer() {
        if(player != null){
                player!!.release()
        }
    }


    fun recodestart(){
        val dirPath = Environment.getExternalStorageDirectory().absolutePath + "/Recode/"
        var file = File(dirPath)

        if (!file.exists()) file.mkdirs()
        val recodePath = "RECODE_" + SimpleDateFormat("yyyyMMdd_HHmmss").format(Date())
        path = "$dirPath$recodePath.3gp"
        recorder = MediaRecorder()
        recorder!!.setAudioSource(MediaRecorder.AudioSource.MIC)
        recorder!!.setOutputFormat(MediaRecorder.OutputFormat.MPEG_4)
        recorder!!.setAudioEncoder(MediaRecorder.AudioEncoder.DEFAULT)
        recorder!!.setOutputFile(path)
        recorder!!.prepare()
        recorder!!.start()
    }



}

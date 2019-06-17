package com.henry.volume

import io.flutter.plugin.common.MethodCall
import io.flutter.plugin.common.MethodChannel
import io.flutter.plugin.common.MethodChannel.MethodCallHandler
import io.flutter.plugin.common.MethodChannel.Result
import io.flutter.plugin.common.PluginRegistry.Registrar
import android.app.Activity
import android.content.Context
import android.media.AudioManager

class VolumePlugin(private var activity: Activity) : MethodCallHandler {

    private lateinit var audioManager: AudioManager
    private var streamType: Int = AudioManager.STREAM_MUSIC

    companion object {
        @JvmStatic
        fun registerWith(registrar: Registrar) {
            val channel = MethodChannel(registrar.messenger(), "volume")
            channel.setMethodCallHandler(VolumePlugin(registrar.activity()))
        }
    }

    override fun onMethodCall(call: MethodCall, result: Result) {
        if (call.method == "getPlatformVersion") {
            result.success("Android ${android.os.Build.VERSION.RELEASE}")
        } else {
            result.notImplemented()
        }
    }

    private fun initAudioManager() {
        audioManager = this.activity.applicationContext.getSystemService(Context.AUDIO_SERVICE) as AudioManager
    }

    fun setSteamType(type: Int) {
        this.streamType = type
    }

    fun getMaxVolume(): Int {
        initAudioManager()
        return audioManager.getStreamMaxVolume(streamType)
    }

    fun getVolume(): Int {
        initAudioManager()
        return audioManager.getStreamVolume(streamType)
    }

    fun setVolume(volume: Int, showUI: Boolean=false): Int {
        initAudioManager()
        if (showUI)
            audioManager.setStreamVolume(streamType, volume, AudioManager.FLAG_SHOW_UI)
        return audioManager.getStreamVolume(streamType)
    }
}

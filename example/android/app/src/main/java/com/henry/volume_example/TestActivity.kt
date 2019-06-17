package com.henry.volume_example

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.SeekBar
import android.widget.Toast
import com.henry.volume.VolumePlugin
import io.flutter.app.FlutterActivity
import kotlinx.android.synthetic.main.activity_test.*

class TestActivity : FlutterActivity() {
    lateinit var volumeManager : VolumePlugin

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_test)
        volumeManager = VolumePlugin(this)
        seekBar.max = volumeManager.getMaxVolume()
        seekBar.incrementProgressBy(1)
        seekBar.progress = volumeManager.getVolume()
        seekBar.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener {
            override fun onStartTrackingTouch(p0: SeekBar?) {
            }

            override fun onStopTrackingTouch(p0: SeekBar?) {
            }

            override fun onProgressChanged(seekBar: SeekBar, i: Int, b: Boolean) {
                // Display the current progress of SeekBar
                volumeManager.setVolume(volume = i)
            }
        })
    }
}

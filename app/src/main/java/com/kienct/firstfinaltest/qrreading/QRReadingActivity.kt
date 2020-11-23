package com.kienct.firstfinaltest.qrreading

import android.Manifest
import android.annotation.SuppressLint
import android.content.Intent
import android.content.pm.PackageManager
import android.os.Bundle
import android.view.SurfaceHolder
import android.view.SurfaceHolder.Callback
import android.view.SurfaceView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import com.google.android.gms.vision.CameraSource
import com.google.android.gms.vision.Detector
import com.google.android.gms.vision.Detector.Detections
import com.google.android.gms.vision.barcode.Barcode
import com.google.android.gms.vision.barcode.BarcodeDetector
import com.kienct.firstfinaltest.R
import com.kienct.firstfinaltest.sharedpreferences.SharedPreferencesActivity
import kotlinx.android.synthetic.main.activity_q_r_reading.*
import java.io.IOException


class QRReadingActivity : AppCompatActivity() {
    private lateinit var scanner: SurfaceView
    private lateinit var detector: BarcodeDetector
    private lateinit var source: CameraSource
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_q_r_reading)
        btNext3.setOnClickListener {
            val intent = Intent(this, SharedPreferencesActivity::class.java)
            startActivity(intent)
        }
        if (ActivityCompat.checkSelfPermission(
                this,
                Manifest.permission.CAMERA
            ) != PackageManager.PERMISSION_GRANTED
        ) {
            ActivityCompat.requestPermissions(
                this,
                arrayOf(Manifest.permission.CAMERA),
                REQUEST_CAMERA
            )
            return
        }
        scanner = surfaceView
        detector =
            BarcodeDetector.Builder(applicationContext).setBarcodeFormats(Barcode.QR_CODE)
                .build()
        source = CameraSource.Builder(this, detector).setFacing(
            CameraSource.CAMERA_FACING_BACK
        )
            .setRequestedFps(35.0f)
            .setAutoFocusEnabled(true)
            .build()

        scanner.holder.addCallback(object : Callback {
            override fun surfaceCreated(holder: SurfaceHolder) {
                try {
                    if (ActivityCompat.checkSelfPermission(
                            this@QRReadingActivity,
                            Manifest.permission.CAMERA
                        ) == PackageManager.PERMISSION_GRANTED
                    ) {
                        source.start(scanner.holder)
                    } else {
                        ActivityCompat.requestPermissions(
                            this@QRReadingActivity,
                            arrayOf(Manifest.permission.CAMERA),
                            REQUEST_CAMERA_PERMISSION
                        )
                    }
                } catch (e: IOException) {
                    e.printStackTrace()
                }
            }

            override fun surfaceChanged(
                holder: SurfaceHolder,
                format: Int,
                width: Int,
                height: Int
            ) {
            }

            override fun surfaceDestroyed(holder: SurfaceHolder) {
                source.stop()
            }
        })

        detector.setProcessor(object : Detector.Processor<Barcode> {
            override fun release() {
            }

            override fun receiveDetections(detections: Detections<Barcode>) {
                val code = detections.detectedItems
                if (code.size() != 0) {
                    camera_capture_button.post {
                        kotlin.run {
                            camera_capture_button.text = code.valueAt(0).displayValue
                        }
                    }
                } else {
                    camera_capture_button.post {
                        kotlin.run {
                            camera_capture_button.text = "Scanning"
                        }
                    }
                }
            }
        })

    }


    @SuppressLint("MissingPermission")
    override fun onRequestPermissionsResult(
        requestCode: Int, permissions: Array<String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if (requestCode == REQUEST_CAMERA && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
            try {
                source.start(scanner.holder)
            } catch (e: IOException) {
                e.printStackTrace()
            }
        }
    }

    companion object {
        const val REQUEST_CAMERA = 1
        const val REQUEST_CAMERA_PERMISSION = 201
    }
}
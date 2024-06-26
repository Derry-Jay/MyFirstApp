package com.example.myfirstapp.utils

//import android.os.Bundle
import android.Manifest
import android.app.AlertDialog
import android.app.Service
import android.content.Intent
import android.content.pm.PackageManager
import android.location.Location
import android.location.LocationListener
import android.location.LocationManager
import android.os.IBinder
import android.provider.Settings
import android.util.Log
import android.view.View
import androidx.core.app.ActivityCompat

/**
 * Created by ciprus on 1/8/16.
 */

class GPSTracker(private val v: View) : Service(), LocationListener {
    // flag for GPS status
    private var isGPSEnabled: Boolean = false

    // flag for network status
    private var isNetworkEnabled: Boolean = false

    // flag for GPS status
    private var canGetLocation: Boolean = false

    private var location: Location? = null // location
    private var latitude: Double = 0.0 // latitude
    private var longitude: Double = 0.0 // longitude
    private var accuracy: Double = 0.0 //accuracy

    // Declaring a Location Manager
    private var locationManager: LocationManager? = null

    init {
        getLocation()
    }

    private fun getLocation(): Location? {
        try {
            locationManager = getDeviceService(v, LOCATION_SERVICE) as LocationManager?

            // getting GPS status
            isGPSEnabled = locationManager
                ?.isProviderEnabled(LocationManager.GPS_PROVIDER) ?: false

            // getting network status
            isNetworkEnabled = locationManager
                ?.isProviderEnabled(LocationManager.NETWORK_PROVIDER) ?: false

            if (!isGPSEnabled && !isNetworkEnabled) {
                // no network provider is enabled
            } else {
                this.canGetLocation = true
                if (isNetworkEnabled) {
                    if (ActivityCompat.checkSelfPermission(
                            this,
                            Manifest.permission.ACCESS_FINE_LOCATION
                        ) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(
                            this,
                            Manifest.permission.ACCESS_COARSE_LOCATION
                        ) != PackageManager.PERMISSION_GRANTED
                    ) {
                        // TODO: Consider calling
                        //    ActivityCompat#requestPermissions
                        // here to request the missing permissions, and then overriding
                        //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
                        //                                          int[] grantResults)
                        // to handle the case where the user grants the permission. See the documentation
                        // for ActivityCompat#requestPermissions for more details.
                        return null
                    }
                    locationManager!!.requestLocationUpdates(
                        LocationManager.NETWORK_PROVIDER,
                        MIN_TIME_BW_UPDATES,
                        MIN_DISTANCE_CHANGE_FOR_UPDATES.toFloat(), this
                    )
                    Log.d("Network", "Network")
                    if (locationManager != null) {
                        location = locationManager!!
                            .getLastKnownLocation(LocationManager.NETWORK_PROVIDER)
                        if (location != null) {
                            latitude = location!!.latitude
                            longitude = location!!.longitude
                            accuracy = location!!.accuracy.toDouble()
                        }
                    }
                }
                // if GPS Enabled get lat/long using GPS Services
                if (isGPSEnabled) {
                    if (location == null) {
                        locationManager!!.requestLocationUpdates(
                            LocationManager.GPS_PROVIDER,
                            MIN_TIME_BW_UPDATES,
                            MIN_DISTANCE_CHANGE_FOR_UPDATES.toFloat(), this
                        )
                        Log.d("GPS Enabled", "GPS Enabled")
                        if (locationManager != null) {
                            location = locationManager!!
                                .getLastKnownLocation(LocationManager.GPS_PROVIDER)
                            if (location != null) {
                                latitude = location!!.latitude
                                longitude = location!!.longitude
                                accuracy = location!!.accuracy.toDouble()
                            }
                        }
                    }
                }
            }
        } catch (e: Exception) {
            e.printStackTrace()
        }

        return location
    }

    /**
     * Stop using GPS listener
     * Calling this function will stop using GPS in your app
     */
    fun stopUsingGPS() {
        if (locationManager != null) {
            locationManager!!.removeUpdates(this@GPSTracker)
        }
    }

    /**
     * Function to get latitude
     */
    fun getLatitude(): Double {
        latitude = location?.latitude ?: 0.0

        // return latitude
        return latitude
    }

    /**
     * Function to get longitude
     */
    fun getLongitude(): Double {
        longitude = location?.longitude ?: 0.0

        // return longitude
        return longitude
    }

    /**
     * Function to get accuracy
     */
    fun getAccuracy(): Double {
        accuracy = location?.accuracy?.toDouble() ?: 0.0

        // return longitude
        return accuracy
    }


    /**
     * Function to check GPS/wifi enabled
     * @return boolean
     */
    fun canGetLocation(): Boolean {
        return this.canGetLocation
    }

    /**
     * Function to show settings alert dialog
     * On pressing Settings button will lauch Settings Options
     */
    fun showSettingsAlert() {
        val alertDialog = AlertDialog.Builder(v.context)
        // Setting Dialog Title
        alertDialog.setTitle("GPS is settings")

        // Setting Dialog Message
        alertDialog.setMessage("GPS is not enabled. Do you want to go to settings menu?")

        // On pressing Settings button
        alertDialog.setPositiveButton("Settings") { dialog, which ->
            val intent = Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS)
            v.context.startActivity(intent)
            println(dialog)
            println(which)
        }

        // on pressing cancel button
        alertDialog.setNegativeButton("Cancel") { dialog, which ->
            dialog.cancel()
            println(which)
        }

        // Showing Alert Message
        alertDialog.show()
    }


    override fun onLocationChanged(location: Location) {
        latitude = location.latitude
        longitude = location.longitude
        accuracy = location.accuracy.toDouble()
    }

    override fun onProviderDisabled(provider: String) {
        println(provider)
    }

    override fun onProviderEnabled(provider: String) {
        println(provider)
    }

    override fun onBind(arg0: Intent): IBinder? {
        return null
    }


    companion object {
        // The minimum distance to change Updates in meters
        const val MIN_DISTANCE_CHANGE_FOR_UPDATES: Long = 10 // 10 meters

        // The minimum time between updates in milliseconds
        const val MIN_TIME_BW_UPDATES: Long = (1000 * 60 * 1 // 1 minute
                ).toLong()
    }
}
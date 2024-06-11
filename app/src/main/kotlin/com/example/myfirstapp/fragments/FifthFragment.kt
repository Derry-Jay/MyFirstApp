package com.example.myfirstapp.fragments

import android.Manifest
import android.content.Context.LOCATION_SERVICE
import android.content.pm.PackageManager
import android.location.Location
import android.location.LocationListener
import android.location.LocationManager
import android.location.LocationManager.GPS_PROVIDER
import android.location.LocationManager.NETWORK_PROVIDER
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.app.ActivityCompat
import androidx.fragment.app.Fragment
import com.example.myfirstapp.R
import com.example.myfirstapp.databinding.FragmentFifthBinding
import com.example.myfirstapp.extensions.getDeviceService
import com.example.myfirstapp.extensions.hasLocationPermission
import com.example.myfirstapp.extensions.str
import com.example.myfirstapp.extensions.stringFromResource

/**
 * A simple [Fragment] subclass as the default destination in the navigation.
 */
class FifthFragment : Fragment(), LocationListener {

    private var _binding: FragmentFifthBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    private fun setLocationData(loc: Location) {
        val spd: Double = if (loc.hasSpeed()) loc.speed * 3.6 else 0.0
        binding.latitude.text = "Latitude: ${loc.latitude.str}"
        binding.longitude.text = "Longitude: ${loc.longitude.str}"
        binding.accuracy.visibility = if (loc.hasAccuracy()) View.VISIBLE else View.GONE
        binding.accuracy.text = "Accuracy: ${loc.accuracy.str}"
        binding.speed.visibility = if (loc.hasSpeed()) View.VISIBLE else View.GONE
        binding.speed.text = "Speed: ${spd.str} Km/Hr"
        binding.height.visibility = if (loc.hasAltitude()) View.VISIBLE else View.GONE
        binding.height.text = "Height: ${loc.altitude.str}"
        binding.bearing.visibility = if (loc.hasBearing()) View.VISIBLE else View.GONE
        binding.bearing.text = "Bearing: ${loc.bearing.str}"
        binding.time.text = "Time: "
        binding.provider.text = "Provider: ${loc.provider}"
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {

        _binding = FragmentFifthBinding.inflate(inflater, container, false)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val lm: LocationManager? = view.getDeviceService(LOCATION_SERVICE) as LocationManager?

        // getting GPS status
        val isGPSEnabled = lm?.isProviderEnabled(GPS_PROVIDER) ?: false

        // getting network status
        val isNetworkEnabled = lm?.isProviderEnabled(NETWORK_PROVIDER) ?: false
        binding.latitude.text = "Latitude: "
        binding.longitude.text = "Longitude: "
        binding.accuracy.text = "Accuracy: "
        binding.speed.text = "Speed: "
        binding.height.text = "Height: "
        binding.bearing.text = "Bearing: "
        binding.time.text = "Time: "
        binding.provider.text = "Provider: "
        if (!isGPSEnabled && !isNetworkEnabled) {
// no network provider is enabled
        } else if (view.hasLocationPermission) {
            if (ActivityCompat.checkSelfPermission(
                    view.context,
                    Manifest.permission.ACCESS_FINE_LOCATION
                ) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(
                    view.context,
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
                return
            }
            lm?.requestLocationUpdates(
                GPS_PROVIDER,
                view.stringFromResource(R.string.minimum_time_between_updates).toLong(),
                view.stringFromResource(R.string.minimum_distance_change_for_updates).toFloat(),
                this
            )
        } else if (isNetworkEnabled && !isGPSEnabled && lm!!.getLastKnownLocation(NETWORK_PROVIDER) != null) {
            setLocationData(
                lm.getLastKnownLocation(NETWORK_PROVIDER)!!
            )
        } else {
            activity?.let {
                ActivityCompat.requestPermissions(
                    it, arrayOf(
                        Manifest.permission.ACCESS_FINE_LOCATION,
                        Manifest.permission.ACCESS_COARSE_LOCATION
                    ), 1
                )
            }
            if (view.hasLocationPermission) {
                lm?.requestLocationUpdates(
                    GPS_PROVIDER,
                    view.stringFromResource(R.string.minimum_time_between_updates).toLong(),
                    view.stringFromResource(R.string.minimum_distance_change_for_updates).toFloat(),
                    this
                )
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onLocationChanged(location: Location) {
        setLocationData(location)
    }

    override fun onProviderEnabled(provider: String) {
        binding.provider.text = "Provider: $provider"
        binding.provider.visibility = View.VISIBLE
    }

    override fun onProviderDisabled(provider: String) {
        binding.provider.text = "Provider: $provider"
        binding.provider.visibility = View.GONE
    }
}
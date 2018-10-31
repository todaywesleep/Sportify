package pro.papaya.canyo.sportify.utils

import android.Manifest
import android.content.Context
import android.content.pm.PackageManager
import android.location.Location
import android.location.LocationManager
import android.support.v4.app.ActivityCompat
import android.support.v4.content.ContextCompat
import android.util.Log
import android.widget.Toast
import pro.papaya.canyo.myapplication.R

class NavigationUtils{
    companion object {
        fun getCurrentLocation(context: Context): Location?{
            val locationManager = context.getSystemService(Context.LOCATION_SERVICE) as LocationManager
            var currentLocation: Location? = null

            if (ContextCompat.checkSelfPermission(context, Manifest.permission.ACCESS_COARSE_LOCATION)
                    == PackageManager.PERMISSION_GRANTED) {
                currentLocation = locationManager.getLastKnownLocation(LocationManager.GPS_PROVIDER)
            }else{
                Toast.makeText(
                        context,
                        context.getString(R.string.gps_permissions_denied),
                        Toast.LENGTH_SHORT
                ).show()
            }

            return currentLocation
        }
    }
}
package com.yanyushkin.ex1.tasks2_2

import android.content.Intent
import android.content.pm.PackageManager
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.design.widget.Snackbar
import android.support.v4.app.ActivityCompat
import android.support.v4.content.ContextCompat
import android.view.View
import android.widget.Toast

class MainActivity : AppCompatActivity() {

    private var myIntent: Intent? = null
    private val REQUEST_CODE_PERMISSION_LOCATION: Int = 1
    private val APP_KEY = "com.yanyushkin.ex1.tasks2"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    fun buttonStartMyAppClick(v: View) {
        myIntent = packageManager.getLaunchIntentForPackage(APP_KEY)

        if (myIntent != null) {
            myIntent!!.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
            startActivity(myIntent)
        } else {
            Toast.makeText(this, "Not found your app", Toast.LENGTH_SHORT).show()
        }
    }

    fun buttonStartAppCall(v: View) {
        myIntent = Intent(Intent.ACTION_DIAL)

        if (myIntent!!.resolveActivity(packageManager) != null)
            startActivity(myIntent)
        else
            Toast.makeText(this, "Error", Toast.LENGTH_SHORT).show()
    }

    fun locationOnClick(v: View) {
        val permissionStatus = ContextCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_FINE_LOCATION)

        if (permissionStatus == PackageManager.PERMISSION_DENIED) {
            if (shouldShowRequestPermissionRationale(android.Manifest.permission.ACCESS_FINE_LOCATION))
                Snackbar.make(v, "I need your location!!!", Snackbar.LENGTH_LONG).show()

            ActivityCompat.requestPermissions(
                this,
                Array<String>(1) { android.Manifest.permission.ACCESS_FINE_LOCATION },
                REQUEST_CODE_PERMISSION_LOCATION
            )
        } else {
            Toast.makeText(this, "Permission has already been given", Toast.LENGTH_SHORT).show()
        }
    }

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)

        if (requestCode == REQUEST_CODE_PERMISSION_LOCATION) {
            if (grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED)
                Toast.makeText(this, "Fine! Thank you!", Toast.LENGTH_SHORT).show()
            else
                Toast.makeText(this, "Hmm! Are you durachok? Give me location!", Toast.LENGTH_SHORT).show()
        }
    }
}

package com.yanyushkin.ex1.tasks2

import android.app.Activity
import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    val REQUEST_CODE_MESSAGE = 1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    fun button1Click(v: View) {
        val myIntent = Intent(this, SecondActivity::class.java)
        startActivityForResult(myIntent, REQUEST_CODE_MESSAGE)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (resultCode == Activity.RESULT_OK) {
            when (requestCode) {
                REQUEST_CODE_MESSAGE -> {
                    textView.text = data!!.getStringExtra("message")
                }
            }
        } else {
            textView.text = ""
            Toast.makeText(this, "Error!", Toast.LENGTH_SHORT).show()
        }
    }
}

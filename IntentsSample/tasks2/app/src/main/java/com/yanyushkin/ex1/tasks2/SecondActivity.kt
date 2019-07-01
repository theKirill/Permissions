package com.yanyushkin.ex1.tasks2

import android.app.Activity
import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import kotlinx.android.synthetic.main.activity_second.*

class SecondActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_second)
    }

    fun button2Click(v: View) {
        val intentRes = Intent()
        intentRes.putExtra("message", editText.text.toString())
        setResult(Activity.RESULT_OK, intentRes)
        finish()
    }
}

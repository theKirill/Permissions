package com.yanyushkin.ex1.tasks

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.fragment_content2.*

class Fragment2 : Fragment() {

    override fun onStart() {
        super.onStart()

        val num = arguments!!.getString("count")
        textView.text = num
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? =
        inflater.inflate(R.layout.fragment_content2, null)
}
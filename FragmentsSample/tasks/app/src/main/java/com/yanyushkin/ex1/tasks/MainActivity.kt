package com.yanyushkin.ex1.tasks

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v4.app.FragmentManager
import android.support.v7.widget.Toolbar
import android.view.View
import android.widget.LinearLayout
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    private var count = 0
    private lateinit var manager: FragmentManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        manager = supportFragmentManager

        if (savedInstanceState != null) {
            val countStr = savedInstanceState.getString("count")
            count = countStr.toInt()

            if (manager.backStackEntryCount > 0)
                manager.popBackStack(null, FragmentManager.POP_BACK_STACK_INCLUSIVE)

            val range = 1..count

            //"рисуем" уничтоженные фрагменты
            for (i in range) {
                val myBundle = Bundle()
                myBundle.putString("count", i.toString())

                val frag2 = Fragment2()
                frag2.arguments = myBundle

                if (i == 1)
                    containerForFragments2.layoutParams =
                        LinearLayout.LayoutParams(
                            0,
                            Toolbar.LayoutParams.MATCH_PARENT,
                            1.0f
                        )//увеличиваем правый фрагмент

                manager.beginTransaction().add(R.id.containerForFragments2, frag2).addToBackStack(null).commit()
            }
        } else
            manager.beginTransaction().add(
                R.id.containerForFragments1,
                Fragment1()
            ).commit()//вставляем первый фрагмент при запуске
    }

    fun onClick(v: View) {//вставляем второй фрагмент при нажатии на кнопку
        count++

        //передаем новое состояние
        val myBundle = Bundle()
        myBundle.putString("count", count.toString())

        val frag2 = Fragment2()
        frag2.arguments = myBundle

        if (count == 1)
            containerForFragments2.layoutParams =
                LinearLayout.LayoutParams(0, Toolbar.LayoutParams.MATCH_PARENT, 1.0f)//увеличиваем правый фрагмент

        manager.beginTransaction().add(R.id.containerForFragments2, frag2).addToBackStack(null).commit()

        Toast.makeText(this, "Добавлен фрагмент!", Toast.LENGTH_SHORT).show()
    }

    override fun onBackPressed() {
        super.onBackPressed()

        count--

        if (count == 0)//нет фрагментов - расширяем левый
            containerForFragments2.layoutParams = LinearLayout.LayoutParams(0, Toolbar.LayoutParams.MATCH_PARENT, 0.0f)

        Toast.makeText(this, "Удалён фрагмент!", Toast.LENGTH_SHORT).show()
    }

    override fun onSaveInstanceState(outState: Bundle?) {
        super.onSaveInstanceState(outState)

        outState!!.putString("count", count.toString())//сохраняем при смене ориентации
    }
}
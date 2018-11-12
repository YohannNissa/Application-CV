package fr.yguerp.yohannguerpillonandroid.tools

import android.support.v4.app.Fragment
import android.support.v7.app.AppCompatActivity
import fr.yguerp.yohannguerpillonandroid.R
fun AppCompatActivity.replace(frag: Fragment) {
    supportFragmentManager.beginTransaction().replace(R.id.content, frag).commit()
}


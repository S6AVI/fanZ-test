package com.saleem.util

import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.fragment.app.Fragment

fun View.hide(){
    visibility = View.GONE
}

fun View.show(){
    visibility = View.VISIBLE
}

fun View.disable() {
    isEnabled = false
}

fun View.enable() {
    isEnabled = true
}

fun Fragment.toast(msg: String?){
    Toast.makeText(requireContext(),msg,Toast.LENGTH_LONG).show()
}

fun Any.logD(message: String) {
    Log.d("savii", message)
}
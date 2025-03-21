package org.choleemduo.doitnow.utils

import android.content.Context
import android.widget.Toast

object DoItUtils {
    fun showToast(context: Context, message: String) {
        Toast.makeText(context, message, Toast.LENGTH_SHORT).show()
    }
}
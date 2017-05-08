package com.nicopasso.babbelkombat.utils

import android.content.Context
import java.io.IOException

class BKUtils {

    companion object {
        val FILE_JSON = "json/words.json"
    }

    fun loadJSONFromAsset(context: Context): String? {
        val json: String?
        try {
            val inputStream = context.assets.open(FILE_JSON)
            val size = inputStream.available()
            val buffer = ByteArray(size)
            inputStream.read(buffer)
            inputStream.close()
            json = String(buffer)
        } catch (ex: IOException) {
            ex.printStackTrace()
            return null
        }
        return json
    }

    fun generateRandomNumber(upper: Int, lower: Int) =
            ((Math.random() * (upper - lower)) + lower).toInt()

}
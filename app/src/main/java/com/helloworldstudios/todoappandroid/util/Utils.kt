package com.helloworldstudios.todoappandroid.util

import com.google.gson.Gson
import com.google.gson.JsonObject

object Utils {
    val gson = Gson()
    fun extractDetailFromErrorBody(errorBody: String?): String?{
        return if (errorBody != null){
            try {
                val jsonObject = gson.fromJson(errorBody, JsonObject::class.java)
                jsonObject.getAsJsonPrimitive("detail")?.asString
            } catch (e: Exception){
                null
            }
        } else{
            null
        }
    }
}
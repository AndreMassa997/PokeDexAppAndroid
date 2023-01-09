package com.example.pokedexapp.common.api

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.net.Uri
import android.util.Log
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.io.BufferedReader
import java.io.InputStreamReader
import java.net.HttpURLConnection
import java.net.URL
import java.net.URLDecoder

class PokeAPI {

    companion object {
        val instance = PokeAPI()
    }

    fun get(path: String, queryParameters: LinkedHashMap<String, String>?, saveResponseOnCache: Boolean, onResponse: (StringBuffer?) -> Unit){
        val builder = Uri.Builder()
        builder.scheme("https")
        builder.authority("pokeapi.co/api/v2")
        builder.appendPath(path)
        queryParameters?.forEach{ (key, value) ->
            builder.appendQueryParameter(key, value)
        }

        val uri = builder.toString()
        val url = URL(URLDecoder.decode(uri, "UTF-8"))
        with(url.openConnection() as HttpURLConnection){
            requestMethod = "GET"

            try {
                Log.d("API","URL: $url")
                Log.d("API", "Response code: $responseCode")

                BufferedReader(InputStreamReader(inputStream)).use {
                    val response = StringBuffer()

                    var inputLine = it.readLine()
                    while (inputLine != null){
                        response.append(inputLine)
                        inputLine = it.readLine()
                    }
                    it.close()
                    Log.d("API", "Response message: $response")
                    onResponse(response)
                }
            }catch(e: Exception){
                Log.d("API", " " + e.localizedMessage)
            }
        }
    }

    fun getImageFromURL(urlString: String?, onResult: (Bitmap?) -> Unit){
        urlString?.let {
            val url = URL(urlString)
            CoroutineScope(Dispatchers.Default).launch {
                val bitmap = BitmapFactory.decodeStream(url.openConnection().getInputStream())
                CoroutineScope(Dispatchers.Main).launch{
                    onResult(bitmap)
                }
            }
        } ?: run {
            onResult(null)
        }
    }
}
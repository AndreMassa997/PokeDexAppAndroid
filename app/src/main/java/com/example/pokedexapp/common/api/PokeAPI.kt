package com.example.pokedexapp.common.api

import android.net.Uri
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
            println("URL: $url")
            println("Response code: $responseCode")

            BufferedReader(InputStreamReader(inputStream)).use {
                val response = StringBuffer()

                var inputLine = it.readLine()
                while (inputLine != null){
                    response.append(inputLine)
                    inputLine = it.readLine()
                }
                it.close()
                println("Response message: $response")
                onResponse(response)
            }
        }
    }


}
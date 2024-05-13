package com.example.pokedexapp

import java.net.URLDecoder

fun String.capitalizeFirst(): String{
    return this.first().uppercase() + this.drop(1)
}

private fun String.getUrlParams() : Map<String, String> {
    val params = mutableMapOf<String, String>()
    val query = substringAfter('?')
    if (query.isEmpty()) {
        return params
    }

    for (param in query.split('&')) {
        val (key, value) = param.split('=', limit = 2)
        params[URLDecoder.decode(key, "UTF-8")] = URLDecoder.decode(value, "UTF-8")
    }

    return params
}
fun String.getUrlParam(key: String): String? {
    val params = getUrlParams()
    return params[key]
}
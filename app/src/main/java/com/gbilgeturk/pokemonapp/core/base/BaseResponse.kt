package com.gbilgeturk.pokemonapp.core.base

import java.io.Serializable

data class BaseResponse<T>(
    val message: String,
    val data: T,
    val httpStatusCode: String,
    val success: Boolean
) : Serializable
package com.example.cherish_refactor.data.source.remote.api

import com.google.gson.annotations.SerializedName

data class PostponeWateringRequest(
    @SerializedName("id") val id: Int,
    @SerializedName("postpone") val postpone: Int,
    @SerializedName("is_limit_postpone_number") val isPostpone: Boolean
)

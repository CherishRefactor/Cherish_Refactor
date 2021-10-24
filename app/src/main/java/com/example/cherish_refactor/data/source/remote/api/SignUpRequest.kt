package com.example.cherish_refactor.data.source.remote.api

data class SignUpRequest(
    val email: String,
    val password: String,
    val nickname: String,
    val phone: String,
    val sex: String,
    val birth: String
)

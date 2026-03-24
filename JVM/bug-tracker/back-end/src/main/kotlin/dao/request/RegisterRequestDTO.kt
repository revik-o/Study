package com.sql.dao.request

data class RegisterRequestDTO(
    val email: String,
    val firstName: String,
    val lastName: String,
    val password: String,
    val passwordVerification: String
)
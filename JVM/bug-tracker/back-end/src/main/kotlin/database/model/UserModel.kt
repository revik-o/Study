package com.sql.database.model

import java.io.Serializable
import java.time.ZonedDateTime
import java.util.*

data class UserModel(
    val id: UUID? = null,
    val email: String,
    val username: String,
    val userLastName: String,
    val passwordHash: String,
    val avatar: String? = null,
    val deactivated: Boolean = false,
    val registrationDate: ZonedDateTime = ZonedDateTime.now(),
    val role: RoleModel,
) : Serializable

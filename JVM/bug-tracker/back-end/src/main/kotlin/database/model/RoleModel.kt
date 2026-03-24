package com.sql.database.model

import java.io.Serializable

data class RoleModel(
    val id: Int? = null,
    val name: String?,
) : Serializable {

    companion object {
        enum class DefaultRoleType {
            USER, ADMIN
        }
    }
}
